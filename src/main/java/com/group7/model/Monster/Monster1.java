package com.group7.model.Monster;

public class Monster1 extends Monster{
    private int health;
    private int moveSpeed;
    private int size;
    private int height;
    private int attack;
    private int attackSpeed;
    private String picture;
    private String name;

    public Monster1(){
        this.health = 500;
        this.moveSpeed = 1;
        this.height = 0;
        this.attackSpeed = 1;
        this.size = 1;
        this.attack = 10;
        this.picture = "src/main/resources/monster1.png";
        this.name = "monster1";
    }


}

