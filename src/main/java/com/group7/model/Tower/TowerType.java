package com.group7.model.Tower;

public enum TowerType {

    TOWER_1(80, 25, 2, 1000, "src/main/resources/tower1.png"),
    TOWER_2(160, 50, 1, 1000, "src/main/resources/tower2.png"),
    TOWER_3(125, 125, 3, 1000, "src/main/resources/tower3.png");

    private final int cost;
    private final int attack;
    private final int attackCooldown;
    private final double range;
    private final String picturePath;

    TowerType(int cost, int attack, int attackCooldown, double range, String picturePath) {
        this.cost = cost;
        this.attack = attack;
        this.attackCooldown = attackCooldown;
        this.range = range;
        this.picturePath = picturePath;

    }

    public int getCost() {
        return cost;
    }

    public int getAttack() {
        return attack;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public double getRange() {
        return range;
    }

}
