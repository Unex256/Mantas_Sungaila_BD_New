package com.example.mantas_sungaila_bd_fx.model;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class MainModel {
    private static Label staticSelectedIdLabel;
    private static int selectedId = 0;
    private static int secondarySelectedId = 0;
    private static int staticArrayItemId;
    private static Label staticStepCountLabel;
    private static ArrayList<Object> objectList = new ArrayList<>();
    private static ArrayList<Influence> influenceList = new ArrayList<>();
    private static ArrayList<Connection> connectionList = new ArrayList<>();
    private static ArrayList<Shape> shapeList = new ArrayList<>();
    private static ArrayList<Line> lineList = new ArrayList<>();
    private static int staticLineId = 0;
    private static Label staticSecondarySelectedIdLabel;

    public static void setSelectedId(int id) {
        selectedId = id;
        //System.out.println(this.selectedIdLabel.getText());
    }

    public static void setSecondarySelectedId(int secondarySelectedId) {
        MainModel.secondarySelectedId = secondarySelectedId;
    }

    public static int getStaticArrayItemId() {
        return staticArrayItemId;
    }

    public static int[] returnSelectedListItem(int selectedId){

        if (selectedId % 2 == 1) {
            staticArrayItemId = 0;
            for (Object object : objectList) {
                if(object.getId() == selectedId){
                    return new int[]{1, staticArrayItemId};
                }
               staticArrayItemId++;
            }
        }else {
            staticArrayItemId = 0;
            for (Influence influence : influenceList) {
                if(influence.getId() == selectedId){
                    return new int[]{2, staticArrayItemId};
                }
                staticArrayItemId++;
            }
        }
            return new int[] {0,0};
    }

    public static Label getStaticSelectedIdLabel() {
        return staticSelectedIdLabel;
    }

    public static void setStaticSelectedIdLabel(Label staticSelectedIdLabel) {
        MainModel.staticSelectedIdLabel = staticSelectedIdLabel;
    }

    public static int getSelectedId() {
        return selectedId;
    }

    public static int getSecondarySelectedId() {
        return secondarySelectedId;
    }

    public static void setStaticArrayItemId(int staticArrayItemId) {
        MainModel.staticArrayItemId = staticArrayItemId;
    }

    public static Label getStaticStepCountLabel() {
        return staticStepCountLabel;
    }

    public static void setStaticStepCountLabel(Label staticStepCountLabel) {
        MainModel.staticStepCountLabel = staticStepCountLabel;
    }

    public static ArrayList<Object> getObjectList() {
        return objectList;
    }

    public static void setObjectList(ArrayList<Object> objectList) {
        MainModel.objectList = objectList;
    }

    public static ArrayList<Influence> getInfluenceList() {
        return influenceList;
    }

    public static void setInfluenceList(ArrayList<Influence> influenceList) {
        MainModel.influenceList = influenceList;
    }

    public static ArrayList<Connection> getConnectionList() {
        return connectionList;
    }

    public static void setConnectionList(ArrayList<Connection> connectionList) {
        MainModel.connectionList = connectionList;
    }

    public static ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public static void setShapeList(ArrayList<Shape> shapeList) {
        MainModel.shapeList = shapeList;
    }

    public static ArrayList<Line> getLineList() {
        return lineList;
    }

    public static void setLineList(ArrayList<Line> lineList) {
        MainModel.lineList = lineList;
    }

    public static int getStaticLineId() {
        return staticLineId;
    }

    public static void setStaticLineId(int staticLineId) {
        MainModel.staticLineId = staticLineId;
    }

    public static Label getStaticSecondarySelectedIdLabel() {
        return staticSecondarySelectedIdLabel;
    }

    public static void setStaticSecondarySelectedIdLabel(Label staticSecondarySelectedIdLabel) {
        MainModel.staticSecondarySelectedIdLabel = staticSecondarySelectedIdLabel;
    }

    public static void increaseStaticLineId() {
        staticLineId++;
    }
}
