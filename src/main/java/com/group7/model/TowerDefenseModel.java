package com.group7.model;
import com.group7.model.Map.Block;
import com.group7.model.Map.Maps;
import com.group7.model.Monster.*;
import com.group7.model.Tower.*;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class TowerDefenseModel {

    private int money;
    private int lives;
    private int kills;
    private int width;
    private int length;
    private List<Monster> monsters;
    private List<Tower> towers;
    private TowerFactory towerFactory;
    private MonsterFactory monsterFactory;

    private Maps map;

    private LinkedList<Level> levels;

    PropertyChangeSupport pcs;

    public TowerDefenseModel() {
        this.money = 0;

        this.monsters = new ArrayList<Monster>();
        this.towers = new ArrayList<Tower>();

        this.towerFactory = new TowerFactory();
        this.monsterFactory = new MonsterFactory();

        this.map = Maps.MAP_1;

        this.width = map.getWidth();
        this.length = map.getLength();

        pcs = new  PropertyChangeSupport(this);

        levels = readLevels();
    }

    private LinkedList<Level> readLevels() {
        LinkedList<Level> levels = new LinkedList<>();

        File dir = new File("src/main/resources/levels/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                Level tmpLevel = new Level(child.getPath());
                levels.add(tmpLevel);
            }
        }

        return levels;
    }

    public void addObserver(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    public void monstersUpdated() {
        pcs.firePropertyChange("monsters", false, true);

    }

    public void towersUpdated() {
        pcs.firePropertyChange("towers", false, true);
    }



    public Maps getMap() {
        return map;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {

        int oldMoney = this.money;
        this.money = money;

        pcs.firePropertyChange("money", oldMoney, this.money);

    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setMonsters(List<Monster> monsters) {

        this.monsters = monsters;

        monstersUpdated();

    }

    public void setTowers(List<Tower> towers) {

        this.towers = towers;

        towersUpdated();
    }

    public Tower addTower(TowerType type, int xCoord, int yCoord) {

        Tower newTower = towerFactory.createTower(type);
        newTower.setXCoord(xCoord);
        newTower.setYCoord(yCoord);

        towers.add(newTower);

        towersUpdated();

        return newTower;

    }


    public void removeTower(Tower tower) {
        
        towers.remove(tower);

        towersUpdated();

    }

    public void removeTower(int towerID) {
        
        for (Tower tower: towers) {
            if (tower.getTowerID() == towerID) {
                towers.remove(tower);
                return;
            }
        }

        towersUpdated();

    }

    public void addMonster(MonsterType type, int xCoord, int yCoord) {

        Monster newMonster = monsterFactory.createMonster(type);
        newMonster.setXCoord(xCoord);
        newMonster.setYCoord(yCoord);

        monsters.add(newMonster);

        pcs.firePropertyChange("monster-add", newMonster.getBlock(map), newMonster);

    }

    public void removeMonster(Monster monster) {

        monsters.remove(monster);

        pcs.firePropertyChange("monster-remove", monster.getBlock(map), monster);
    }

    public void removeMonster(int monsterID) {

        for (Monster monster: monsters) {
            if (monster.getMonsterID() == monsterID) {
                removeMonster(monster);
            }
        }
    }

    public void moveMonster(Monster monster, Block oldBlock, Block newBlock) {

        monster.setBlockNum(newBlock);

        pcs.firePropertyChange("monster-move", oldBlock, monster);
        
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {

        int oldKills = this.kills;
        this.kills = kills;

        pcs.firePropertyChange("kills", oldKills, this.kills);
    }

    public int addKills(int kills) {
        int newKills = this.kills + kills;
        setKills(newKills);
        return newKills;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {

        int oldLives = this.lives;
        this.lives = lives;

        pcs.firePropertyChange("lives", oldLives, this.lives);
    }

    public int modifyLives(int delta) {
        int newLives = this.lives + delta;

        setLives(newLives);

        return newLives;
    }

    public LinkedList<Level> getLevels() {
        return levels;
    }



    
}
