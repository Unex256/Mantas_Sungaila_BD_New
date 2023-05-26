package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Connection;
import com.example.mantas_sungaila_bd_fx.model.Influence;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.model.Object;
import com.example.mantas_sungaila_bd_fx.view.ApplicationLauncher;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    private MainModel model = new MainModel();

    @FXML private AnchorPane mainPane;

    @FXML private Label selectedIdLabel;

    @FXML private Label secondarySelectedIdLabel;

    @FXML private Slider speedSlider;

    @FXML private ToggleButton startStopSimulationBtn;

    @FXML private Label stepCountLabel;
    public int idIntType1 = 1;
    public int idIntType2 = 2;

    private int simulationSpeed = 1;

    private int stepCount = 0;

    private int shapeId = 0;


    private final Set<String> connectionSet = new HashSet<>();

    private final DraggableSelection draggableMaker = new DraggableSelection(model);
    private boolean notAdjusted = true;

    public void onNewObjectBtnPressed() {
        createNewObject();
        newObject();
    }

    private void createNewObject() {
        model.getObjectList().add(new Object(idIntType1, shapeId));
    }

    public void onNewInfluenceBtnPressed() {
        createNewInfluence();
        newInfluence();
    }

    private void createNewInfluence() {
        model.getInfluenceList().add(new Influence(idIntType2, shapeId));
    }

    public void newObject() {
        model.getShapeList().add(new Rectangle(50, 50));

        model.getShapeList().get(shapeId).setStroke(Color.BLACK);
        model.getShapeList().get(shapeId).setFill(Color.CYAN);
        model.getShapeList().get(shapeId).setId(Integer.toString(idIntType1));
        draggableMaker.makeDraggable(model.getShapeList().get(shapeId));
        mainPane.getChildren().add(model.getShapeList().get(shapeId));
        this.shapeId++;
        this.idIntType1+=2;
    }
    public void newInfluence() {
        model.getShapeList().add(new Rectangle(50, 50));

        model.getShapeList().get(shapeId).setStroke(Color.BLACK);
        model.getShapeList().get(shapeId).setFill(Color.SANDYBROWN);
        model.getShapeList().get(shapeId).setId(Integer.toString(idIntType2));
        draggableMaker.makeDraggable(model.getShapeList().get(shapeId));
        mainPane.getChildren().add(model.getShapeList().get(shapeId));
        this.shapeId++;
        this.idIntType2+=2;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedIdLabel.textProperty().bind(model.getSelectedId().asString());
        secondarySelectedIdLabel.textProperty().bind(model.getSecondarySelectedId().asString());

        speedSlider.valueProperty().addListener((observableValue, number, t1) -> {
            simulationSpeed = (int)speedSlider.getValue();
            System.out.println(simulationSpeed);
        });
    }

    public void editSelectedNode() throws IOException {
        //System.out.println("Edit selected node is pressed, node id:");
        //System.out.println(model.getSelectedId().get());
        switch (model.returnSelectedListItem(model.getSelectedId().get())[0]) {
            case 1 -> {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/com/example/mantas_sungaila_bd_fx/object-options.fxml"));
                fxmlLoader.setControllerFactory(c -> new ObjectOptions(model));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Object options");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            }
            case 2 -> {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/com/example/mantas_sungaila_bd_fx/influence-options.fxml"));
                fxmlLoader.setControllerFactory(c -> new InfluenceOptions(model));
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
        if(model.getSelectedId().get() != 0 && model.getSecondarySelectedId().get() != 0 && model.getSecondarySelectedId().get() != model.getSelectedId().get()){
            String connectionKey = model.getSelectedId() + ":" + model.getSecondarySelectedId();

            if (!connectionSet.contains(connectionKey)) {
                model.getConnectionList().add(new Connection(model.getSelectedId().get(), model.getSecondarySelectedId().get(), model.getLineId(), model));
                addConnectionToObject(model.getSelectedId().get(), model.getSecondarySelectedId().get());

                // Add the connection key to the set
                connectionSet.add(connectionKey);
            }  // messageText.setText("Connection already exists");

        }
    }

    private void addConnectionToObject(int selectedId, int secondarySelectedId) {
        if (selectedId % 2 == 1) {
            for (Object object : model.getObjectList()) {
                if(object.getId() == selectedId){
                    object.addConnection(secondarySelectedId);
                }
            }
        }else {
            for (Influence influence : model.getInfluenceList()) {
                if(influence.getId() == selectedId){
                    influence.addConnection(secondarySelectedId);
                }
            }
        }
    }

    public void startStopSimulation() {
        if(notAdjusted) {
            for (Influence influence : model.getInfluenceList()) {
                for (Integer integer : influence.getConnections()) {
                    System.out.println(model.getObjectList().get(integer / 2).getExitChance() + " " + influence.getExitValueChange() + " " + integer / 2);
                    model.getObjectList().get(integer / 2).adjustExitChance(influence.getExitValueChange());
                    System.out.println(model.getObjectList().get(integer / 2).getAdjustedExitChance());
                    System.out.println(model.getObjectList().get(integer / 2).getRiskChance());
                    model.getObjectList().get(integer / 2).adjustRiskChance(influence.getRiskValueChange());
                    System.out.println(model.getObjectList().get(integer / 2).getAdjustedRiskChance());
                }
            }
            this.notAdjusted = false;
        } else{
            for (Influence influence : model.getInfluenceList()) {
                for (Integer integer : influence.getConnections()) {
                    System.out.println(model.getObjectList().get(integer / 2).getExitChance() + " " + influence.getExitValueChange());
                    model.getObjectList().get(integer / 2).adjustExitChance(-influence.getExitValueChange());
                    System.out.println(model.getObjectList().get(integer / 2).getAdjustedExitChance());
                    System.out.println(model.getObjectList().get(integer / 2).getRiskChance() + " " + influence.getRiskValueChange());
                    model.getObjectList().get(integer / 2).adjustRiskChance(-influence.getRiskValueChange());
                    System.out.println(model.getObjectList().get(integer / 2).getAdjustedRiskChance());
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
                            for (Object object : model.getObjectList()) {
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
                                objectTree(model.getObjectList().get(integer / 2));

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
        model.getStepCountLabel().setText(Integer.toString(stepCount));
    }

}

