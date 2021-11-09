module com.example.triviamaze {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.triviamaze to javafx.fxml;
    exports com.example.triviamaze;
}