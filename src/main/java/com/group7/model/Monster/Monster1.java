package com.group7.model.Monster;

public class Monster1 extends Monster{
    private int health;
    private int movespeed;
    private int size;
    private int Height;
    private int attack;
    private int attack_speed;
    private String picture;
    private String name;

    public Monster1(){
        this.health = 500;
        this.movespeed = 1;
        this.Height = 0;
        this.attack_speed = 1;
        this.size = 1;
        this.attack = 10;
        this.picture = "src/main/resources/monster1.png";
        this.name = "monster1";
    }


}

