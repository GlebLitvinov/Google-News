package com.home.hierarchy.spell;

import com.home.hierarchy.role.Assassin;

public class Spell {
    private String name;
    private int heal;
    private int healthReduction;
    private int damage;

    public Spell(String name, int heal, int healthReduction, int damage) throws SpellException {
        int imba = Assassin.ASSASSINS_MAX_HEALTH / 2;
        if (heal > imba || healthReduction > imba || damage > imba) {
            throw new SpellException("Spell is not balanced");
        }
        if (heal < 0 || healthReduction < 0 || damage < 0){
            throw new SpellException("Spell power can't be negative");
        }
        if(healthReduction != 0 && healthReduction < 10){
            throw new SpellException("Health reduction mus be not less then 10");
        }
        this.name = name;
        this.heal = heal;
        this.healthReduction = healthReduction;
        this.damage = damage;
    }

    public int getHeal() {
        return heal;
    }

    public int getHealthReduction() {
        return healthReduction;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
