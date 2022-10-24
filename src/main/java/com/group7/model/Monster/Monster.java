package com.group7.model.Monster;

import com.group7.model.TowerDefenseEntity;

public class Monster extends TowerDefenseEntity {
    protected int monsterID;
    protected MonsterType type;

    protected int health;
    protected int moveSpeed;


    public Monster(MonsterType type, int monsterID) {
        this.health = type.getHealth();
        this.moveSpeed = type.getMoveSpeed();

        this.picturePath = type.getPicturePath();
        this.monsterID = monsterID;

    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMoveSpeed() {
        return moveSpeed;
    }
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getMonsterID() {

        return monsterID;
    }
}
