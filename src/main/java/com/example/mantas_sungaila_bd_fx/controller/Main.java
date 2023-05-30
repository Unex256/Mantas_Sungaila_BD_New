package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Connection;
import com.example.mantas_sungaila_bd_fx.model.Influence;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.model.GenericObject;
import com.example.mantas_sungaila_bd_fx.view.ApplicationLauncher;
import com.example.mantas_sungaila_bd_fx.view.CustomNode;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class Main implements Initializable {

    private final MainModel model = new MainModel();

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
        model.getObjectList().put(idIntType1, new GenericObject(idIntType1, shapeId));
        System.out.println("Obj list after creation:");
        System.out.println(model.getObjectList());
    }

    public void onNewInfluenceBtnPressed() {
        createNewInfluence();
        newInfluence();
    }

    private void createNewInfluence() {
        model.getInfluenceList().put(idIntType2, new Influence(idIntType2, shapeId));
        System.out.println("Inf list after creation:");
        System.out.println(model.getInfluenceList());
    }

    public void newObject() {
        CustomNode customNode = new CustomNode(idIntType1, "CYAN");
        model.getShapeList().put(shapeId, customNode);
        model.getShapeList().get(shapeId).setId(Integer.toString(idIntType1));
        draggableMaker.makeDraggable(model.getShapeList().get(shapeId));
        mainPane.getChildren().add(model.getShapeList().get(shapeId));
        this.shapeId++;
        this.idIntType1+=2;
    }
    public void newInfluence() {
        CustomNode customNode = new CustomNode(idIntType2, "SANDYBROWN");
        model.getShapeList().put(shapeId, customNode);
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

        Service<Void> toggleService = createToggleService();

        startStopSimulationBtn.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal) {
                toggleService.reset();
                toggleService.start();
            }
            else
                toggleService.cancel();

        });
    }

    private Service<Void> createToggleService() {
        return new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        while (!isCancelled()) {
                            System.out.println(stepCount);
                            Platform.runLater(Main.this::updateStepCount);
                            for (GenericObject object : model.getObjectList().values()) {
                                if (object.isBeginningNode()) {
                                    objectTree(object);
                                }
                            }
                            Thread.sleep(1000 / simulationSpeed);
                            stepCount++;
                        }
                        return null;
                    }

                    private void objectTree(GenericObject object){
                        if (Math.random() < object.getAdjustedRiskChance()) {
                            object.increaseRiskCount();
                        }
                        if (Math.random() < object.getAdjustedExitChance()) {
                            for (Map.Entry<Integer, Integer> entry : object.getConnections().entrySet()) {
                                Platform.runLater(() -> model.getArrowById(entry.getKey()).animateArrow(simulationSpeed));
                                objectTree(model.getObjectList().get(entry.getValue()));
                            }
                        }
                    }
                };
            }
        };
    }

    public void editSelectedNode() throws IOException {
        //System.out.println("Edit selected node is pressed, node id:");
        //System.out.println(model.getSelectedId().get());
        Object oj = model.returnSelectedListItem(model.getSelectedId().get());
        if (oj instanceof GenericObject) {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/com/example/mantas_sungaila_bd_fx/object-options.fxml"));
                fxmlLoader.setControllerFactory(c -> new ObjectOptions(model, model.getSelectedId().get()));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Object options");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            }
            else if (oj instanceof Influence) {
                FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("/com/example/mantas_sungaila_bd_fx/influence-options.fxml"));
                fxmlLoader.setControllerFactory(c -> new InfluenceOptions(model, model.getSelectedId().get()));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Outside influence");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            }
        }


    public void makeConnection() {
        if(model.getSelectedId().get() != 0 && model.getSecondarySelectedId().get() != 0 && model.getSecondarySelectedId().get() != model.getSelectedId().get()){
            String connectionKey = model.getSelectedId() + ":" + model.getSecondarySelectedId();

            if (!connectionSet.contains(connectionKey)) {
                model.getConnectionList().put(model.getArrowId(), new Connection(model.getSelectedId().get(), model.getSecondarySelectedId().get(), model.getArrowId(), model));
                addConnectionToObject(model.getSelectedId().get(), model.getSecondarySelectedId().get(), model.getArrowId() - 1);

                connectionSet.add(connectionKey);
            }  // messageText.setText("Connection already exists");

        }
    }

    private void addConnectionToObject(int selectedId, int secondarySelectedId, int arrowId) {
        if (selectedId % 2 == 1) {
            System.out.println(model.getObjectList());
            model.getObjectListItemById(selectedId).addConnection(secondarySelectedId, arrowId);
        } else {
            System.out.println(model.getInfluenceList());
            model.getInfluenceListItemById(selectedId).addConnection(secondarySelectedId, arrowId);
        }
    }

    public void startStopSimulation() {
        if(notAdjusted) {
            for (Influence influence : model.getInfluenceList().values()) {
                for (Integer integer : influence.getConnections().values()) {
                    model.getObjectList().get(integer).adjustExitChance(influence.getExitValueChange());
                    model.getObjectList().get(integer).adjustRiskChance(influence.getRiskValueChange());
                }
            }
            this.notAdjusted = false;
        } else{
            for (Influence influence : model.getInfluenceList().values()) {
                for (Integer integer : influence.getConnections().values()) {
                    model.getObjectList().get(integer).adjustExitChance(-influence.getExitValueChange());
                    model.getObjectList().get(integer).adjustRiskChance(-influence.getRiskValueChange());
                }
            }
            this.notAdjusted = true;
        }
    }

    private void updateStepCount() {
        stepCountLabel.setText(Integer.toString(stepCount));
    }

}

