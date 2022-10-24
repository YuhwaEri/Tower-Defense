package com.group7.model.Tower;

public class TowerFactory {

    private int towersGenerated;

    public TowerFactory() {

        this.towersGenerated = 0;

    }


    public Tower createTower(TowerType type) {

        towersGenerated++;

        return new Tower(type, towersGenerated);
    }

    public int getTowersGenerated() {

        return towersGenerated;
    }






    
}
