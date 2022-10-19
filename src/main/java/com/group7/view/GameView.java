package com.group7.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameView extends BorderPane{
    // internal objects used
    private final TowerDefenseView view;
    private final StackPane gameStack;
    private final GridPane farmGrid = new GridPane();
    private final ArrayList<ArrayList<StackPane>> cellGrid = new ArrayList<>();

    public GameView(TowerDefenseView view, StackPane gameStack){
        this.view = view;
        this.gameStack = gameStack;
        setBackground(new Background(new BackgroundImage(
            TowerDefenseView.terrain, 
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT
        )));
    }
}
