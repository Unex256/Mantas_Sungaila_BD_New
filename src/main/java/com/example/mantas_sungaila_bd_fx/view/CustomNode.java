package com.example.mantas_sungaila_bd_fx.view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomNode extends Pane {
    private Rectangle rectangle;
    private Label idLabel;

    private final ImageView imageView;

    private final int id;

    public CustomNode(int id, String color) {
        this.id = id;
        rectangle = new Rectangle(50, 50);
        rectangle.setStroke(Color.BLACK);
        switch (color) {
            case "CYAN" -> rectangle.setFill(Color.CYAN);
            case "SANDYBROWN" -> rectangle.setFill(Color.SANDYBROWN);
        }
        idLabel = new Label(Integer.toString(id));
        if (id < 10) {
            idLabel.setLayoutX(50.0 * 4/5);
        } else {
            idLabel.setLayoutX(50.0 * 2/3);
        }
        idLabel.setLayoutY(50.0 * 2/3);

        imageView = new ImageView();
        imageView.setFitWidth(48);  // adjust these values as needed
        imageView.setFitHeight(48);
        imageView.setLayoutX(1);
        imageView.setLayoutY(1);
        this.getChildren().addAll(rectangle, imageView, idLabel);
        this.setId(Integer.toString(id));
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public int getNodeId(){
        return id;
    }
}
