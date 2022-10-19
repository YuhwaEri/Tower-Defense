package com.group7.model.Monster;

public class Monster {
    private int health;
    private int moveSpeed;
    private int size;
    private int height;
    private int attack;
    private int attackSpeed;
    private String picture;
    private String name;

    public Monster(){
        
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMovespeed() {
        return moveSpeed;
    }
    public void setMovespeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
