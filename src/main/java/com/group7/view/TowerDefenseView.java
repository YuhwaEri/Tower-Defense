package com.group7.view;

import java.util.ArrayList;
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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TowerDefenseView extends Application implements PropertyChangeListener{

    // Media player
    private MediaPlayer mediaPlayer;
    private double defaultVolume = .4;

    // Images to be preloaded
    static Image terrain = null;
    static Image monster = null;
    static Image tower = null;
    static Image bg = null;
    static Image placeTowerBtn = null;
    static Image bottomBar;

    private final StackPane gameStack = new StackPane();

    // Program Constants
    static final double WINDOW_HEIGHT = 1050;
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

        if (evt.getPropertyName().equals("towers")){
            gameView.updateTowers();
        }
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

        // Start Media player
        loadMedia();

       // -- TESTING LISTENER
        
        System.out.println("lives: " + controller.getLives());

        controller.modifyLives(-5);
        // At this point, action should've fired and propertyChanged() should've run and printed stuff

        System.out.println("lives: " + controller.getLives()); // sanity check
    }

    private void loadSprites(){
        try{
            bg = new Image(new FileInputStream("src/main/resources/map1.png"));
            terrain = new Image(new FileInputStream("src/main/resources/grass.png"));
            monster = new Image(new FileInputStream("src/main/resources/monster3.png"));
            tower = new Image(new FileInputStream("src/main/resources/tower2.png"), 60, 60, false, false);
            placeTowerBtn = new Image(new FileInputStream("src/main/resources/placeTower.png"));
            bottomBar = new Image(new FileInputStream("src/main/resources/toolbar.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadMedia(){
        ArrayList<Media> songList = new ArrayList<>();
        Media song1 = new Media(new File("src/main/resources/sound/Warmth.wav").toURI().toString());
        Media song2 = new Media(new File("src/main/resources/sound/Chachokid.wav").toURI().toString());
        songList.add(song2);
        songList.add(song1);
        playMusic(songList);
    }

    private void playMusic(ArrayList<Media> songList) {
        // if the songs have not been loaded yet
        if (songList.size() == 0) {
            loadMedia();
            return;
        }
        // looping the music selection if called after initializing
        mediaPlayer = new MediaPlayer(songList.remove(0));
        mediaPlayer.setVolume(defaultVolume);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> playMusic(songList));
    }
}
