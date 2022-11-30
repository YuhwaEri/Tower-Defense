package com.group7.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.group7.controller.Ticker;
import com.group7.model.Monster.MonsterType;

public class Level {

    private int reward;
    private String levelName;
    private LinkedList<Map<MonsterType, Double>> monsterRecipe;

    public Level(String levelCSVpath) {

        monsterRecipe = new LinkedList<>();

        try {
            Scanner sc = new Scanner(new File(levelCSVpath));

            sc.useDelimiter(",");

            this.levelName = sc.nextLine();

            String rewardStr = sc.nextLine();
            reward = Integer.parseInt(rewardStr);

            Double spawnTime = 0.0;

            while (sc.hasNext()) {
                String[] row = sc.nextLine().split(",");
                MonsterType monsterType = MonsterType.getMonsterType(Integer.parseInt(row[0]));
                Double waitTillNext = Double.parseDouble(row[1]);

                spawnTime += (waitTillNext * Ticker.STARTING_TICK_RATE);

                Map<MonsterType, Double> tmpMap = new HashMap<>();

                tmpMap.put(monsterType, spawnTime);
                monsterRecipe.add(tmpMap);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public int getReward() {
        return reward;
    }

    public List<Map<MonsterType, Double>> getMonsterRecipe() {
        return monsterRecipe;
    }

    public Map<MonsterType, Double> getAndRemoveNextMonster() {

        Map<MonsterType, Double> nextMonster = monsterRecipe.getFirst();

        monsterRecipe.removeFirst();

        return nextMonster;
    }

    public Map<MonsterType, Double> getNextMonster() {

        Map<MonsterType, Double> nextMonster = monsterRecipe.getFirst();

        return nextMonster;
    }

    public String getLevelName() {
        return levelName;
    }

    public boolean decrementSpawnTime(int n) {

        if (monsterRecipe.isEmpty()) return false;

        Map<MonsterType, Double> nextMonster = monsterRecipe.getFirst();
        MonsterType key = nextMonster.entrySet().iterator().next().getKey();
        nextMonster.put(key, nextMonster.get(key) - n);

        // if cooldown is not over
        if (nextMonster.get(key) > 0) {

            return false;

        // if cooldown is over
        } else {

            return true;
        }
    }


    
}
