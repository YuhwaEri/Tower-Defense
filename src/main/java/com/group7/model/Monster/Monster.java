package com.group7.model.Monster;

import com.group7.model.TowerDefenseEntity;
import com.group7.model.Map.Block;

public class Monster extends TowerDefenseEntity {
    protected int monsterID;
    protected MonsterType type;

    protected int health;
    protected int moveCooldown;
    protected int moveCooldownRemaining;
    protected int killPayout;
    protected int livesTaken;

    protected int blockNum;
    


    public Monster(MonsterType type, int monsterID) {
        this.health = type.getHealth();
        this.moveCooldown = type.getMoveCooldown();
        this.killPayout = type.getKillPayout();

        this.picturePath = type.getPicturePath();
        this.monsterID = monsterID;

    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMoveCooldown() {
        return moveCooldown;
    }
    public void setMoveCooldown(int moveCooldown) {
        this.moveCooldown = moveCooldown;
    }

    public int getMonsterID() {

        return monsterID;
    }

    public int getKillPayout() {
        return killPayout;
    }

    public int getLivesTaken() {
        return livesTaken;
    }

    public int getMoveCooldownRemaining() {
        return moveCooldownRemaining;
    }

    public void setMoveCooldownRemaining(int moveCooldownRemaining) {
        this.moveCooldownRemaining = moveCooldownRemaining;
    }

    public boolean decrementMoveCooldownRemaining(int n) {

        moveCooldownRemaining -= n;

        // if cooldown is not over
        if (moveCooldownRemaining > 0) {

            return false;

        // if cooldown is over
        } else {

            // reset cooldown
            moveCooldownRemaining = moveCooldown;

            return true;
        }
    }

    public int getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(Block block) {
        this.blockNum = block.getBlockNum();

        this.xCoord = block.getxCoord();
        this.yCoord = block.getyCoord();
    }
}
