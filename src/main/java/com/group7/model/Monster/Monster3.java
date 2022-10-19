package com.group7.model.Monster;

public class Monster3 {
    private int health;
    private int movespeed;
    private int size;
    private int Height;
    private int attack;
    private int attack_speed;
    private String picture;
    private String name;

    public Monster3(){
        this.health = 300;
        this.movespeed = 2;
        this.Height = 0;
        this.attack_speed = 1;
        this.size = 1;
        this.attack = 15;
        this.picture = "src/main/resources/monster3.png";
        this.name = "monster3";
    }
}
