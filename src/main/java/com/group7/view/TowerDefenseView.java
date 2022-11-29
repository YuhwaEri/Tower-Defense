package com.group7.view;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.group7.controller.TowerDefenseController;
import com.group7.model.TowerDefenseModel;
import com.group7.model.Monster.Monster;
import com.group7.model.Tower.Tower;

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

import javafx.scene.Node;

public class TowerDefenseView extends Application implements PropertyChangeListener{

    // Media player
    private MediaPlayer mediaPlayer;
    private double defaultVolume = .2;

    // Images to be preloaded
    static Image terrain = null;
    static Image monster = null;
    static Image tower = null;
    static Image bg = null;
    static Image placeTowerBtn = null;
    static Image bottomBar = null;
    static Image mainMenuBackground = null;
    static Image startRoundBtn = null;

    private final StackPane gameStack = new StackPane();

    // Program Constants
    static final double WINDOW_HEIGHT = 1050;
    static final double WINDOW_WIDTH = 1200;

    // fields
    private BorderPane viewHandler;
    private GameView gameView;
    private MainMenu mainMenu;


    // model and controller
    private TowerDefenseController controller;
    private TowerDefenseModel model;
    private List<Tower> towers;
    private List<Monster> monsters;

    private boolean isInGame = false;

    @Override
	public void propertyChange(PropertyChangeEvent evt) {


        if (evt.getPropertyName().equals("towers")){
            gameView.updateTowers();
        }

        else if (evt.getPropertyName().equals("monsters")) {
            gameView.updateMonsters();
        }

        else if (evt.getPropertyName().equals("kills")) {
            gameView.updateKills();
        }

        else if (evt.getPropertyName().equals("money")) {
            gameView.updateMoney();
        }

        else if (evt.getPropertyName().equals("lives")) {
            gameView.updateLives();
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

        this.towers = controller.getTowers();
        this.monsters = controller.getMonsters();

        model.addObserver(this);
    }
   
    @Override
    public void start(Stage primaryStage) {

        setup(null);

        primaryStage.setTitle("TowerDefense");
        // initializing the internal classes
        loadSprites();
        mainMenu = new MainMenu(this);
        gameView = new GameView(this, gameStack, controller, towers, monsters);
        viewHandler = new BorderPane();
        Scene scene = new Scene(viewHandler, WINDOW_WIDTH, WINDOW_HEIGHT);
        viewHandler.setCenter(mainMenu);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start Media player
        loadMedia();

    }

    private void loadSprites(){
        try{
            bg = new Image(new FileInputStream("src/main/resources/map1.png"));
            terrain = new Image(new FileInputStream("src/main/resources/grass.png"));
            monster = new Image(new FileInputStream("src/main/resources/monster3.png"));
            tower = new Image(new FileInputStream("src/main/resources/tower2.png"), 60, 60, false, false);
            placeTowerBtn = new Image(new FileInputStream("src/main/resources/placeTower.png"));
            bottomBar = new Image(new FileInputStream("src/main/resources/toolbar.png"));
            mainMenuBackground = new Image(new FileInputStream("src/main/resources/temp.png"));
            startRoundBtn = new Image(new FileInputStream("src/main/resources/startRoundBtn.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void transitionBetweenGames(){
        if(isInGame){
            isInGame = false;
            viewHandler.setCenter(mainMenu);
        } else {
            isInGame = true;
            viewHandler.setCenter(gameView);
        }
    }

    public void updateWindow(Node node){
        viewHandler.setCenter(node);
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

    public void updateVolume(double newValue){
        defaultVolume = newValue;
        mediaPlayer.setVolume(newValue);
    }

}
