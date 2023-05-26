package com.example.mantas_sungaila_bd_fx.model;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class MainModel {
    private static Label staticSelectedIdLabel;
    public static int selectedId = 0;
    public static int secondarySelectedId = 0;
    public static int staticArrayItemId;
    public static Label staticStepCountLabel;
    public static ArrayList<Object> objectList = new ArrayList<>();
    public static ArrayList<Influence> influenceList = new ArrayList<>();
    public static ArrayList<Connection> connectionList = new ArrayList<>();
    public static ArrayList<Shape> shapeList = new ArrayList<>();
    public static ArrayList<Line> lineList = new ArrayList<>();
    public static int staticLineId = 0;
    public static Label staticSecondarySelectedIdLabel;

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
}
