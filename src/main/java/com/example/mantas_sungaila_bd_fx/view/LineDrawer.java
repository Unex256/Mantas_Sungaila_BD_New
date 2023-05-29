package com.example.mantas_sungaila_bd_fx.view;
import com.example.mantas_sungaila_bd_fx.controller.Center;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class LineDrawer {
    //final Line line = new Line();
        MainModel model;
        public LineDrawer(MainModel model){
            this.model = model;
        }

        public void drawLine (Center center1, Center center2){
            model.getLineList().add(new Line());
            model.getLineList().get(model.getLineId()).setStartX(center1.centerXProperty().doubleValue());
            model.getLineList().get(model.getLineId()).setStartY(center1.centerYProperty().doubleValue());
            model.getLineList().get(model.getLineId()).setEndX(center2.centerXProperty().doubleValue());
            model.getLineList().get(model.getLineId()).setEndY(center2.centerYProperty().doubleValue());
            ((AnchorPane) model.getShapeList().get(1).getParent()).getChildren().add(model.getLineList().get(model.getLineId()));
            model.increaseStaticLineId();
        }

}
