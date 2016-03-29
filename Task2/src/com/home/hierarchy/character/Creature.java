package com.home.hierarchy.character;

import com.home.hierarchy.race.Race;

public class Creature {
    private String name;
    private int health;
    private boolean dead;

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
        this.dead = false;
    }

    public boolean isDead() {
        return dead;
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
        if(isDead()) return;
        health-=damage;
        if(health < 0) onDeath();
    }

    private void onDeath() {
        dead = true;
        System.out.println(name + ": I'm dead");
    }
}
