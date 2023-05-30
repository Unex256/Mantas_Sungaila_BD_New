package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Influence;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import com.example.mantas_sungaila_bd_fx.view.DrawingCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class InfluenceOptions implements Initializable {

    private final int selectedId;
    MainModel model;
    public TextArea objDescriptionTextArea;
    public TextField exitValueChange;
    public TextField riskValueChange;
    public TextField objNameTextArea;
    public Label idLabel;
    public Button confirmBtn;

    @FXML
    private DrawingCanvas drawingCanvas;

    public InfluenceOptions(MainModel model, int selectedId) {
        this.model = model;
        this.selectedId = selectedId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drawingCanvas.setBackgroundColor(Color.SANDYBROWN);

        this.idLabel.setText(Integer.toString(model.getSelectedId().get()));
        this.objDescriptionTextArea.setText(model.getInfluenceListItemById(selectedId).getDescription());
        this.exitValueChange.setText(Float.toString(model.getInfluenceListItemById(selectedId).getExitValueChange()));
        this.riskValueChange.setText(Float.toString(model.getInfluenceListItemById(selectedId).getRiskValueChange()));
        this.objNameTextArea.setText(model.getInfluenceListItemById(selectedId).getObjName());
    }

    public void onConfirmBtnPress() {
        Influence selectedInfluence = model.getInfluenceListItemById(selectedId);
        selectedInfluence.setDescription(objDescriptionTextArea.getText());
        selectedInfluence.setExitValueChange(Float.parseFloat(exitValueChange.getText()));
        selectedInfluence.setObjName(objNameTextArea.getText());
        selectedInfluence.setRiskValueChange(Float.parseFloat(riskValueChange.getText()));

        Image image = drawingCanvas.snapshot(new SnapshotParameters(), null);
        model.getShapeListItemById(selectedInfluence.getShapeId()).setImage(image);
    }

}
