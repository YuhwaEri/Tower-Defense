package com.group7.view;

import com.group7.controller.TowerDefenseController;
import com.group7.model.TowerDefenseModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TowerDefenseView extends Application{

    // Program Constants
    static final double WINDOW_HEIGHT = 1080;
    static final double WINDOW_WIDTH = 1920;

    private boolean pushTest = false;

    // fields
    private BorderPane viewHandler;
    private boolean isInGame = false;

    // model and controller
    private TowerDefenseModel model;
    private TowerDefenseController controller;



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
