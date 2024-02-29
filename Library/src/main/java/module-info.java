module sample.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;



    opens sample.library to javafx.fxml;
    exports sample.library;
}