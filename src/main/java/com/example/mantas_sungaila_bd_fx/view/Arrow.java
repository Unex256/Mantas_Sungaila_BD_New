package com.example.mantas_sungaila_bd_fx.view;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

// Animation imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Arrow extends Group {
    private Line line;
    private Polygon arrowHead;

    public Arrow(double startX, double startY, double endX, double endY) {
        line = new Line(startX, startY, endX, endY);

        arrowHead = new Polygon();
        arrowHead.getPoints().addAll(0.0, 0.0, 10.0, 5.0, 0.0, 10.0);

        getChildren().add(line);
        getChildren().add(arrowHead);
        setEndX(endX);
        setEndY(endY);
    }

    public void setStartX(double startX) {
        line.setStartX(startX);
        updateArrowHeadPositionAndRotation();
    }

    public void setStartY(double startY) {
        line.setStartY(startY);
        updateArrowHeadPositionAndRotation();
    }

    public void setEndX(double endX) {
        line.setEndX(endX);
        updateArrowHeadPositionAndRotation();
    }

    public void setEndY(double endY) {
        line.setEndY(endY);
        updateArrowHeadPositionAndRotation();
    }

    private void updateArrowHeadPositionAndRotation() {
        double angle = Math.atan2((line.getEndY() - line.getStartY()), (line.getEndX() - line.getStartX()));
        arrowHead.setRotate(Math.toDegrees(angle));
        double arrowHeadWidth = 10.0;
        double arrowHeadHeight = 10.0;
        double threeQuartersX = line.getStartX() + 0.75 * (line.getEndX() - line.getStartX());
        double threeQuartersY = line.getStartY() + 0.75 * (line.getEndY() - line.getStartY());
        double dx = line.getEndX() - line.getStartX();
        double dy = line.getEndY() - line.getStartY();
        double length = Math.sqrt(dx * dx + dy * dy);
        double directionX = dx / length;
        double directionY = dy / length;
        double shiftX = 10 * directionX;
        double shiftY = 10 * directionY;
        arrowHead.setLayoutX(threeQuartersX - arrowHeadWidth / 2 - shiftX);
        arrowHead.setLayoutY(threeQuartersY - arrowHeadHeight / 2 - shiftY);
    }

    public void animateArrow(int simulationSpeed) {
        int duration = 800 / simulationSpeed;

        if (duration < 40){
            return;
        }

        Color originalLineColor = (Color) line.getStroke();
        Color originalArrowHeadColor = (Color) arrowHead.getFill();
        double originalStrokeWidth = line.getStrokeWidth();
        double originalArrowHeadScale = arrowHead.getScaleX(); // assuming arrowHead's scale in x and y directions are the same

        // Create a Timeline
        Timeline timeline = new Timeline();

        // Create a KeyFrame to change the color, width, and scale over 500ms
        KeyFrame startFrame = new KeyFrame(Duration.ZERO,
                new KeyValue(line.strokeProperty(), Color.RED),
                new KeyValue(line.strokeWidthProperty(), 5.0),
                new KeyValue(arrowHead.fillProperty(), Color.RED),
                new KeyValue(arrowHead.scaleXProperty(), 2.0), // make arrowHead 2 times larger
                new KeyValue(arrowHead.scaleYProperty(), 2.0));
        timeline.getKeyFrames().add(startFrame);

        // Create a KeyFrame to change the color, width, and scale back over the next 500ms
        KeyFrame endFrame = new KeyFrame(Duration.millis(duration),
                new KeyValue(line.strokeProperty(), originalLineColor),
                new KeyValue(line.strokeWidthProperty(), originalStrokeWidth),
                new KeyValue(arrowHead.fillProperty(), originalArrowHeadColor),
                new KeyValue(arrowHead.scaleXProperty(), originalArrowHeadScale),
                new KeyValue(arrowHead.scaleYProperty(), originalArrowHeadScale));
        timeline.getKeyFrames().add(endFrame);

        // Set the animation to play for 1 second total
        timeline.setCycleCount(1);

        // Play the animation
        timeline.play();
    }

}

