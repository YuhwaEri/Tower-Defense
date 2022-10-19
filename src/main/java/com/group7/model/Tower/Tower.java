package com.group7.model.Tower;

public class Tower {
    private int size;
    private int cost;
    private int attack;
    private int attackSpeed;
    private String picture;
    private String name;

    public Tower(){
        
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
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
    public int getAttack_speed() {
        return attackSpeed;
    }
    public void setAttack_speed(int attack_speed) {
        this.attackSpeed = attack_speed;
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
