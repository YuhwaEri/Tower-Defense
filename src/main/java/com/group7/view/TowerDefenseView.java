package com.group7.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class TowerDefenseView extends Application{

    static Image terrain = null;
    //static Image monster = null;
    //static Image tower = null;

    private final StackPane gameStack = new StackPane();

    // Program Constants
    static final double WINDOW_HEIGHT = 1080;
    static final double WINDOW_WIDTH = 1920;

    // fields
    private BorderPane viewHandler;
    private boolean isInGame = false;
    private GameView gameView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TowerDefense");
        // initializing the internal classes
        loadSprites();
        gameView = new GameView(this, gameStack);
        viewHandler = new BorderPane();
        Scene scene = new Scene(gameView, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadSprites(){
        try{
            FileInputStream testImg = new FileInputStream("src\\main\\java\\com\\group7\\resources\\katakata.png");
            terrain = new Image(testImg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
