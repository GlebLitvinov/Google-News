package com.home.hierarchy;

import com.home.hierarchy.character.Creature;
import com.home.hierarchy.character.Hero;
import com.home.hierarchy.spell.Spell;

public class CombatLog {
    public static void log(Hero from, Creature to, Spell spell) {
        int bonus = from.getLevel();
        int damage = spell.getDamage();
        int healthReduction = spell.getHealthReduction();
        int heal = spell.getHeal();
        System.out.println(String.format("%s casts %s", from.getName(), spell.getName()));
        if (heal > 0) {
            System.out.println(String.format("%s restores %s health", from.getName(), heal + bonus));
        }
        if(to == null) return;
        if(healthReduction > 0){
            System.out.println(String.format("%s deals %s damage to him/herself", from.getName(), healthReduction - bonus));
        }
        if(damage > 0){
            System.out.println(String.format("%s deals %s damage to %s", from.getName(), damage + bonus,to.getName()));
        }
    }
}
