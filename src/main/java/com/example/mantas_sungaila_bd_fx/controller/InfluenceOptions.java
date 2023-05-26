package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.controller.Main;
import javafx.event.ActionEvent;
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
        this.idLabel.setText(Integer.toString(Main.selectedId));
        this.objDescriptionTextArea.setText(Main.influenceList.get(Main.staticArrayItemId).getDescription());
        this.exitValueChange.setText(Float.toString(Main.influenceList.get(Main.staticArrayItemId).getExitValueChange()));
        this.riskValueChange.setText(Float.toString(Main.influenceList.get(Main.staticArrayItemId).getRiskValueChange()));
        this.objNameTextArea.setText(Main.influenceList.get(Main.staticArrayItemId).getObjName());
    }

    public void onConfirmBtnPress(ActionEvent event) {
        Main.influenceList.get(Main.staticArrayItemId).setDescription(objDescriptionTextArea.getText());
        Main.influenceList.get(Main.staticArrayItemId).setExitValueChange(Float.parseFloat(exitValueChange.getText()));
        Main.influenceList.get(Main.staticArrayItemId).setObjName(objNameTextArea.getText());
        Main.influenceList.get(Main.staticArrayItemId).setRiskValueChange(Float.parseFloat(riskValueChange.getText()));
    }
}
