package com.group7.model.Monster;

public enum MonsterType {

    MONSTER_1(1, 500, 60, 10, 1, "src/main/resources/monster1.png"),
    MONSTER_2(2, 300, 2, 20, 2, "src/main/resources/monster2.png"),
    MONSTER_3(3, 200, 3, 30, 3, "src/main/resources/monster3.png");

    private final int monsterTypeNum;
    private final int health;
    private final int moveCooldown;
    private final int killPayout;
    private final int livesTaken;
    private final String picturePath;

    MonsterType(int monsterTypeNum, int health, int moveCooldown, int killPayout, int livesTaken, String picturePath) {
        this.monsterTypeNum = monsterTypeNum;
        this.health = health;
        this.moveCooldown = moveCooldown;
        this.killPayout = killPayout;
        this.livesTaken = livesTaken;
        this.picturePath = picturePath;
    }

    public int getHealth() {
        return health;
    }

    public int getMoveCooldown() {
        return moveCooldown;
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

    public int getMonsterTypeNum() {
        return monsterTypeNum;
    }

    public static MonsterType getMonsterType(int monsterTypeNum) {

        if (monsterTypeNum == 1) return MONSTER_1;

        else if (monsterTypeNum == 2) return MONSTER_2;

        else if (monsterTypeNum == 3) return MONSTER_3;

        else return null;
    }
    
}
