package com.example.mantas_sungaila_bd_fx.model;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class MainModel {
    private Label selectedIdLabel;
    private int selectedId = 0;
    private int secondarySelectedId = 0;
    private int arrayItemId;
    private Label stepCountLabel;
    private ArrayList<Object> objectList = new ArrayList<>();
    private ArrayList<Influence> influenceList = new ArrayList<>();
    private ArrayList<Connection> connectionList = new ArrayList<>();
    private ArrayList<Shape> shapeList = new ArrayList<>();
    private ArrayList<Line> lineList = new ArrayList<>();
    private int lineId = 0;
    private Label secondarySelectedIdLabel;

    public int[] returnSelectedListItem(int selectedId){

        if (selectedId % 2 == 1) {
            arrayItemId = 0;
            for (Object object : objectList) {
                if(object.getId() == selectedId){
                    return new int[]{1, arrayItemId};
                }
               arrayItemId++;
            }
        }else {
            arrayItemId = 0;
            for (Influence influence : influenceList) {
                if(influence.getId() == selectedId){
                    return new int[]{2, arrayItemId};
                }
                arrayItemId++;
            }
        }
            return new int[] {0,0};
    }

    public Label getSelectedIdLabel() {
        return selectedIdLabel;
    }

    public void setSelectedIdLabel(Label selectedIdLabel) {
        this.selectedIdLabel = selectedIdLabel;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getSecondarySelectedId() {
        return secondarySelectedId;
    }

    public void setSecondarySelectedId(int secondarySelectedId) {
        this.secondarySelectedId = secondarySelectedId;
    }

    public int getArrayItemId() {
        return arrayItemId;
    }

    public void setArrayItemId(int arrayItemId) {
        this.arrayItemId = arrayItemId;
    }

    public Label getStepCountLabel() {
        return stepCountLabel;
    }

    public void setStepCountLabel(Label stepCountLabel) {
        this.stepCountLabel = stepCountLabel;
    }

    public ArrayList<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<Object> objectList) {
        this.objectList = objectList;
    }

    public ArrayList<Influence> getInfluenceList() {
        return influenceList;
    }

    public void setInfluenceList(ArrayList<Influence> influenceList) {
        this.influenceList = influenceList;
    }

    public ArrayList<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(ArrayList<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(ArrayList<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public ArrayList<Line> getLineList() {
        return lineList;
    }

    public void setLineList(ArrayList<Line> lineList) {
        this.lineList = lineList;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public Label getSecondarySelectedIdLabel() {
        return secondarySelectedIdLabel;
    }

    public void setSecondarySelectedIdLabel(Label secondarySelectedIdLabel) {
        this.secondarySelectedIdLabel = secondarySelectedIdLabel;
    }

    public void increaseStaticLineId() {
        this.lineId++;
    }
}
