module org.example.tp3javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tp3javafx to javafx.fxml;
    exports org.example.tp3javafx;
}