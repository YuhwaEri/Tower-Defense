package com.group7.model.Monster;

public enum MonsterType {

    MONSTER_1(500, 1, "src/main/resources/monster1.png"),
    MONSTER_2(300, 2, "src/main/resources/monster2.png"),
    MONSTER_3(50, 3, "src/main/resources/monster3.png");

    private final int health;
    private final int moveSpeed;
    private final String picturePath;

    MonsterType(int health, int moveSpeed, String picturePath) {
        this.health = health;
        this.moveSpeed = moveSpeed;
        this.picturePath = picturePath;
    }

    public int getHealth() {
        return health;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public String getPicturePath() {
        return picturePath;
    }
    
}
