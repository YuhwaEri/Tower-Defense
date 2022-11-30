package com.group7.model.Tower;

import com.group7.model.TowerDefenseEntity;

public class Tower extends TowerDefenseEntity {
    protected int towerID;
    protected TowerType type;

    protected int cost;
    protected int attack;
    protected int attackCooldown;
    protected double range;
    protected int damageDealt;
    protected int kills;
    protected int cooldownRemaining;

    public Tower(TowerType type, int towerID) {
        this.cost = type.getCost();
        this.attack = type.getAttack();
        this.attackCooldown = type.getAttackCooldown();
        this.range = type.getRange();
        this.damageDealt = 0;
        this.kills = 0;

        this.picturePath = type.getPicturePath();
        this.towerID = towerID;
        cooldownRemaining = 0;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttackCooldown() {
        return attackCooldown;
    }
    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public int getTowerID() {
        return towerID;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    public void addDamageDealt(int damageDealt) {
        setDamageDealt(this.damageDealt + damageDealt);
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int addKills(int kills) {
        int newKills = this.kills + kills;

        setKills(newKills);

        return newKills;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getCooldownRemaining() {

        return cooldownRemaining;
    }

    public void setCooldownRemaining(int cooldownRemaining) {

        this.cooldownRemaining = cooldownRemaining;

    }

    public boolean decrementCooldownRemaining(int n) {

        cooldownRemaining -= n;

        // if cooldown is not over
        if (cooldownRemaining > 0) {

            return false;

        // if cooldown is over
        } else {

            // reset cooldown
            cooldownRemaining = attackCooldown;

            return true;
        }

    }





}
