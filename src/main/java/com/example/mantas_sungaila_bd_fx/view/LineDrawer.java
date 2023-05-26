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
            MainModel.getLineList().add(new Line());
            MainModel.getLineList().get(MainModel.getStaticLineId()).setStartX(center1.centerXProperty().doubleValue());
            MainModel.getLineList().get(MainModel.getStaticLineId()).setStartY(center1.centerYProperty().doubleValue());
            MainModel.getLineList().get(MainModel.getStaticLineId()).setEndX(center2.centerXProperty().doubleValue());
            MainModel.getLineList().get(MainModel.getStaticLineId()).setEndY(center2.centerYProperty().doubleValue());
            ((AnchorPane) MainModel.getShapeList().get(1).getParent()).getChildren().add(MainModel.getLineList().get(MainModel.getStaticLineId()));
            MainModel.increaseStaticLineId();
        }

}
