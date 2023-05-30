package com.example.mantas_sungaila_bd_fx.model;

import com.example.mantas_sungaila_bd_fx.controller.Center;
import com.example.mantas_sungaila_bd_fx.view.LineDrawer;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Connection implements Initializable {
    MainModel model;
    private final int[] connectedElementIds;
    private final LineDrawer lineDrawer;
    private final int lineId;
    private Center center1;
    private Center center2;

    public Connection(int a, int b, int lineId, MainModel model){
        this.model = model;
        this.lineDrawer = new LineDrawer(model);
        this.connectedElementIds = new int[]{a , b};
        this.lineId = lineId;
        switch (model.returnSelectedListItem(connectedElementIds[0])[0]) {
            case 1 -> center1 = new Center(model.getShapeList().get(model.getObjectList().get(
                    model.returnSelectedListItem(connectedElementIds[0])[1]).getShapeId()));
            case 2 -> center1 = new Center(model.getShapeList().get(model.getInfluenceList().get(
                    model.returnSelectedListItem(connectedElementIds[0])[1]).getShapeId()));
        }
        switch (model.returnSelectedListItem(connectedElementIds[1])[0]) {
            case 1 -> {
                center2 = new Center(model.getShapeList().get(model.getObjectList().get(
                        model.returnSelectedListItem(connectedElementIds[1])[1]).getShapeId()));
                lineDrawer.drawLine(center1, center2, lineId);
            }
            case 2 -> {
                center2 = new Center(model.getShapeList().get(model.getInfluenceList().get(
                        model.returnSelectedListItem(connectedElementIds[1])[1]).getShapeId()));
                lineDrawer.drawLine(center1, center2, lineId);

            }

        }
    }

    public int[] getConnectedElementIds() {
        return connectedElementIds;
    }

    public int getLineId() {
        return lineId;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch (model.returnSelectedListItem(connectedElementIds[0])[0]) {
            case 1 -> center1 = new Center(model.getShapeList().get(model.getObjectList().get(
                    model.returnSelectedListItem(connectedElementIds[0])[1]).getShapeId()));
            case 2 -> center1 = new Center(model.getShapeList().get(model.getInfluenceList().get(
                    model.returnSelectedListItem(connectedElementIds[0])[1]).getShapeId()));
        }
        switch (model.returnSelectedListItem(connectedElementIds[1])[0]) {
            case 1 -> {
                center2 = new Center(model.getShapeList().get(model.getObjectList().get(
                        model.returnSelectedListItem(connectedElementIds[1])[1]).getShapeId()));
                lineDrawer.drawLine(center1, center2, lineId);
            }
            case 2 -> {
                center2 = new Center(model.getShapeList().get(model.getInfluenceList().get(
                        model.returnSelectedListItem(connectedElementIds[1])[1]).getShapeId()));
                lineDrawer.drawLine(center1, center2, lineId);

            }

        }
    }
}
