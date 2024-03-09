module sn.ismonline.personneapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sn.ismonline.personneapp to javafx.fxml;
    exports sn.ismonline.personneapp;
}