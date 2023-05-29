package com.example.mantas_sungaila_bd_fx.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawingCanvas extends Canvas {

    private final GraphicsContext gc;

    public DrawingCanvas() {
        super();
        gc = getGraphicsContext2D();
        gc.setFill(Color.CYAN); // background color
        gc.setStroke(Color.BLACK); // color for drawing lines

        widthProperty().addListener((obs, oldVal, newVal) -> fillBackground(newVal.doubleValue(), getHeight()));
        heightProperty().addListener((obs, oldVal, newVal) -> fillBackground(getWidth(), newVal.doubleValue()));


        setOnMouseDragged(this::handleMouseDragged);
        setOnMousePressed(this::handleMousePressed);
    }

    private void fillBackground(double width, double height) {
        gc.fillRect(0, 0, width, height);
    }

    private void handleMouseDragged(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        gc.lineTo(x, y);
        gc.stroke();
    }

    private void handleMousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        gc.beginPath();
        gc.moveTo(x, y);
        gc.stroke();
    }

    public void setBackgroundColor(Color color) {
        gc.setFill(color);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }
}