module org.example.examensujet3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.examensujet3 to javafx.fxml;
    exports org.example.examensujet3;
}