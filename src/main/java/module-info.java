module com.group7 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.group7 to javafx.fxml;
    exports com.group7;
    exports com.group7.view;
    // Remove the comments when folder contains files.
    //exports com.group7.model;
    //exports com.group7.controller;
}
