package com.group7.model.Tower;

public enum TowerType {

    TOWER_1(80, 25, 2, "src/main/resources/tower1.png"),
    TOWER_2(160, 50, 1, "src/main/resources/tower2.png"),
    TOWER_3(125, 125, 3, "src/main/resources/tower3.png");

    private final int cost;
    private final int attack;
    private final int attackSpeed;
    private final String picturePath;

    TowerType(int cost, int attack, int attackSpeed, String picturePath) {
        this.cost = cost;
        this.attack = attack;
        this.attackSpeed = attackSpeed;
        this.picturePath = picturePath;

    }

    public int getCost() {
        return cost;
    }

    public int getAttack() {
        return attack;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public String getPicturePath() {
        return picturePath;
    }

}
