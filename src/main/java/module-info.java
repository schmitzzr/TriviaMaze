module com.triviamaze {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.junit.jupiter.api;
    requires junit;
    requires javafx.media;


    opens com.triviamaze to javafx.fxml;
    exports com.triviamaze;
    exports com.triviamaze.maze;
    opens com.triviamaze.maze to javafx.fxml;
}