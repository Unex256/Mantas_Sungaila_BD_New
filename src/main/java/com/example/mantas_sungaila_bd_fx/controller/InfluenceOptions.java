package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.MainModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InfluenceOptions implements Initializable {

    MainModel model;
    public TextArea objDescriptionTextArea;
    public TextField exitValueChange;
    public TextField riskValueChange;
    public TextField objNameTextArea;
    public Label idLabel;
    public Button confirmBtn;

    public InfluenceOptions(MainModel model) {
        this.model = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idLabel.setText(Integer.toString(model.getSelectedId()));
        this.objDescriptionTextArea.setText(model.getInfluenceList().get(model.getArrayItemId()).getDescription());
        this.exitValueChange.setText(Float.toString(model.getInfluenceList().get(model.getArrayItemId()).getExitValueChange()));
        this.riskValueChange.setText(Float.toString(model.getInfluenceList().get(model.getArrayItemId()).getRiskValueChange()));
        this.objNameTextArea.setText(model.getInfluenceList().get(model.getArrayItemId()).getObjName());
    }

    public void onConfirmBtnPress() {
        model.getInfluenceList().get(model.getArrayItemId()).setDescription(objDescriptionTextArea.getText());
        model.getInfluenceList().get(model.getArrayItemId()).setExitValueChange(Float.parseFloat(exitValueChange.getText()));
        model.getInfluenceList().get(model.getArrayItemId()).setObjName(objNameTextArea.getText());
        model.getInfluenceList().get(model.getArrayItemId()).setRiskValueChange(Float.parseFloat(riskValueChange.getText()));
    }

    public void setModel(MainModel model){
        this.model = model;
    }
}
