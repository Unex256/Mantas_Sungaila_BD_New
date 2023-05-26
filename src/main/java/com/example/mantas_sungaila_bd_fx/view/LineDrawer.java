package com.example.mantas_sungaila_bd_fx.view;
import com.example.mantas_sungaila_bd_fx.model.Center;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class LineDrawer {
    //final Line line = new Line();

        public LineDrawer(){
        }

        public void drawLine (Center center1, Center center2){
            MainModel.lineList.add(new Line());
            MainModel.lineList.get(MainModel.staticLineId).setStartX(center1.centerXProperty().doubleValue());
            MainModel.lineList.get(MainModel.staticLineId).setStartY(center1.centerYProperty().doubleValue());
            MainModel.lineList.get(MainModel.staticLineId).setEndX(center2.centerXProperty().doubleValue());
            MainModel.lineList.get(MainModel.staticLineId).setEndY(center2.centerYProperty().doubleValue());
            ((AnchorPane) MainModel.shapeList.get(1).getParent()).getChildren().add(MainModel.lineList.get(MainModel.staticLineId));
            MainModel.staticLineId++;
        }

}
