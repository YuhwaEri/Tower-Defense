package com.group7.view;

import java.io.File;
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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TowerDefenseView extends Application implements PropertyChangeListener{

    static Image terrain = null;
    static Image monster = null;
    static Image tower = null;
    static Image bg = null;

    private final StackPane gameStack = new StackPane();

    // Program Constants
    static final double WINDOW_HEIGHT = 800;
    static final double WINDOW_WIDTH = 1200;

    // fields
    private BorderPane viewHandler;
    private GameView gameView;


    // model and controller
    private TowerDefenseController controller;
    private TowerDefenseModel model;


    @Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Property changed: " + evt.getPropertyName());
		System.out.println("\t(" + evt.getOldValue() + 
							" -> " + evt.getNewValue() + ")");
		System.out.println("Property in object " + evt.getSource());
	}

    private void setup(File file) {
        if (file == null) {
            model = new TowerDefenseModel();
        }
        else {
            // If we add save-load functionality, this is where a save file would be passed in;
            // TowerDefenseModel(File file) constructor would need to be implemented.
            // For now, using default constructor.

            model = new TowerDefenseModel();
        }

        controller = new TowerDefenseController(model);

        model.addObserver(this);
    }
   
    @Override
    public void start(Stage primaryStage) {

        setup(null);

        primaryStage.setTitle("TowerDefense");
        // initializing the internal classes
        loadSprites();
        gameView = new GameView(this, gameStack, controller);
        viewHandler = new BorderPane();
        Scene scene = new Scene(gameView, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

       // -- TESTING LISTENER
        
        System.out.println("lives: " + controller.getLives());

        controller.modifyLives(-5);
        // At this point, action should've fired and propertyChanged() should've run and printed stuff

        System.out.println("lives: " + controller.getLives()); // sanity check
    }

    private void loadSprites(){
        try{
            bg = new Image(new FileInputStream("src/main/resources/map1.png"));
            //terrain = new Image(new FileInputStream("src/main/resources/terrain.png"));
            monster = new Image(new FileInputStream("src/main/resources/monster3.png"));
            tower = new Image(new FileInputStream("src/main/resources/tower2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
