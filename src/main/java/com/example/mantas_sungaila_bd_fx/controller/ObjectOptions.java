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
        this.idLabel.setText(Integer.toString(MainModel.getSelectedId()));
        this.objDescriptionTextArea.setText(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).getDescription());
        this.exitChance.setText(Float.toString(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).getExitChance()));
        this.riskChance.setText(Float.toString(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).getRiskChance()));
        this.exitCountLabel.setText(Integer.toString(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).getExitCount()));
        this.riskCountLabel.setText(Integer.toString(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).getRiskCount()));
        this.objNameTextArea.setText(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).getObjName());
        this.beginningNodeCheckBox.setSelected(MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).isBeginningNode());
    }

    public void onConfirmBtnPress() {
        MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).setDescription(objDescriptionTextArea.getText());
        MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).setExitChance(Float.parseFloat(exitChance.getText()));
        MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).setObjName(objNameTextArea.getText());
        MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).setRiskChance(Float.parseFloat(riskChance.getText()));
        MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).resetChances();

    }

    public void checkBoxCheck(){
        MainModel.getObjectList().get(MainModel.getStaticArrayItemId()).setBeginningNode(beginningNodeCheckBox.isSelected());
    }
}
