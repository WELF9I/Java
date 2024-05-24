module org.example.examensujet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ui.controllers to javafx.fxml;
    opens org.example.examensujet to javafx.fxml;
    exports org.example.examensujet;
    exports ui.controllers;
    exports ui.views;
    exports model;
    exports dao;
    exports service;
}