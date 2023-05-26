package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.MainModel;
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
        System.out.println(MainModel.getStaticArrayItemId());
        this.idLabel.setText(Integer.toString(MainModel.selectedId));
        this.objDescriptionTextArea.setText(MainModel.objectList.get(MainModel.staticArrayItemId).getDescription());
        this.exitChance.setText(Float.toString(MainModel.objectList.get(MainModel.staticArrayItemId).getExitChance()));
        this.riskChance.setText(Float.toString(MainModel.objectList.get(MainModel.staticArrayItemId).getRiskChance()));
        this.exitCountLabel.setText(Integer.toString(MainModel.objectList.get(MainModel.staticArrayItemId).getExitCount()));
        this.riskCountLabel.setText(Integer.toString(MainModel.objectList.get(MainModel.staticArrayItemId).getRiskCount()));
        this.objNameTextArea.setText(MainModel.objectList.get(MainModel.staticArrayItemId).getObjName());
        this.beginningNodeCheckBox.setSelected(MainModel.objectList.get(MainModel.staticArrayItemId).isBeginningNode());
    }

    public void onConfirmBtnPress() {
        MainModel.objectList.get(MainModel.staticArrayItemId).setDescription(objDescriptionTextArea.getText());
        MainModel.objectList.get(MainModel.staticArrayItemId).setExitChance(Float.parseFloat(exitChance.getText()));
        MainModel.objectList.get(MainModel.staticArrayItemId).setObjName(objNameTextArea.getText());
        MainModel.objectList.get(MainModel.staticArrayItemId).setRiskChance(Float.parseFloat(riskChance.getText()));
        MainModel.objectList.get(MainModel.staticArrayItemId).resetChances();

    }

    public void checkBoxCheck(){
        MainModel.objectList.get(MainModel.staticArrayItemId).setBeginningNode(beginningNodeCheckBox.isSelected());
    }
}
