package com.group7.model.Monster;

public enum MonsterType {

    MONSTER_1(500, 1, 10, 1, "src/main/resources/monster1.png"),
    MONSTER_2(300, 2, 20, 2, "src/main/resources/monster2.png"),
    MONSTER_3(200, 3, 30, 3, "src/main/resources/monster3.png");

    private final int health;
    private final int moveSpeed;
    private final int killPayout;
    private final int livesTaken;
    private final String picturePath;

    MonsterType(int health, int moveSpeed, int killPayout, int livesTaken, String picturePath) {
        this.health = health;
        this.moveSpeed = moveSpeed;
        this.killPayout = killPayout;
        this.livesTaken = livesTaken;
        this.picturePath = picturePath;
    }

    public int getHealth() {
        return health;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getKillPayout() {
        return killPayout;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public int getLivesTaken() {
        return livesTaken;
    }
    
}
