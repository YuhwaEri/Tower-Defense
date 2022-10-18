package com.group7.model.Monster;

public class Monster2 extends Monster{
    private int health;
    private int movespeed;
    private int size;
    private int Height;
    private int attack;
    private int attack_speed;
    private String picture;
    private String name;

    public Monster2(){
        this.health = 300;
        this.movespeed = 2;
        this.Height = 0;
        this.attack_speed = 1;
        this.size = 1;
        this.attack = 15;
        this.picture = "src/main/java/com/group7/model/Monster/monster2.png";
        this.name = "monster2";
    }
}
