package com.group7.view;

import javafx.beans.Observable;
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

import com.group7.controller.TowerDefenseController;
import com.group7.model.Tower.TowerType;

public class GameView extends BorderPane{
    // internal objects used
    private final TowerDefenseView view;
    private final TowerDefenseController controller;
    private final StackPane gameStack;
    private final GridPane gridPane = new GridPane();
    private final ArrayList<ArrayList<StackPane>> cellGrid = new ArrayList<>();
    private boolean placeTowerMode = false;

    public GameView(TowerDefenseView view, StackPane gameStack, TowerDefenseController controller){
        this.view = view;
        this.gameStack = gameStack;
        this.controller = controller;
        setBackground(new Background(new BackgroundImage(
            TowerDefenseView.bg, 
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(.8, .8, true, true, false, false)
        )));
        setupGameScene();
    }

    private void setupGameScene(){

        // setting up the menu
        HBox placeTower = new HBox();
        placeTower.setPadding(new Insets(5));
        placeTower.setPrefSize(TowerDefenseView.WINDOW_WIDTH, 100);
        ImageView placeTowerBtn = new ImageView(TowerDefenseView.placeTowerBtn);
        placeTower.getChildren().add(placeTowerBtn);
        placeTowerBtn.setOnMouseClicked((event)-> {
            placeTowerMode = true;

            for (int x = 0; x < 12; x++) {
                for (int y = 0; y < 20; y++) {
                    int col = x;
                    int row = y;
                    cellGrid.get(x).get(y).setOnMouseClicked((event2) -> {
                        controller.placeTower(TowerType.TOWER_2 , col, row);
                        placeTowerMode = false;
                        cellGrid.get(col).get(row).getChildren().add(new ImageView(TowerDefenseView.tower));
                    });
                }
            }
        });

        setBottom(placeTower);

        setCenter(gridPane);

        for (int i = 0; i < 12; i++){
            ArrayList<StackPane> cellRow = new ArrayList<>();
            cellGrid.add(cellRow);
            for (int j = 0; j < 20; j++){
                StackPane cell = new StackPane();
                cell.setBackground(new Background( new BackgroundFill(Color.TRANSPARENT, null, null)));
                cell.setPadding(new Insets(0));

                cellRow.add(cell);
                cell.setPrefHeight(60);
                cell.setPrefWidth(60);
                gridPane.add(cell, j, i); 
            }
            cellGrid.add(cellRow);
        }   

        try {
            cellGrid.get(8).get(9).getChildren().add(new ImageView(new Image(new FileInputStream("src/main/resources/tower2.png"), 60, 60, false, false)));
            cellGrid.get(12).get(3).getChildren().add(new ImageView(new Image(new FileInputStream("src/main/resources/monster3.png"), 80, 80, false, false)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
