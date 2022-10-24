package com.group7.model;
import com.group7.model.Monster.*;
import com.group7.model.Tower.*;

import java.util.List;
import java.util.ArrayList;

public class TowerDefenseModel {

    private int money;
    private int width;
    private int length;
    private List<Monster> monsters;
    private List<Tower> towers;
    private TowerFactory towerFactory;
    private MonsterFactory monsterFactory;

    public TowerDefenseModel() {
        this.money = 0;
        this.width = 100;
        this.length = 100;

        this.monsters = new ArrayList<Monster>();
        this.towers = new ArrayList<Tower>();

        this.towerFactory = new TowerFactory();
        this.monsterFactory = new MonsterFactory();

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



    
}
