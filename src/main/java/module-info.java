module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.commons.lang3;
    requires json.simple;

    opens de.hdm_stuttgart_mi to javafx.fxml;
    opens de.hdm_stuttgart_mi.Controller to javafx.fxml;
    exports de.hdm_stuttgart_mi.Controller;
    exports de.hdm_stuttgart_mi;
}