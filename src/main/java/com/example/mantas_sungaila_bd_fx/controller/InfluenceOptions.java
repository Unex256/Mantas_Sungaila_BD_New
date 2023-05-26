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
    public TextArea objDescriptionTextArea;
    public TextField exitValueChange;
    public TextField riskValueChange;
    public TextField objNameTextArea;
    public Label idLabel;
    public Button confirmBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idLabel.setText(Integer.toString(MainModel.selectedId));
        this.objDescriptionTextArea.setText(MainModel.influenceList.get(MainModel.staticArrayItemId).getDescription());
        this.exitValueChange.setText(Float.toString(MainModel.influenceList.get(MainModel.staticArrayItemId).getExitValueChange()));
        this.riskValueChange.setText(Float.toString(MainModel.influenceList.get(MainModel.staticArrayItemId).getRiskValueChange()));
        this.objNameTextArea.setText(MainModel.influenceList.get(MainModel.staticArrayItemId).getObjName());
    }

    public void onConfirmBtnPress() {
        MainModel.influenceList.get(MainModel.staticArrayItemId).setDescription(objDescriptionTextArea.getText());
        MainModel.influenceList.get(MainModel.staticArrayItemId).setExitValueChange(Float.parseFloat(exitValueChange.getText()));
        MainModel.influenceList.get(MainModel.staticArrayItemId).setObjName(objNameTextArea.getText());
        MainModel.influenceList.get(MainModel.staticArrayItemId).setRiskValueChange(Float.parseFloat(riskValueChange.getText()));
    }
}
