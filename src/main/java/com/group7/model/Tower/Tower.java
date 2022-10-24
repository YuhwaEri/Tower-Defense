package com.group7.model.Tower;

import com.group7.model.TowerDefenseEntity;

public class Tower extends TowerDefenseEntity {
    protected int towerID;
    protected TowerType type;

    protected int cost;
    protected int attack;
    protected int attackSpeed;

    public Tower(TowerType type, int towerID) {
        this.cost = type.getCost();
        this.attack = type.getAttack();
        this.attackSpeed = type.getAttackSpeed();

        this.picturePath = type.getPicturePath();
        this.towerID = towerID;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(int attack_speed) {
        this.attackSpeed = attack_speed;
    }

    public int getTowerID() {
        return towerID;
    }



}
