package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Connection;
import com.example.mantas_sungaila_bd_fx.model.Influence;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.model.Object;
import com.example.mantas_sungaila_bd_fx.view.ApplicationLauncher;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class Main implements Initializable {

    public AnchorPane mainPane;

    public Button newObjectBtn;

    public  Label selectedIdLabel;

    public int idIntType1 = 1;
    public int idIntType2 = 2;
    public Slider speedSlider;

    private int simulationSpeed = 1;
    public ToggleButton startStopSimulationBtn;
    public Label stepCountLabel;

    private int stepCount = 0;

    private int shapeId = 0;


    private final Set<String> connectionSet = new HashSet<>();

    public Label secondarySelectedIdLabel;

    DraggableSelection draggableMaker = new DraggableSelection();
    private boolean notAdjusted = true;

    public void onNewObjectBtnPressed() {
        createNewObject();
        newObject();
    }

    private void createNewObject() {
        MainModel.getObjectList().add(new Object(idIntType1, shapeId));
    }

    public void onNewInfluenceBtnPressed() {
        createNewInfluence();
        newInfluence();
    }

    private void createNewInfluence() {
        MainModel.getInfluenceList().add(new Influence(idIntType2, shapeId));
    }

    public void newObject() {
        MainModel.getShapeList().add(new Rectangle(50, 50));

        MainModel.getShapeList().get(shapeId).setStroke(Color.BLACK);
        MainModel.getShapeList().get(shapeId).setFill(Color.CYAN);
        MainModel.getShapeList().get(shapeId).setId(Integer.toString(idIntType1));
        draggableMaker.makeDraggable(MainModel.getShapeList().get(shapeId));
        mainPane.getChildren().add(MainModel.getShapeList().get(shapeId));
        this.shapeId++;
        this.idIntType1++;
        this.idIntType1++;
    }
    public void newInfluence() {
        MainModel.getShapeList().add(new Rectangle(50, 50));

        MainModel.getShapeList().get(shapeId).setStroke(Color.BLACK);
        MainModel.getShapeList().get(shapeId).setFill(Color.SANDYBROWN);
        MainModel.getShapeList().get(shapeId).setId(Integer.toString(idIntType2));
        draggableMaker.makeDraggable(MainModel.getShapeList().get(shapeId));
        mainPane.getChildren().add(MainModel.getShapeList().get(shapeId));
        this.shapeId++;
        this.idIntType2++;
        this.idIntType2++;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainModel.setStaticSelectedIdLabel(selectedIdLabel);
        MainModel.setStaticSecondarySelectedIdLabel(secondarySelectedIdLabel);
        MainModel.setStaticStepCountLabel(stepCountLabel);

        speedSlider.valueProperty().addListener((observableValue, number, t1) -> {

            simulationSpeed = (int)speedSlider.getValue();
            System.out.println(simulationSpeed);
        });
    }

    public void editSelectedNode() throws IOException {
        switch (MainModel.returnSelectedListItem(MainModel.getSelectedId())[0]) {
            case 1 -> {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/com/example/mantas_sungaila_bd_fx/object-options.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Object options");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            }
            case 2 -> {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/com/example/mantas_sungaila_bd_fx/influence-options.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Outside influence");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            }
        }

    }


    public void makeConnection() {
        if(MainModel.getSelectedId() != 0 && MainModel.getSecondarySelectedId() != 0 && MainModel.getSecondarySelectedId() != MainModel.getSelectedId()){
            String connectionKey = MainModel.getSelectedId() + ":" + MainModel.getSecondarySelectedId();

            if (!connectionSet.contains(connectionKey)) {
                MainModel.getConnectionList().add(new Connection(MainModel.getSelectedId(), MainModel.getSecondarySelectedId(), MainModel.getStaticLineId()));
                addConnectionToObject(MainModel.getSelectedId(), MainModel.getSecondarySelectedId());

                // Add the connection key to the set
                connectionSet.add(connectionKey);
            }  // messageText.setText("Connection already exists");

        }
    }

    private void addConnectionToObject(int selectedId, int secondarySelectedId) {
        if (selectedId % 2 == 1) {
            for (Object object : MainModel.getObjectList()) {
                if(object.getId() == selectedId){
                    object.addConnection(secondarySelectedId);
                }
            }
        }else {
            for (Influence influence : MainModel.getInfluenceList()) {
                if(influence.getId() == selectedId){
                    influence.addConnection(secondarySelectedId);
                }
            }
        }
    }

    public void startStopSimulation() {
        if(notAdjusted) {
            for (Influence influence : MainModel.getInfluenceList()) {
                for (Integer integer : influence.getConnections()) {
                    System.out.println(MainModel.getObjectList().get(integer / 2).getExitChance() + " " + influence.getExitValueChange() + " " + integer / 2);
                    MainModel.getObjectList().get(integer / 2).adjustExitChance(influence.getExitValueChange());
                    System.out.println(MainModel.getObjectList().get(integer / 2).getAdjustedExitChance());
                    System.out.println(MainModel.getObjectList().get(integer / 2).getRiskChance());
                    MainModel.getObjectList().get(integer / 2).adjustRiskChance(influence.getRiskValueChange());
                    System.out.println(MainModel.getObjectList().get(integer / 2).getAdjustedRiskChance());
                }
            }
            this.notAdjusted = false;
        } else{
            for (Influence influence : MainModel.getInfluenceList()) {
                for (Integer integer : influence.getConnections()) {
                    System.out.println(MainModel.getObjectList().get(integer / 2).getExitChance() + " " + influence.getExitValueChange());
                    MainModel.getObjectList().get(integer / 2).adjustExitChance(-influence.getExitValueChange());
                    System.out.println(MainModel.getObjectList().get(integer / 2).getAdjustedExitChance());
                    System.out.println(MainModel.getObjectList().get(integer / 2).getRiskChance() + " " + influence.getRiskValueChange());
                    MainModel.getObjectList().get(integer / 2).adjustRiskChance(-influence.getRiskValueChange());
                    System.out.println(MainModel.getObjectList().get(integer / 2).getAdjustedRiskChance());
                }
            }
            this.notAdjusted = true;
        }
        Service<Void> toggleService = new Service<>() {

            @Override
            protected Task<Void> createTask() {

                return new Task<>() {

                    @Override
                    protected Void call() throws Exception {

                        while (!isCancelled()) {
                            System.out.println(stepCount);
                            Platform.runLater(Main.this::updateStepCount);
                            for (Object object : MainModel.getObjectList()) {
                                if (object.isBeginningNode()) {
                                    objectTree(object);
                                }
                            }

                            Thread.sleep(1000 / simulationSpeed);
                            stepCount++;
                        }
                        return null;
                    }

                    private void objectTree(Object object) {
                        if (Math.random() < object.getAdjustedRiskChance()) {
                            object.increaseRiskCount();
                            System.out.println(Math.random() + " " + object.getRiskCount());
                        }
                        if (Math.random() < object.getAdjustedExitChance()) {
                            for (Integer integer : object.getConnections()) {
                                objectTree(MainModel.getObjectList().get(integer / 2));

                            }
                        }
                    }
                };
            }
        };

        startStopSimulationBtn.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal) {
                toggleService.reset();
                toggleService.start();
            }
            else
                toggleService.cancel();

        });
    }

    private void updateStepCount() {
        MainModel.getStaticStepCountLabel().setText(Integer.toString(stepCount));
    }

}

