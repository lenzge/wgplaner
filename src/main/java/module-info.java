module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens de.hdm_stuttgart_mi to javafx.fxml;
    exports de.hdm_stuttgart_mi;
}