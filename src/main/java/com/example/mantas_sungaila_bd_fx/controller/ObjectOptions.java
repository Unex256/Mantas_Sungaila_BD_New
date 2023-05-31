package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.GenericObject;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.view.DrawingCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ObjectOptions implements Initializable{

    private final int selectedId;
    MainModel model;
    public TextArea objDescriptionTextArea;
    public TextField exitChance;
    public TextField riskChance;
    public Label exitCountLabel;
    public Label riskCountLabel;
    public TextField objNameTextArea;
    public Label idLabel;
    public Button confirmBtn;

    public CheckBox beginningNodeCheckBox;

    @FXML private DrawingCanvas drawingCanvas;

    public ObjectOptions(MainModel model, int selectedId) {
        this.model = model;
        this.selectedId = selectedId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(model.getArrayItemId());

        drawingCanvas.setBackgroundColor(Color.CYAN);

        GenericObject selectedObject = model.getObjectListItemById(selectedId);

        this.idLabel.setText(Integer.toString(model.getSelectedId().get()));
        this.objDescriptionTextArea.setText(selectedObject.getDescription());
        this.exitChance.setText(Float.toString(selectedObject.getExitChance()));
        this.riskChance.setText(Float.toString(selectedObject.getRiskChance()));
        this.exitCountLabel.setText(Integer.toString(selectedObject.getExitCount()));
        this.riskCountLabel.setText(Integer.toString(selectedObject.getRiskCount()));
        this.objNameTextArea.setText(selectedObject.getObjName());
        this.beginningNodeCheckBox.setSelected(selectedObject.isBeginningNode());
    }

    public void onConfirmBtnPress() {
        GenericObject selectedObject = model.getObjectListItemById(selectedId);

        selectedObject.setDescription(objDescriptionTextArea.getText());
        selectedObject.setExitChance(Float.parseFloat(exitChance.getText()));
        selectedObject.setObjName(objNameTextArea.getText());
        selectedObject.setRiskChance(Float.parseFloat(riskChance.getText()));
        selectedObject.resetChances();

        Image image = drawingCanvas.snapshot(new SnapshotParameters(), null);
        model.getShapeListItemById(selectedObject.getShapeId()).setImage(image);
    }


    public void checkBoxCheck(){
        model.getObjectListItemById(selectedId).setBeginningNode(beginningNodeCheckBox.isSelected());
    }
}
