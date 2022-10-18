package com.group7.model.Monster;

public class Monster {
    private int health;
    private int movespeed;
    private int size;
    private int Height;
    private int attack;
    private int attack_speed;
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
        return movespeed;
    }
    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getHeight() {
        return Height;
    }
    public void setHeight(int height) {
        Height = height;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getAttack_speed() {
        return attack_speed;
    }
    public void setAttack_speed(int attack_speed) {
        this.attack_speed = attack_speed;
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
