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

    public TowerDefenseModel() {
        this.money = 0;
        this.width = 100;
        this.length = 100;

        this.monsters = new ArrayList<Monster>();
        this.towers = new ArrayList<Tower>();

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;

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



    
}
