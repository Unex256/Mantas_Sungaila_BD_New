package com.example.mantas_sungaila_bd_fx.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CustomNode extends Pane {
    private Rectangle rectangle;
    private Label idLabel;

    private final ImageView imageView;

    private ScaleTransition scaleTransition;
    private Timeline colorTransition;
    private final int id;

    public CustomNode(int id, String color) {
        this.id = id;
        rectangle = new Rectangle(50, 50);
        rectangle.setStroke(Color.BLACK);
        switch (color) {
            case "CYAN" -> rectangle.setFill(Color.CYAN);
            case "SANDYBROWN" -> rectangle.setFill(Color.SANDYBROWN);
        }

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setColor(Color.BLACK);
        rectangle.setEffect(dropShadow);

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

    public void animateNode() {
        scaleTransition = new ScaleTransition(Duration.millis(2000), rectangle);
        scaleTransition.setFromX(0.95);
        scaleTransition.setFromY(0.95);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        DropShadow dropShadow = (DropShadow) rectangle.getEffect();

        colorTransition = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(dropShadow.colorProperty(), Color.BLACK)),
                new KeyFrame(Duration.millis(1000), new KeyValue(dropShadow.colorProperty(), Color.TRANSPARENT)),
                new KeyFrame(Duration.millis(2000), new KeyValue(dropShadow.colorProperty(), Color.BLACK))
        );
        colorTransition.setAutoReverse(true);
        colorTransition.play();
    }

    public void errorAnimation(String color, int simulationSpeed) {
        int duration = 800 / simulationSpeed;

        if (duration < 40){
            return;
        }

        Color chosenColor;
        switch (color.toUpperCase()) {
            case "RED" -> chosenColor = Color.RED;
            case "BLUE" -> chosenColor = Color.BLUE;
            case "GREEN" -> chosenColor = Color.GREEN;
            // add other colors if needed
            default -> chosenColor = Color.BLACK;
        }

        Color originalColor = (Color) rectangle.getFill();
        DropShadow dropShadow = (DropShadow) rectangle.getEffect();
        Color originalShadowColor = dropShadow.getColor();
        double originalOpacity = imageView.getOpacity();

        Timeline timeline = new Timeline();

        KeyFrame startFrame = new KeyFrame(Duration.ZERO,
                new KeyValue(rectangle.fillProperty(), chosenColor),
                new KeyValue(dropShadow.colorProperty(), chosenColor),
                new KeyValue(imageView.opacityProperty(), 0)); // start the imageView as transparent
        timeline.getKeyFrames().add(startFrame);

        KeyFrame endFrame = new KeyFrame(Duration.millis(duration),
                new KeyValue(rectangle.fillProperty(), originalColor),
                new KeyValue(dropShadow.colorProperty(), originalShadowColor),
                new KeyValue(imageView.opacityProperty(), originalOpacity)); // restore the imageView's original opacity
        timeline.getKeyFrames().add(endFrame);

        timeline.setAutoReverse(true); // makes the animation automatically reverse direction each cycle
        timeline.play();
    }
}
