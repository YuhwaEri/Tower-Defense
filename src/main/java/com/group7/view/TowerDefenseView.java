package com.group7.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TowerDefenseView extends Application{

    // Program Constants
    static final double WINDOW_HEIGHT = 1080;
    static final double WINDOW_WIDTH = 1920;

    // fields
    private BorderPane viewHandler;
    private boolean isInGame = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TD");
        // initializing the internal classes
        viewHandler = new BorderPane();
        Scene scene = new Scene(viewHandler, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
