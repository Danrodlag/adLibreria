module proyecto.adbd {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens proyecto.adbd1 to javafx.fxml;
    exports proyecto.adbd1;
}