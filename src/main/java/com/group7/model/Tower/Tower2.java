package com.group7.model.Tower;

public class Tower2 extends Tower{
    private int size;
    private int cost;
    private int attack;
    private int attack_speed;
    private String picture;
    private String name;


    public Tower2(){
        this.size = 1;
        this.cost = 80;
        this.attack = 25;
        this.attack_speed = 2;
        this.picture = "src/main/resources/tower2.png";
        this.name = "Tower2";
    }
}
