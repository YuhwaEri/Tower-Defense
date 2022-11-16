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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.group7.controller.InvalidTowerLocation;
import com.group7.controller.TowerDefenseController;
import com.group7.model.Monster.Monster;
import com.group7.model.Monster.MonsterType;
import com.group7.model.Tower.Tower;
import com.group7.model.Tower.TowerType;

public class GameView extends BorderPane{
    // internal objects used
    private final TowerDefenseView view;
    private final TowerDefenseController controller;
    private final StackPane gameStack;
    private final GridPane gridPane = new GridPane();
    private final ArrayList<ArrayList<Pane>> cellGrid = new ArrayList<>();
    private boolean placeTowerMode = false;
    private boolean removeTowerMode = false;
    private List<Monster> monsters;
    private List<Tower> towers;

    public GameView(TowerDefenseView view, StackPane gameStack, TowerDefenseController controller, List<Tower> towers, List<Monster> monsters){
        this.view = view;
        this.gameStack = gameStack;
        this.controller = controller;
        this.monsters = monsters;
        this.towers = towers;
        gridPane.setBackground(new Background(new BackgroundImage(
            TowerDefenseView.bg, 
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        )));
        setupGameScene();
    }

    private void setupGameScene(){

        // setting up the menu
        HBox bottomMenu = new HBox();
        HBox topMenu = new HBox();
        setupGameBoard(gridPane, controller.getBoardWidth(), controller.getBoardLength());
        setupTopMenu(topMenu);
        setupBottomMenu(bottomMenu);

        // Positioning the pieces
        setTop(topMenu);
        setCenter(gridPane);
        setBottom(bottomMenu);
        
  

        try {
            cellGrid.get(5).get(3).getChildren().add(new ImageView(new Image(new FileInputStream("src/main/resources/monster3.png"), 80, 80, false, false)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setupGameBoard(GridPane gridPane, int width, int length){

        gridPane.setGridLinesVisible(true);

        gridPane.setMaxWidth(TowerDefenseView.WINDOW_WIDTH);
        for (int i = 0; i < length; i++){
            ArrayList<Pane> cellRow = new ArrayList<>();
            for (int j = 0; j < width; j++){
            
                int row = i;
                int col = j;
                Pane cell = new Pane();
                cell.setBackground(new Background( new BackgroundFill(Color.TRANSPARENT, null, null)));
                cellRow.add(cell);
                gridPane.add(cell, col, row); 
                cell.setOnMouseClicked((event) -> {
                    if (placeTowerMode && cell.getUserData() == null) {
                        try {
                            controller.placeTower(TowerType.TOWER_2 , col, row);
                            placeTowerMode = false;

                        } catch (InvalidTowerLocation e) {
                            System.out.println(e);
                            
                        }

                        //System.out.println("Tower placed at " + col + ":" + row);
                    }
                    else if (removeTowerMode && cell.getUserData() != null){
                        controller.removeTower((Tower) cell.getUserData());
                        cell.setUserData(null);
                        removeTowerMode = false;
                        //cell.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

                        cell.getChildren().clear();

                        //System.out.println("Tower removed at " + col + ":" + row);
                        
                    }
                });
            }
            cellGrid.add(cellRow);

        } 

        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100d / width);
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100d / length);
        for (int i = 0; i < width; i++) {
            gridPane.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < length; i++) {
            gridPane.getRowConstraints().add(rc);
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
            removeTowerMode = false;
        });

        // Button to remove towers
        bottomMenu.getChildren().add(removeTowerBtn);
        removeTowerBtn.setOnMouseClicked((event) -> {
            removeTowerMode = true;
            placeTowerMode = false;
        });
    }

    void updateTowers(){

        for (Tower tower: towers){
            int x = tower.getXCoord();
            int y = tower.getYCoord();


            Pane cell = cellGrid.get(y).get(x);

            if (cell.getUserData() != tower) {

                cell.setUserData(tower);

                // cell.setBackground(new Background(new BackgroundImage (TowerDefenseView.tower, BackgroundRepeat.NO_REPEAT,
                //         BackgroundRepeat.NO_REPEAT,
                //         BackgroundPosition.CENTER,
                //         new BackgroundSize(.8, .8, true, true, false, false) )));

                ImageView iv = new ImageView(TowerDefenseView.tower);
                iv.fitWidthProperty().bind(cell.widthProperty());
                iv.fitHeightProperty().bind(cell.heightProperty());
                cell.getChildren().add(iv);

            }

            

        }

        // JUST TESTING SPAWNING
        controller.spawnMonster(MonsterType.MONSTER_1);

        //System.out.println("number of towers: " + controller.getTowers().size());
    }

    void updateMonsters() {

        //TODO: implement

        for (Monster monster : monsters) {
            int x = monster.getXCoord();
            int y = monster.getYCoord();

            Pane cell = cellGrid.get(y).get(x);


            ImageView iv = new ImageView(TowerDefenseView.monster);

            int monstersInCell = cell.getChildren().size();

            iv.fitWidthProperty().bind(cell.widthProperty());
            iv.fitHeightProperty().bind(cell.heightProperty());
            iv.setX(monstersInCell * 3 - 10);

            cell.getChildren().add(iv);

            Rectangle clip = new Rectangle(0, 0, 0, 0);
            clip.widthProperty().bind(cell.widthProperty());
            clip.heightProperty().bind(cell.heightProperty());
            cell.setClip(clip);

            

            


        }

    }

    void updateKills() {
        
        //TODO: implement

    }

    void updateMoney() {
        
        //TODO: implement

    }

    void updateLives() {
        
        //TODO: implement

    }
}