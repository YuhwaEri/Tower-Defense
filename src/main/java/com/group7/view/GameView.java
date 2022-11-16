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
    private boolean removeTowerMode = false;

    public GameView(TowerDefenseView view, StackPane gameStack, TowerDefenseController controller){
        this.view = view;
        this.gameStack = gameStack;
        this.controller = controller;
        gridPane.setBackground(new Background(new BackgroundImage(
            TowerDefenseView.bg, 
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1, 1, true, true, false, false)
        )));
        setupGameScene();
    }

    private void setupGameScene(){

        // setting up the menu
        HBox bottomMenu = new HBox();
        HBox topMenu = new HBox();
        setupGameBoard(gridPane);
        setupTopMenu(topMenu);
        setupBottomMenu(bottomMenu);

        // Positioning the pieces
        setTop(topMenu);
        setCenter(gridPane);
        setBottom(bottomMenu);
        
  

        try {
            cellGrid.get(11).get(3).getChildren().add(new ImageView(new Image(new FileInputStream("src/main/resources/monster3.png"), 80, 80, false, false)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setupGameBoard(GridPane gridPane){
        for (int i = 0; i < 12; i++){
            ArrayList<StackPane> cellRow = new ArrayList<>();
            for (int j = 0; j < 20; j++){
                int col = j;
                int row = i;
                StackPane cell = new StackPane();
                cell.setBackground(new Background( new BackgroundFill(Color.TRANSPARENT, null, null)));
                cellRow.add(cell);
                cell.setPrefHeight(60);
                cell.setPrefWidth(60);
                gridPane.add(cell, j, i); 
                cell.setOnMouseClicked((event) -> {
                    if (placeTowerMode){
                        controller.placeTower(TowerType.TOWER_2 , col, row);
                        placeTowerMode = false;
                        cellGrid.get(row).get(col).setBackground(new Background(new BackgroundImage (TowerDefenseView.tower, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(60, 60, false, false, false, false) )));
                        System.out.println("Tower placed at " + col + ":" + row);
                    }
                    else if (removeTowerMode){
                        //controller.removeTower(TowerType.TOWER_2 , col, row);
                        removeTowerMode = false;
                        cellGrid.get(row).get(col).setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
                        System.out.println("Tower removed at " + col + ":" + row);
                    }
                });
            }
            cellGrid.add(cellRow);
        } 
    }

    private void setupTopMenu(HBox topMenu){
        topMenu.setBackground(new Background(new BackgroundImage(TowerDefenseView.bottomBar, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        topMenu.setPadding(new Insets(0));
        topMenu.setPrefSize(TowerDefenseView.WINDOW_WIDTH, 120);
    }

    private void setupBottomMenu(HBox bottomMenu){
        bottomMenu.setPadding(new Insets(0));
        bottomMenu.setPrefSize(TowerDefenseView.WINDOW_WIDTH, 120);
        ImageView placeTowerBtn = new ImageView(TowerDefenseView.placeTowerBtn);
        ImageView removeTowerBtn = new ImageView(TowerDefenseView.placeTowerBtn);

        // Button to place towers
        bottomMenu.getChildren().add(placeTowerBtn);
        placeTowerBtn.setOnMouseClicked((event)-> {
            placeTowerMode = true;
        });

        // Button to remove towers
        bottomMenu.getChildren().add(removeTowerBtn);
        removeTowerBtn.setOnMouseClicked((event) -> {
            removeTowerMode = true;
        });
    }
}