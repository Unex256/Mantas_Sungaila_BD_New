package com.example.mantas_sungaila_bd_fx.view;
import com.example.mantas_sungaila_bd_fx.model.Center;
import com.example.mantas_sungaila_bd_fx.controller.Main;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class LineDrawer {
    //final Line line = new Line();

        public LineDrawer(){
        }

        public void drawLine (Center center1, Center center2, int nodeId){
            Main.lineList.add(new Line());
            Main.lineList.get(Main.staticLineId).setStartX(center1.centerXProperty().doubleValue());
            Main.lineList.get(Main.staticLineId).setStartY(center1.centerYProperty().doubleValue());
            Main.lineList.get(Main.staticLineId).setEndX(center2.centerXProperty().doubleValue());
            Main.lineList.get(Main.staticLineId).setEndY(center2.centerYProperty().doubleValue());
            ((AnchorPane) Main.shapeList.get(1).getParent()).getChildren().add(Main.lineList.get(Main.staticLineId));
            Main.staticLineId++;
        }

}
