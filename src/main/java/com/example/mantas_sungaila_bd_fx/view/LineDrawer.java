package com.example.mantas_sungaila_bd_fx.view;
import com.example.mantas_sungaila_bd_fx.controller.Center;
import com.example.mantas_sungaila_bd_fx.model.MainModel;
import javafx.scene.layout.AnchorPane;

public class LineDrawer {
    //final Line line = new Line();
        MainModel model;
        public LineDrawer(MainModel model){
            this.model = model;
        }

        public void drawLine(Center center1, Center center2, int lineId){
            Arrow arrow = new Arrow(center1.centerXProperty().doubleValue(), center1.centerYProperty().doubleValue(), center2.centerXProperty().doubleValue(), center2.centerYProperty().doubleValue());
            model.getArrowList().put(lineId, arrow);
            AnchorPane anchorPane = ((AnchorPane) model.getShapeList().get(1).getParent());
            anchorPane.getChildren().add(arrow);
            arrow.toBack();
            model.increaseLineId();
            arrow.animateArrow();
        }
}
