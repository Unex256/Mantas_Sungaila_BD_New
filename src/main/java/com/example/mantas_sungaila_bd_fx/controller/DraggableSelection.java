package com.example.mantas_sungaila_bd_fx.controller;

import com.example.mantas_sungaila_bd_fx.model.Center;
import com.example.mantas_sungaila_bd_fx.model.Connection;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
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
                MainModel.setSelectedId(Integer.parseInt(node.getId()));
                MainModel.getStaticSelectedIdLabel().setText(node.getId());
                //new LineDrawer(node);
            } else {
                MainModel.setSecondarySelectedId(Integer.parseInt(node.getId()));
                MainModel.getStaticSecondarySelectedIdLabel().setText(node.getId());
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
        for(Connection connection : MainModel.getConnectionList()){
            if(connection.getConnectedElementIds()[0] == MainModel.getSelectedId()){
                MainModel.getLineList().get(connection.getLineId()).setStartX(new Center(node).centerXProperty().doubleValue());
                MainModel.getLineList().get(connection.getLineId()).setStartY(new Center(node).centerYProperty().doubleValue());
            } else if (connection.getConnectedElementIds()[1] == MainModel.getSelectedId()){
                MainModel.getLineList().get(connection.getLineId()).setEndX(new Center(node).centerXProperty().doubleValue());
                MainModel.getLineList().get(connection.getLineId()).setEndY(new Center(node).centerYProperty().doubleValue());
            }

        }
    }
}
