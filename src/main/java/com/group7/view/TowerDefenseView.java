package com.group7.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.group7.controller.TowerDefenseController;
import com.group7.model.TowerDefenseModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class TowerDefenseView extends Application{

    static Image terrain = null;
    static Image monster = null;
    static Image tower = null;
    static Image bg = null;
    static Image placeTowerBtn = null;

    private final StackPane gameStack = new StackPane();

    // Program Constants
    static final double WINDOW_HEIGHT = 800;
    static final double WINDOW_WIDTH = 1200;

    // fields
    private BorderPane viewHandler;
    private GameView gameView;


    // model and controller
    private TowerDefenseController controller;



    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TowerDefense");
        // initializing the internal classes
        this.controller = new TowerDefenseController(new TowerDefenseModel());
        loadSprites();
        gameView = new GameView(this, gameStack, this.controller);
        viewHandler = new BorderPane();
        Scene scene = new Scene(gameView, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadSprites(){
        try{
            bg = new Image(new FileInputStream("src/main/resources/map1.png"));
            //terrain = new Image(new FileInputStream("src/main/resources/terrain.png"));
            monster = new Image(new FileInputStream("src/main/resources/monster3.png"));
            tower = new Image(new FileInputStream("src/main/resources/tower2.png"), 60, 60, false, false);
            placeTowerBtn = new Image(new FileInputStream("src/main/resources/placeTower.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
