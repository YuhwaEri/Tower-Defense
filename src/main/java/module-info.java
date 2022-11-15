module com.group7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.desktop;

    opens com.group7 to javafx.fxml;
    exports com.group7;
    exports com.group7.view;
    exports com.group7.model;
    exports com.group7.controller;
    exports com.group7.model.Monster;
    exports com.group7.model.Tower;
    exports com.group7.model.Map;
}
