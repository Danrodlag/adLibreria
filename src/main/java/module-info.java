module proyecto.adbd {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.zaxxer.hikari;

    opens proyecto.erp to javafx.fxml;
    exports proyecto.erp;
}