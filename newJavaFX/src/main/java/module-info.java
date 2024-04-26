module org.example.newjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.newjavafx to javafx.fxml;
    exports org.example.newjavafx;
}