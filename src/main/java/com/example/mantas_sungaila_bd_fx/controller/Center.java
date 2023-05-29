package com.example.mantas_sungaila_bd_fx.controller;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.geometry.Bounds;
import javafx.scene.Node;

public class Center {
    private final ReadOnlyDoubleWrapper centerX = new ReadOnlyDoubleWrapper();
    private final ReadOnlyDoubleWrapper centerY = new ReadOnlyDoubleWrapper();

    public Center(Node node) {
        calcCenter(node.getBoundsInParent());
        node.boundsInParentProperty().addListener((observableValue, oldBounds, bounds) -> calcCenter(bounds));
    }

    private void calcCenter(Bounds bounds) {
        centerX.set(bounds.getMinX() + bounds.getWidth()  / 2);
        centerY.set(bounds.getMinY() + bounds.getHeight() / 2);
    }

    public ReadOnlyDoubleProperty centerXProperty() {
        return centerX.getReadOnlyProperty();
    }

    public ReadOnlyDoubleProperty centerYProperty() {
        return centerY.getReadOnlyProperty();
    }
}
