package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.view.CustomNode;
import com.example.mantas_sungaila_bd_fx.view.DrawingCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ObjectOptions implements Initializable{

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

    public ObjectOptions(MainModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(model.getArrayItemId());

        drawingCanvas.setBackgroundColor(Color.CYAN);

        this.idLabel.setText(Integer.toString(model.getSelectedId().get()));
        this.objDescriptionTextArea.setText(model.getObjectList().get(model.getArrayItemId()).getDescription());
        this.exitChance.setText(Float.toString(model.getObjectList().get(model.getArrayItemId()).getExitChance()));
        this.riskChance.setText(Float.toString(model.getObjectList().get(model.getArrayItemId()).getRiskChance()));
        this.exitCountLabel.setText(Integer.toString(model.getObjectList().get(model.getArrayItemId()).getExitCount()));
        this.riskCountLabel.setText(Integer.toString(model.getObjectList().get(model.getArrayItemId()).getRiskCount()));
        this.objNameTextArea.setText(model.getObjectList().get(model.getArrayItemId()).getObjName());
        this.beginningNodeCheckBox.setSelected(model.getObjectList().get(model.getArrayItemId()).isBeginningNode());
    }

    public void onConfirmBtnPress() {
        model.getObjectList().get(model.getArrayItemId()).setDescription(objDescriptionTextArea.getText());
        model.getObjectList().get(model.getArrayItemId()).setExitChance(Float.parseFloat(exitChance.getText()));
        model.getObjectList().get(model.getArrayItemId()).setObjName(objNameTextArea.getText());
        model.getObjectList().get(model.getArrayItemId()).setRiskChance(Float.parseFloat(riskChance.getText()));
        model.getObjectList().get(model.getArrayItemId()).resetChances();

        Image image = drawingCanvas.snapshot(new SnapshotParameters(), null);

        model.getShapeListItemById(model.getSelectedId().get()).setImage(image);

    }

    public void checkBoxCheck(){
        model.getObjectList().get(model.getArrayItemId()).setBeginningNode(beginningNodeCheckBox.isSelected());
    }
}
