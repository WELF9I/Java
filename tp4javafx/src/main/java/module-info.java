module org.example.tp4javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.tp4javafx to javafx.fxml;
    exports org.example.tp4javafx;
}