package com.group7.model.Monster;


public class MonsterFactory {
    
    private int monstersGenerated;

    public MonsterFactory() {
        this.monstersGenerated = 0;
    }

    public Monster createMonster(MonsterType type) {

        monstersGenerated++;

        return new Monster(type, monstersGenerated);
            
    }

    public int getMonstersGenerated() {
        return monstersGenerated;

    }
}
