package com.example.mantas_sungaila_bd_fx.model;

import java.util.ArrayList;

public class Object {
    public int id;
    private int shapeId;
    public int type = 1;

    public boolean beginningNode = false;

    public String objName = "Obj Name";

    public String description = "Obj description";

    public ArrayList<Integer> connections = new ArrayList<>();
    public float exitChance = 1;

    public float adjustedExitChance = exitChance;

    public int exitCount = 0;

    public float riskChance = 0;

    public float adjustedRiskChance = riskChance;

    public int riskCount = 0;

    public int pulseCount = 0;


    public Object(int id, int shapeId) {
        this.id = id;
        this.shapeId = shapeId;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }

    public void addConnection(int connection, int arrowId) {
        this.connections.add(connection, arrowId);
    }

    public float getExitChance() {
        return exitChance;
    }

    public void setExitChance(float exitChance) {
        this.exitChance = exitChance;
    }

    public int getExitCount() {
        return exitCount;
    }

    public void setExitCount(int exitCount) {
        this.exitCount = exitCount;
    }

    public float getRiskChance() {
        return riskChance;
    }

    public void setRiskChance(float riskChance) {
        this.riskChance = riskChance;
    }

    public int getRiskCount() {
        return riskCount;
    }

    public void setRiskCount(int riskCount) {
        this.riskCount = riskCount;
    }

    public boolean isBeginningNode() {
        return beginningNode;
    }

    public void setBeginningNode(boolean beginningNode) {
        this.beginningNode = beginningNode;
    }

    public int getShapeId() {
        return shapeId;
    }


    public float getAdjustedExitChance() {
        return adjustedExitChance;
    }

    public void adjustExitChance(float adjustment) {
        this.adjustedExitChance = adjustedExitChance + adjustment;
    }

    public float getAdjustedRiskChance() {
        return adjustedRiskChance;
    }

    public void adjustRiskChance(float riskChanceAdjustment) {
        this.adjustedRiskChance = adjustedRiskChance + riskChanceAdjustment;
    }

    public int getPulseCount() {
        return pulseCount;
    }

    public void setPulseCount(int pulseCount) {
        this.pulseCount = pulseCount;
    }

    public void resetChances() {
        this.adjustedRiskChance = riskChance;
        this.adjustedExitChance = exitChance;
    }


    public void increaseRiskCount() {
        this.riskCount++;
    }
}
