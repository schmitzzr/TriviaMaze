module com.triviamaze {
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires junit;
    requires javafx.controls;
    requires org.junit.platform.commons;
    requires org.apiguardian.api;

    opens com.triviamaze to javafx.fxml;
    exports com.triviamaze;
    exports com.triviamaze.maze;
    opens com.triviamaze.maze to javafx.fxml;
}