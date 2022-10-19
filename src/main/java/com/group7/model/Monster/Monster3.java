package com.group7.model.Monster;

public class Monster3 {
    private int health;
    private int moveSpeed;
    private int size;
    private int height;
    private int attack;
    private int attackSpeed;
    private String picture;
    private String name;

    public Monster3(){
        this.health = 300;
        this.moveSpeed = 2;
        this.height = 0;
        this.attackSpeed = 1;
        this.size = 1;
        this.attack = 15;
        this.picture = "src/main/resources/monster3.png";
        this.name = "monster3";
    }
}
