package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Center;
import com.example.mantas_sungaila_bd_fx.model.Connection;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;

public class DraggableSelection {

    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node){

        node.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                mouseAnchorX = mouseEvent.getX();
                mouseAnchorY = mouseEvent.getY();
                Main.setSelectedId(Integer.parseInt(node.getId()));
                Main.staticSelectedIdLabel.setText(node.getId());
                //new LineDrawer(node);
            } else {
                Main.setSecondarySelectedId(Integer.parseInt(node.getId()));
                Main.staticSecondarySelectedIdLabel.setText(node.getId());
                //MainPage.staticSelectedIdLabel.setText(node.getId());
            }
        });

        node.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
                node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
                lineUpdate(node);
            }
        });

    }

    public void lineUpdate(Node node){
        for(Connection connection : Main.connectionList){
            if(connection.getConnectedElementIds()[0] == Main.selectedId){
                Main.lineList.get(connection.getLineId()).setStartX(new Center(node).centerXProperty().doubleValue());
                Main.lineList.get(connection.getLineId()).setStartY(new Center(node).centerYProperty().doubleValue());
            } else if (connection.getConnectedElementIds()[1] == Main.selectedId){
                Main.lineList.get(connection.getLineId()).setEndX(new Center(node).centerXProperty().doubleValue());
                Main.lineList.get(connection.getLineId()).setEndY(new Center(node).centerYProperty().doubleValue());
            }

        }
    }
}
