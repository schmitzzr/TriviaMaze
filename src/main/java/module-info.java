module com.triviamaze {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.triviamaze to javafx.fxml;
    exports com.triviamaze;
}