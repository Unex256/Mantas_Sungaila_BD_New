package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Connection;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;

public class DraggableSelection {

    MainModel model;
    private double mouseAnchorX;
    private double mouseAnchorY;

    public DraggableSelection(MainModel model) {
        this.model = model;
    }

    public void makeDraggable(Node node){

        node.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                mouseAnchorX = mouseEvent.getX();
                mouseAnchorY = mouseEvent.getY();
                model.getSelectedId().set(Integer.parseInt(node.getId()));
                //System.out.println("Selected primary node id:");
                //System.out.println(model.getSelectedId().get());
            } else {
                model.getSecondarySelectedId().set(Integer.parseInt(node.getId()));
                //System.out.println("Selected secondary node id:");
                //System.out.println(model.getSelectedId().get());
            }
        });

        node.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX - 3.0);
                node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY - 29.0);
                lineUpdate(node);
            }
        });

    }


    public void lineUpdate(Node node){
        for(Connection connection : model.getConnectionList()){
            if(connection.getConnectedElementIds()[0] == model.getSelectedId().get()){
                model.getArrowList().get(connection.getLineId()).setStartX(new Center(node).centerXProperty().doubleValue());
                model.getArrowList().get(connection.getLineId()).setStartY(new Center(node).centerYProperty().doubleValue());
            } else if (connection.getConnectedElementIds()[1] == model.getSelectedId().get()){
                model.getArrowList().get(connection.getLineId()).setEndX(new Center(node).centerXProperty().doubleValue());
                model.getArrowList().get(connection.getLineId()).setEndY(new Center(node).centerYProperty().doubleValue());
            }
        }
    }

}
