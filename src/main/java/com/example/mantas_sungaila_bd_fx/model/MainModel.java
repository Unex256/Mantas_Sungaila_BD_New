package com.example.mantas_sungaila_bd_fx.model;

import com.example.mantas_sungaila_bd_fx.view.Arrow;
import com.example.mantas_sungaila_bd_fx.view.CustomNode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.HashMap;

public class MainModel {
    private final IntegerProperty selectedId = new SimpleIntegerProperty(0);
    private final IntegerProperty secondarySelectedId = new SimpleIntegerProperty(0);

    private int arrayItemId;
    private HashMap<Integer, GenericObject> objectList = new HashMap<>();
    private HashMap<Integer, Influence> influenceList = new HashMap<>();
    private HashMap<Integer, Connection> connectionList = new HashMap<>();
    private HashMap<Integer, CustomNode> shapeList = new HashMap<>();
    private HashMap<Integer, Arrow> arrowList = new HashMap<>();
    private int arrowId = 0;

    public java.lang.Object returnSelectedListItem(int selectedId){
        if (selectedId % 2 == 1) {
            return getObjectListItemById(selectedId);
        }else {
            return getInfluenceListItemById(selectedId);
        }
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

    public HashMap<Integer, GenericObject> getObjectList() {
        return objectList;
    }


    public GenericObject getObjectListItemById(int id) {
        return objectList.get(id);
    }
    public HashMap<Integer, Influence> getInfluenceList() {
        return influenceList;
    }


    public Influence getInfluenceListItemById(int id) {
        return influenceList.get(id);
    }

    public HashMap<Integer, Connection> getConnectionList() {
        return connectionList;
    }

    public HashMap<Integer, CustomNode> getShapeList() {
        return shapeList;
    }

    public CustomNode getShapeListItemById(int id) {
        return shapeList.get(id);
    }


    public HashMap<Integer, Arrow> getArrowList() {
        return arrowList;
    }

    public Arrow getArrowById(int id) {
        return arrowList.get(id);
    }

    public int getArrowId() {
        return arrowId;
    }


    public void increaseArrowId() {
        this.arrowId++;
    }
}
