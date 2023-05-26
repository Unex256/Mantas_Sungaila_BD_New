package com.example.mantas_sungaila_bd_fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ObjectOptions implements Initializable{

    public TextArea objDescriptionTextArea;
    public TextField exitChance;
    public TextField riskChance;
    public Label exitCountLabel;
    public Label riskCountLabel;
    public TextField objNameTextArea;
    public Label idLabel;
    public Button confirmBtn;

    public CheckBox beginningNodeCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Main.getStaticArrayItemId());
        this.idLabel.setText(Integer.toString(Main.selectedId));
        this.objDescriptionTextArea.setText(Main.objectList.get(Main.staticArrayItemId).getDescription());
        this.exitChance.setText(Float.toString(Main.objectList.get(Main.staticArrayItemId).getExitChance()));
        this.riskChance.setText(Float.toString(Main.objectList.get(Main.staticArrayItemId).getRiskChance()));
        this.exitCountLabel.setText(Integer.toString(Main.objectList.get(Main.staticArrayItemId).getExitCount()));
        this.riskCountLabel.setText(Integer.toString(Main.objectList.get(Main.staticArrayItemId).getRiskCount()));
        this.objNameTextArea.setText(Main.objectList.get(Main.staticArrayItemId).getObjName());
        this.beginningNodeCheckBox.setSelected(Main.objectList.get(Main.staticArrayItemId).isBeginningNode());
    }

    public void onConfirmBtnPress(ActionEvent event) {
        Main.objectList.get(Main.staticArrayItemId).setDescription(objDescriptionTextArea.getText());
        Main.objectList.get(Main.staticArrayItemId).setExitChance(Float.parseFloat(exitChance.getText()));
        Main.objectList.get(Main.staticArrayItemId).setObjName(objNameTextArea.getText());
        Main.objectList.get(Main.staticArrayItemId).setRiskChance(Float.parseFloat(riskChance.getText()));
        Main.objectList.get(Main.staticArrayItemId).resetChances();

    }

    public void checkBoxCheck(ActionEvent event){
        if(beginningNodeCheckBox.isSelected()){
            Main.objectList.get(Main.staticArrayItemId).setBeginningNode(true);
        } else {
            Main.objectList.get(Main.staticArrayItemId).setBeginningNode(false);
        }
    }
}
