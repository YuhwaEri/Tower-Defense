package com.group7.controller;

import com.group7.model.TowerDefenseModel;


public class TowerDefenseController {

    private TowerDefenseModel model;

    public TowerDefenseController(TowerDefenseModel model) {

        this.model = model;
    }

    /**
     * 
     * @param monster monster whose health is to be modified
     * @param delta change in health. positive to add health; negative to subtract.
     * @return true if monster health > 0; false if not.
     */
    public boolean modifyHealth(TowerDefenseMonster monster, int delta) {

        int newHealth = monster.getHealth() + delta;
        monster.setHealth(newHealth);
        if (newHealth > 0) {
            return true;
        } else return false;

    }

    /**
     * 
     * @param entity entity whose position is to be updated
     * @param xCoord new x coordinate
     * @param yCoord new y coordinate
     */
    public void updateEntityPosition(TowerDefenseEntity entity, int xCoord, int yCoord) {

        entity.setXCoord(xCoord);
        entity.setYCoord(yCoord);

    }

    /**
     * 
     * @param entity entity that is to be moved
     * @param deltaX number of units to be moved in x-direction 
     * @param deltaY numbe rof units to be moved in y-direction
     */
    public void moveEntity(TowerDefenseEntity entity, int deltaX, int deltaY) {
        
        int newXCoord = entity.getXCoord() + deltaX;
        int newYCoord = entity.getYCoord() + deltaY;

        updatePosition(entity, newXCoord, newYCoord);
    }

    public void placeTower() {
        // TODO: implement
    }

    public void spawnMonster() {
        // TODO: implement
    }

    public void purchaseTower() {
        // TODO: implement
    }

    /**
     * 
     * @param amount the amount of money to add
     * @return the amount of money following the operation
     */
    public int addMoney(int amount) {

        int newMoney = model.getMoney() + amount;

        model.setMoney(newMoney);

        return newMoney;

    }

    /**
     * 
     * @param amount the amount of money to be subtracted
     * @return the amount of money following the operation
     */
    public int subtractMoney(int amount) {

        int newMoney = model.getMoney() - amount;

        if (newMoney < 0) {
            // throw InsuffcientFundsException
        }

        return newMoney;

    }

}
