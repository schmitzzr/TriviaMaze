module com.triviamaze {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.triviamaze to javafx.fxml;
    exports com.triviamaze;
}