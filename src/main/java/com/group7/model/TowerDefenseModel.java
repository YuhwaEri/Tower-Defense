package com.group7.model;
import com.group7.model.Map.Maps;
import com.group7.model.Monster.*;
import com.group7.model.Tower.*;

import java.util.List;
import java.util.ArrayList;

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

    public TowerDefenseModel() {
        this.money = 0;

        this.monsters = new ArrayList<Monster>();
        this.towers = new ArrayList<Tower>();

        this.towerFactory = new TowerFactory();
        this.monsterFactory = new MonsterFactory();

        this.map = Maps.MAP_1;

        this.width = map.getWidth();
        this.length = map.getLength();


    }

    public Maps getMap() {
        return map;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;

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

    }

    public void setTowers(List<Tower> towers) {

        this.towers = towers;
    }

    public void addTower(TowerType type, int xCoord, int yCoord) {

        Tower newTower = towerFactory.createTower(type);
        newTower.setXCoord(xCoord);
        newTower.setYCoord(yCoord);

        towers.add(newTower);

    }

    public void addMonster(MonsterType type, int xCoord, int yCoord) {

        Monster newMonster = monsterFactory.createMonster(type);
        newMonster.setXCoord(xCoord);
        newMonster.setYCoord(yCoord);

        monsters.add(newMonster);

    }

    public void removeTower(Tower tower) {
        
        towers.remove(tower);

    }

    public void removeTower(int towerID) {
        
        for (Tower tower: towers) {
            if (tower.getTowerID() == towerID) {
                towers.remove(tower);
                return;
            }
        }

    }

    public void removeMonster(Monster monster) {

        monsters.remove(monster);
    }

    public void removeMonster(int monsterID) {

        for (Monster monster: monsters) {
            if (monster.getMonsterID() == monsterID) {
                monsters.remove(monster);
                return;
            }
        }
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
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
        this.lives = lives;
    }

    public int modifyLives(int delta) {
        int newLives = this.lives + delta;

        setLives(newLives);

        return newLives;
    }



    
}
