package com.home.hierarchy.character;

import com.home.hierarchy.race.Race;

public class Creature {
    public String name;
    int health;

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void onDamageTaken(int damage){
        health-=damage;
        if(health < 0) onDeath();
    }

    public void onAttack(int damage) {
        System.out.println("Charge!!!");
    }

    private void onDeath() {
        System.out.println("I'm dead");
    }
}
