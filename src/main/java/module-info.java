module com.example.mantas_sungaila_bd_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;

    opens com.example.mantas_sungaila_bd_fx to javafx.fxml;
    exports com.example.mantas_sungaila_bd_fx.view;
    opens com.example.mantas_sungaila_bd_fx.view to javafx.fxml;
    exports com.example.mantas_sungaila_bd_fx.controller;
    opens com.example.mantas_sungaila_bd_fx.controller to javafx.fxml;
    exports com.example.mantas_sungaila_bd_fx.model;
    opens com.example.mantas_sungaila_bd_fx.model to javafx.fxml;
}