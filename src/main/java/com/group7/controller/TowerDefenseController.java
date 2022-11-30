package com.group7.controller;

import com.group7.model.Monster.*;
import com.group7.model.Tower.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.group7.model.*;
import com.group7.model.Map.Block;
import com.group7.model.Map.Maps;
import com.group7.model.Map.Path;


public class TowerDefenseController {

    private TowerDefenseModel model;
    private boolean roundActive;
    private Path path;
    private Maps map;

    public TowerDefenseController(TowerDefenseModel model) {

        this.model = model;
        this.map = model.getMap();
        this.path = map.getPath();

        model.setMoney(100);
        model.setLives(100);

    }

    public List<Monster> getMonsters() {
        return model.getMonsters();
    }

    public List<Tower> getTowers() {
        return model.getTowers();
    }


    /**
     * Adds a given value to the given monster's health.
     * @param monster monster whose health is to be modified
     * @param delta change in health. positive to add health; negative to subtract.
     * @return health of monster after modification
     */
    public int modifyMonsterHealth(Monster monster, int delta) {

        int newHealth = monster.getHealth() + delta;
        monster.setHealth(newHealth);
        
        return newHealth;

    }

    /**
     * Finds monsters in range of a tower. Uses the tower's default range
     * @param tower tower to find monsters in range for
     * @return a map of all monsters in range of tower, keys being monsters and values being their respective distances
     */
    public Map<Monster, Double> getMonstersInRange(Tower tower) {

        Map<Monster, Double> monstersInRange = new HashMap<Monster, Double>();

        double range = tower.getRange();

        for (Monster monster : model.getMonsters()) {

            double distance = Math.hypot(tower.getXCoord() - monster.getXCoord(), tower.getYCoord() - monster.getYCoord());

            if (distance <= range) {
                monstersInRange.put(monster, distance);
            }

        }
        return monstersInRange;

    }

    /**
     * Finds monsters in range of a tower. Uses a custom range
     * @param tower tower to find monsters in range for
     * @return a map of all monsters in range of tower, keys being monsters and values being their respective distances
     */
    public Map<Monster, Double> getMonstersInRange(Tower tower, double range) {

        Map<Monster, Double> monstersInRange = new HashMap<Monster, Double>();

        for (Monster monster : model.getMonsters()) {

            double distance = Math.hypot(tower.getXCoord() - monster.getXCoord(), tower.getYCoord() - monster.getYCoord());

            if (distance <= range) {
                monstersInRange.put(monster, distance);
            }

        }

        return monstersInRange;

    }

    /**
     * Finds the closest monster in range of a tower. Uses tower's default range.
     * @param tower tower to find the closest monster in range for
     * @return Returns closest monster in range of the tower. If no such tower, returns null.
     */
    public Monster getClosestMonsterInRange(Tower tower) {

        Map<Monster, Double> monstersInRange = getMonstersInRange(tower);

        double smallestDistance = Double.MAX_VALUE;
        Monster monster = null;

        for (Map.Entry<Monster, Double> entry : monstersInRange.entrySet()) {
            if (entry.getValue() < smallestDistance) {
                monster = entry.getKey();
                smallestDistance = entry.getValue();
            }
        }

        return monster;
    }

    /**
     * Finds the closest monster in range of a tower. Uses a custom range.
     * @param tower tower to find the closest monster in range for
     * @return Returns closest monster in range of the tower. If no such tower, returns null.
     */
    public Monster getClosestMonsterInRange(Tower tower, double range) {

        Map<Monster, Double> monstersInRange = getMonstersInRange(tower, range);

        double smallestDistance = Double.MAX_VALUE;
        Monster monster = null;

        for (Map.Entry<Monster, Double> entry : monstersInRange.entrySet()) {
            if (entry.getValue() < smallestDistance) {
                monster = entry.getKey();
                smallestDistance = entry.getValue();
            }
        }

        return monster;
    }

    /**
     * Call when monster exits the map successfully. 
     * Decrements lives according to monster's livesTaken value and calls gameOver() if lives are depleted.
     * @param monster monster that is exiting
     * @return the numnber of lives left after monster exits.
     */
    public int monsterExit(Monster monster) {
        int lives = model.getLives();
        
        int livesTaken = monster.getLivesTaken();

        if (livesTaken >= lives) {
            model.setLives(0);
            gameOver();
            return 0;
        }

        return model.modifyLives(livesTaken * -1);
    }

    /**
     * Deals damage from a tower to a monster.
     * Increments tower's damageDealt count and calls appropriate function if kill occurs.
     * @param tower tower that is dealing damage
     * @param monster monster that is taking damage
     * @return true if monster is killed, false if not
     */
    public boolean giveDamage(Tower tower, Monster monster) {
        int attackPts = tower.getAttack();

        int monsterHealth = monster.getHealth();
        
        if (monsterHealth < attackPts) {
            attackPts = monsterHealth;
            
        }
        
        tower.addDamageDealt(attackPts);

        if (this.modifyMonsterHealth(monster, attackPts * -1) <= 0) {
            this.killMonster(tower, monster);
            return true;
        } else {
            return false;
        }
 
    }

    /**
     * Call this when a tower kills a monster. 
     * Increments player and tower killcount, adds money, and removes the monster.
     * @param tower tower that killed a monster
     * @param monster monster that has been killed
     */
    public void killMonster(Tower tower, Monster monster) {
        model.addKills(1);
        this.addMoney(monster.getKillPayout());
        
        tower.addKills(1);

        this.removeMonster(monster);
    }

    /**
     * Removes the given monster.
     * @param monster monster to be removed
     */
    public void removeMonster(Monster monster) {

        model.removeMonster(monster);
    
    }

    /**
     * Sets the position of an TowerDefenseEntity.
     * @param entity entity whose position is to be updated
     * @param xCoord new x coordinate
     * @param yCoord new y coordinate
     */
    public void setEntityPosition(TowerDefenseEntity entity, int xCoord, int yCoord) {

        entity.setXCoord(xCoord);
        entity.setYCoord(yCoord);

    }

    /**
     * Moves a TowerDefenseEntity
     * @param entity entity that is to be moved
     * @param deltaX number of units to be moved in x-direction 
     * @param deltaY numbe rof units to be moved in y-direction
     */
    public void moveEntity(TowerDefenseEntity entity, int deltaX, int deltaY) {
        
        int newXCoord = entity.getXCoord() + deltaX;
        int newYCoord = entity.getYCoord() + deltaY;

        setEntityPosition(entity, newXCoord, newYCoord);
    }

    /**
     * Place a given tower type at the given coordinates.
     * @param type type of tower to place
     * @param xCoord x-coordinate where tower should be placed
     * @param yCoord y-coordinate where tower should be placed
     * @throws InvalidTowerLocation
     */
    public Tower placeTower(TowerType type, int xCoord, int yCoord) throws InvalidTowerLocation {

        if (isOnPath(xCoord, yCoord)) {
            throw new InvalidTowerLocation("Cannot place tower on path.");
        }
        
        return model.addTower(type, xCoord, yCoord);


    }

    /**
     * Purchases a given tower type at the given coordinates.
     * @param type type of tower to be purchased
     * @param XCoord x-coordinate where tower should be placed
     * @param yCoord y-coordinate where tower should be placed
     * @throws InsufficientFundsException thrown if not enough money for purchase
     * @throws InvalidTowerLocation
     */
    public Tower purchaseTower(TowerType type, int XCoord, int yCoord) throws InsufficientFundsException, InvalidTowerLocation {
        
        this.subtractMoney(type.getCost());
        return placeTower(type, XCoord, yCoord);

    }

    /**
     * Removes the given tower.
     * @param tower tower to be removed.
     */
    public void removeTower(Tower tower) {
        model.removeTower(tower);
    }

    /**
     * Spawns a monster at given coordinates.
     * @param type type of monster to spawn
     * @param xCoord x-coordinate where monster should spawn
     * @param yCoord y-coordinate where monster should spawn
     */
    public void spawnMonster(MonsterType type) {

        Block startBlock = model.getMap().getPath().getBlock(0);

        
        model.addMonster(type, startBlock.getxCoord(), startBlock.getyCoord());
    }


    /**
     * Adds a given amount of money to the player's money.
     * @param amount the amount of money to add
     * @return the amount of money following the operation
     */
    public int addMoney(int amount) {

        int newMoney = model.getMoney() + amount;

        model.setMoney(newMoney);

        return newMoney;

    }

    /**
     * Sets the players money to the given amount.
     * @param amount amount of money to set money to
     */
    public void setMoney(int amount) {

        model.setMoney(amount);
    }

    public int getMoney() {

        return model.getMoney();
    }

    /**
     * Subtracts money from the player's money.
     * @param amount the amount of money to be subtracted
     * @return the amount of money following the operation
     * @throws InsufficientFundsException if amount > amount of money player has
     */
    public int subtractMoney(int amount) throws InsufficientFundsException {

        int newMoney = model.getMoney() - amount;

        if (newMoney < 0) {
            throw new InsufficientFundsException(
                "Transaction of -$" + amount + " failed; only $" + model.getMoney() + " available.");
        }

        model.setMoney(newMoney);

        return newMoney;

    }

    /**
     * Returns the number of kills the player has.
     * @return the number of kills the player has.
     */
    public int getKills() {
        return model.getKills();
    }

    /**
     * Returns the number of lives the player has.
     * @return the number of lives the player has.
     */
    public int getLives() {
        return model.getLives();
    }

    /**
     * Sets the remaining lives of the player to the given number.
     * @param lives the number of desired lives.
     */
    public void setLives(int lives) {
        model.setLives(lives);
    }

    public int modifyLives(int delta) {
        return model.modifyLives(delta);
    }


    public boolean isOnPath(int xCoord, int yCoord) {

        if (map.isOnPath(xCoord, yCoord)) {
            return true;
        } else return false;
    }

    /**
     * 
     */
    public void gameOver() {

        // TODO: implement


    }

    public void startRound() {

        // TODO: implement
        
        // start spawning in monsters
        // where to store how many and which monsters in each round? Is this set or calculated programmatically?
        // (probably better hardcoded and stored somewhere)

        // allow towers to be activated (or does this happen in view? probably not)


        // maybe a LevelRecipe class/enum or something that has: 
        //  - an ordered list of monsters 
        //  - level $ bonus
        //  - speed multiplier (slowly increasing speed of monsters on later rounds)
        // a LevelFactory can always generate the next level
        // another idea: info for each level is stored in csv / json (probably best)

    }

    public void endRound() {

        // TODO: implement

        // stop attacks

        // delete all monsters (maybe this is already done and doesn't have to be done here)

        // give player round completion money (how much depending on level? shouldn't be hard to calculate programatically,
        // but if we have hardcoded monster info per level saved somewhere, we can also put this info in there too)


    }

    public int getBoardWidth() {
        return model.getWidth();
    }

    public int getBoardLength() {
        return model.getLength();
    }

    public void handleTick(int ticks) {


        if (roundActive) {

            for (Tower tower : model.getTowers()) {
                
                if (tower.decrementCooldownRemaining(1)) {

                    initiateAttack(tower);

                }

            }

            for (Monster monster : model.getMonsters()) {

                if (monster.decrementMoveCooldownRemaining(1)) {

                    initiateMove(monster);
                }
            }

        }

        
    }

    private void initiateAttack(Tower tower) {
        // TODO: implement
    }

    private void initiateMove(Monster monster) {

        int blockNum = monster.getBlockNum();

        Block nextBlock = path.nextBlock(blockNum);

        if (nextBlock == null) {
            monsterExit(monster);
        } else {
            monster.setBlockNum(nextBlock);
            model.monstersUpdated();
        }

    }
}




