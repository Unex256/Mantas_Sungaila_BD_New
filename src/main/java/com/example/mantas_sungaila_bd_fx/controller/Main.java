package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Connection;
import com.example.mantas_sungaila_bd_fx.model.Influence;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.model.Object;
import com.example.mantas_sungaila_bd_fx.view.ApplicationLauncher;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class Main implements Initializable {

    private MainModel model = new MainModel();

    public AnchorPane mainPane;

    public Button newObjectBtn;

    public  Label selectedIdLabel;
    public static Label staticSelectedIdLabel;

    public int idIntType1 = 1;
    public int idIntType2 = 2;
    public static int selectedId = 0;
    public static int secondarySelectedId = 0;
    public static int staticArrayItemId;
    public Slider speedSlider;

    private int simulationSpeed = 1;
    public ToggleButton startStopSimulationBtn;
    public Label stepCountLabel;

    private int stepCount = 0;
    public static Label staticStepCountLabel;

    private int shapeId = 0;


    public static ArrayList<Object> objectList = new ArrayList<Object>();

    public static ArrayList<Influence> influenceList = new ArrayList<Influence>();

    public static ArrayList<Connection> connectionList = new ArrayList<Connection>();

    private Set<String> connectionSet = new HashSet<>();

    public static ArrayList<Shape> shapeList = new ArrayList<Shape>();

    public static ArrayList<Line> lineList = new ArrayList<Line>();

    public static int staticLineId = 0;
    public Label secondarySelectedIdLabel;

    public static Label staticSecondarySelectedIdLabel;
    DraggableSelection draggableMaker = new DraggableSelection();
    private boolean notAdjusted = true;

    public void onNewObjectBtnPressed(ActionEvent actionEvent) throws IOException {
        createNewObject();
        newObject();
    }

    private void createNewObject() {
        this.objectList.add(new Object(idIntType1, shapeId));
    }

    public void onNewInfluenceBtnPressed(ActionEvent actionEvent) throws IOException {
        createNewInfluence();
        newInfluence();
    }

    private void createNewInfluence() {
        this.influenceList.add(new Influence(idIntType2, shapeId));
    }

    public void newObject() {
        Main.shapeList.add(new Rectangle(50, 50));

        Main.shapeList.get(shapeId).setStroke(Color.BLACK);
        Main.shapeList.get(shapeId).setFill(Color.CYAN);
        Main.shapeList.get(shapeId).setId(Integer.toString(idIntType1));
        draggableMaker.makeDraggable(Main.shapeList.get(shapeId));
        mainPane.getChildren().add(Main.shapeList.get(shapeId));
        this.shapeId++;
        this.idIntType1++;
        this.idIntType1++;
    }
    public void newInfluence() {
        Main.shapeList.add(new Rectangle(50, 50));

        Main.shapeList.get(shapeId).setStroke(Color.BLACK);
        Main.shapeList.get(shapeId).setFill(Color.SANDYBROWN);
        Main.shapeList.get(shapeId).setId(Integer.toString(idIntType2));
        draggableMaker.makeDraggable(Main.shapeList.get(shapeId));
        mainPane.getChildren().add(Main.shapeList.get(shapeId));
        this.shapeId++;
        this.idIntType2++;
        this.idIntType2++;
    }

    public static void setSelectedId(int id) {
        Main.selectedId = id;
        //System.out.println(this.selectedIdLabel.getText());
    }

    public static int getSecondarySelectedId() {
        return secondarySelectedId;
    }

    public static void setSecondarySelectedId(int secondarySelectedId) {
        Main.secondarySelectedId = secondarySelectedId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        staticSelectedIdLabel = selectedIdLabel;
        staticSecondarySelectedIdLabel = secondarySelectedIdLabel;
        staticStepCountLabel = stepCountLabel;

        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

                simulationSpeed = (int)speedSlider.getValue();
                System.out.println(simulationSpeed);
            }
        });
    }

    public static int getStaticArrayItemId() {
        return staticArrayItemId;
    }

    public void editSelectedNode(ActionEvent event) throws IOException {
        switch (returnSelectedListItem(selectedId)[0]) {
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


    public static int[] returnSelectedListItem(int selectedId){

        if (selectedId % 2 == 1) {
            Main.staticArrayItemId = 0;
            for (Object object : objectList) {
                if(object.getId() == selectedId){
                    return new int[]{1, staticArrayItemId};
                }
               Main.staticArrayItemId++;
            }
        }else {
            Main.staticArrayItemId = 0;
            for (Influence influence : influenceList) {
                if(influence.getId() == selectedId){
                    return new int[]{2, staticArrayItemId};
                }
                Main.staticArrayItemId++;
            }
        }
            return new int[] {0,0};
    }
    public void makeConnection(ActionEvent event) {
        if(selectedId != 0 && secondarySelectedId != 0 && secondarySelectedId != selectedId){
            String connectionKey = selectedId + ":" + secondarySelectedId;

            if (!connectionSet.contains(connectionKey)) {
                connectionList.add(new Connection(selectedId, secondarySelectedId, staticLineId));
                addConnectionToObject(selectedId, secondarySelectedId);

                // Add the connection key to the set
                connectionSet.add(connectionKey);
            } else {
                // messageText.setText("Connection already exists");
            }
        }
    }

    private void addConnectionToObject(int selectedId, int secondarySelectedId) {
        if (selectedId % 2 == 1) {
            for (Object object : objectList) {
                if(object.getId() == selectedId){
                    object.addConnection(secondarySelectedId);
                }
            }
        }else {
            for (Influence influence : influenceList) {
                if(influence.getId() == selectedId){
                    influence.addConnection(secondarySelectedId);
                }
            }
        }
    }

    public void startStopSimulation(ActionEvent event) throws InterruptedException {
        if(notAdjusted) {
            for (Influence influence : influenceList) {
                for (Integer integer : influence.getConnections()) {
                    System.out.println(Main.objectList.get(integer / 2).getExitChance() + " " + influence.getExitValueChange() + " " + integer / 2);
                    Main.objectList.get(integer / 2).adjustExitChance(influence.getExitValueChange());
                    System.out.println(Main.objectList.get(integer / 2).getAdjustedExitChance());
                    System.out.println(Main.objectList.get(integer / 2).getRiskChance());
                    Main.objectList.get(integer / 2).adjustRiskChance(influence.getRiskValueChange());
                    System.out.println(Main.objectList.get(integer / 2).getAdjustedRiskChance());
                }
            }
            this.notAdjusted = false;
        } else{
            for (Influence influence : influenceList) {
                for (Integer integer : influence.getConnections()) {
                    System.out.println(Main.objectList.get(integer / 2).getExitChance() + " " + influence.getExitValueChange());
                    Main.objectList.get(integer / 2).adjustExitChance(-influence.getExitValueChange());
                    System.out.println(Main.objectList.get(integer / 2).getAdjustedExitChance());
                    System.out.println(Main.objectList.get(integer / 2).getRiskChance() + " " + influence.getRiskValueChange());
                    Main.objectList.get(integer / 2).adjustRiskChance(-influence.getRiskValueChange());
                    System.out.println(Main.objectList.get(integer / 2).getAdjustedRiskChance());
                }
            }
            this.notAdjusted = true;
        }
        Service<Void> toggleService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {

                return new Task<Void>(){

                    @Override
                    protected Void call() throws Exception {

                        while(!isCancelled()) {
                            System.out.println(stepCount);
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    updateStepCount();
                                }
                            });
                            for (Object object : objectList) {
                                if(object.isBeginningNode()) {
                                    objectTree(object);
                                }
                            }

                            Thread.sleep(1000/simulationSpeed);
                            stepCount++;
                        }
                        return null;
                    }

                    private void objectTree(Object object) {
                        if(Math.random() < object.getAdjustedRiskChance()){
                            object.increaseRiskCount();
                            System.out.println(Math.random() + " " + object.getRiskCount());
                        }
                        if(Math.random() < object.getAdjustedExitChance()){
                            for(Integer integer : object.getConnections()){
                                objectTree(Main.objectList.get(integer / 2));

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
        Main.staticStepCountLabel.setText(Integer.toString(stepCount));
    }

}

