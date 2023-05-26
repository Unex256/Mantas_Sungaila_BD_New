package com.example.mantas_sungaila_bd_fx.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class StartPage {

    public Button newProjectBtn;
    @FXML
    protected void onNewProjectButtonClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/mantas_sungaila_bd_fx/main-page.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("CyberSocial");
        stage.setScene(scene);
        stage.show();
    }

    public void onExistingProjectButtonClick(ActionEvent actionEvent) {

    }
}