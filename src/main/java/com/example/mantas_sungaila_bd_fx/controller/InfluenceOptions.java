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
        this.idLabel.setText(Integer.toString(MainModel.getSelectedId()));
        this.objDescriptionTextArea.setText(MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).getDescription());
        this.exitValueChange.setText(Float.toString(MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).getExitValueChange()));
        this.riskValueChange.setText(Float.toString(MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).getRiskValueChange()));
        this.objNameTextArea.setText(MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).getObjName());
    }

    public void onConfirmBtnPress() {
        MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).setDescription(objDescriptionTextArea.getText());
        MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).setExitValueChange(Float.parseFloat(exitValueChange.getText()));
        MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).setObjName(objNameTextArea.getText());
        MainModel.getInfluenceList().get(MainModel.getStaticArrayItemId()).setRiskValueChange(Float.parseFloat(riskValueChange.getText()));
    }
}
