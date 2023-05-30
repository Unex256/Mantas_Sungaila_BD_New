package com.example.mantas_sungaila_bd_fx.model;

import com.example.mantas_sungaila_bd_fx.view.Arrow;
import com.example.mantas_sungaila_bd_fx.view.CustomNode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class MainModel {
    private final IntegerProperty selectedId = new SimpleIntegerProperty(0);
    private final IntegerProperty secondarySelectedId = new SimpleIntegerProperty(0);

    private int arrayItemId;
    private ArrayList<Object> objectList = new ArrayList<>();
    private ArrayList<Influence> influenceList = new ArrayList<>();
    private ArrayList<Connection> connectionList = new ArrayList<>();
    private ArrayList<CustomNode> shapeList = new ArrayList<>();
    private HashMap<Integer, Arrow> arrowList = new HashMap<>();
    private int arrowId = 0;

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


    public IntegerProperty getSelectedId() {
        return selectedId;
    }

    public IntegerProperty getSecondarySelectedId() {
        return secondarySelectedId;
    }


    public int getArrayItemId() {
        return arrayItemId;
    }

    public void setArrayItemId(int arrayItemId) {
        this.arrayItemId = arrayItemId;
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

    public ArrayList<CustomNode> getShapeList() {
        return shapeList;
    }

    public CustomNode getShapeListItemById(int id) {
        for (CustomNode node : shapeList) {
            if (node.getNodeId()== id) {
                return node;
            }
        }
        return null;
    }

    public void setShapeList(ArrayList<CustomNode> shapeList) {
        this.shapeList = shapeList;
    }

    public HashMap<Integer, Arrow> getArrowList() {
        return arrowList;
    }

    public void setArrowList(HashMap<Integer, Arrow> arrowList) {
        this.arrowList = arrowList;
    }

    public int getArrowId() {
        return arrowId;
    }

    public void setArrowId(int arrowId) {
        this.arrowId = arrowId;
    }

    public void increaseLineId() {
        this.arrowId++;
    }
}
