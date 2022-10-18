package com.group7.model.Tower;

public class Tower {
    private int size;
    private int cost;
    private int attack;
    private int attack_speed;
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
