package com.group7.view;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

import com.group7.controller.InvalidTowerLocation;
import com.group7.controller.Ticker;
import com.group7.controller.TowerDefenseController;
import com.group7.model.Map.Block;
import com.group7.model.Monster.Monster;
import com.group7.model.Monster.MonsterType;
import com.group7.model.Tower.Tower;
import com.group7.model.Tower.TowerType;

public class GameView extends BorderPane{
    // internal objects used
    private final TowerDefenseController controller;
    private final GridPane gridPane = new GridPane();
    private final ArrayList<ArrayList<Pane>> cellGrid = new ArrayList<>();
    private boolean placeTowerMode = false;
    private boolean removeTowerMode = false;
    private List<Monster> monsters;
    private List<Tower> towers;

    // fields
    private Label moneyCount;
    private Label livesCount;

    public GameView(TowerDefenseView view, StackPane gameStack, TowerDefenseController controller, List<Tower> towers, List<Monster> monsters){
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
        topMenu.setPadding(new Insets(5));
        
        
        // Stats Window creation
        StackPane moneyWindow = new StackPane();
        topMenu.getChildren().add(moneyWindow);
        moneyWindow.setAlignment(Pos.CENTER);

        moneyCount = new Label("Gold " + Integer.toString(controller.getMoney()));
        moneyCount.setFont(new Font("Source Code Pro", 30));
        HBox.setMargin(moneyWindow, new Insets(0,0,0,450));
        moneyWindow.getChildren().add(moneyCount);

        StackPane livesWindow = new StackPane();
        topMenu.getChildren().add(livesWindow);
        livesWindow.setAlignment(Pos.CENTER);

        livesCount = new Label("Lives " + Integer.toString(controller.getLives()));
        livesCount.setFont(new Font("Source Code Pro", 30));

        HBox.setMargin(livesWindow, new Insets(0,0,0,30));
        livesWindow.getChildren().add(livesCount);

        ImageView startRoundBtn = new ImageView(TowerDefenseView.startRoundBtn);
        HBox.setMargin(startRoundBtn, new Insets(0,0,0,200));
        startRoundBtn.setOnMouseClicked((event)-> {

            controller.startRound();
            // Start ticker
        });
        topMenu.getChildren().add(startRoundBtn);

        livesCount.setTextFill(Color.WHITE);
        moneyCount.setTextFill(Color.WHITE);

    }

    private void setupBottomMenu(HBox bottomMenu){
        bottomMenu.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        bottomMenu.setPadding(new Insets(5));
        bottomMenu.setPrefSize(TowerDefenseView.WINDOW_WIDTH, 120);
        ImageView placeTowerBtn = new ImageView(TowerDefenseView.placeTowerBtn);
        ImageView removeTowerBtn = new ImageView(TowerDefenseView.removeTowerBtn);

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
        //controller.spawnMonster(MonsterType.MONSTER_1);

        //System.out.println("number of towers: " + controller.getTowers().size());
    }


    // issue: doesn't get rid of monster when move / deleted
    void updateMonsters() {

        //TODO: implement
    }

    void moveMonster(Block before, Monster monster) {


        removeMonster(before, monster);


        int newx = monster.getXCoord();
        int newy = monster.getYCoord();


        addMonsterToCell(monster, newx, newy);

    }

    void removeMonster(Block block, Monster monster) {

        int oldx = block.getxCoord();
        int oldy = block.getyCoord();
        Pane oldCell = cellGrid.get(oldy).get(oldx);

        Node nodeToRemove = null;

        for (Node node : oldCell.getChildren()) {
            if (node.getUserData() == monster) {
                nodeToRemove = node;
                break;
                
            }
            
        }
        oldCell.getChildren().remove(nodeToRemove);

    }

    void addMonsterToCell(Monster monster, int xCoord, int yCoord) {
        Pane cell = cellGrid.get(yCoord).get(xCoord);


        //ImageView iv = new ImageView(TowerDefenseView.monster);
        ImageView iv;
        try {
            iv = new ImageView(new Image(new FileInputStream(monster.getPicturePath())));
            iv.setUserData(monster);

            int monstersInCell = cell.getChildren().size();
    
            iv.fitWidthProperty().bind(cell.widthProperty());
            iv.fitHeightProperty().bind(cell.heightProperty());
            iv.setX(monstersInCell * 3 - 10);

            
            iv.setX(TowerDefenseView.WINDOW_WIDTH / controller.getBoardLength() * xCoord);
    
            cell.getChildren().add(iv);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Rectangle clip = new Rectangle(0, 0, 0, 0);
        clip.widthProperty().bind(cell.widthProperty());
        clip.heightProperty().bind(cell.heightProperty());
        cell.setClip(clip);
    }

    void addMonsterToCell(Monster monster, Block block) {
        addMonsterToCell(monster, block.getxCoord(), block.getyCoord());
    }

    void monsterGone(Block block) {
        
    }

    void updateKills() {
        
        //TODO: implement

        

    }

    void updateMoney() {
        
        moneyCount.setText("Gold " + Integer.toString(controller.getMoney()));

    }

    void updateLives() {
        
        //TODO: implement

        livesCount.setText("Lives " + Integer.toString(controller.getLives()));

    }
}