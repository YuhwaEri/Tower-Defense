package com.group7.model.Tower;

public class Tower3  extends Tower{
    private int size;
    private int cost;
    private int attack;
    private int attackSpeed;
    private String picture;
    private String name;


    public Tower3(){
        this.size = 1;
        this.cost = 125;
        this.attack = 125;
        this.attackSpeed = 1;
        this.picture = "src/main/resources/tower3.png";
        this.name = "Tower3";
    }
}
