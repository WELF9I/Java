module org.example.registrationform {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.registrationform to javafx.fxml;
    exports org.example.registrationform;
}