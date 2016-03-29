package com.home.hierarchy;

import com.home.hierarchy.character.Creature;
import com.home.hierarchy.character.Hero;
import com.home.hierarchy.spell.Spell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CombatLog {

    public static String getLog(Hero from, Creature to, Spell spell) {
        StringBuilder builder = new StringBuilder();
        int bonus = from.getLevel();
        int damage = spell.getDamage();
        int healthReduction = spell.getHealthReduction();
        int heal = spell.getHeal();
        builder.append(String.format("%s casts %s\n", from.getName(), spell.getName()));
        if (heal > 0) {
            builder.append(String.format("%s restores %s health\n", from.getName(), heal + bonus));
        }
        if(to == null) return builder.toString();
        if(healthReduction > 0){
            builder.append(String.format("%s deals %s damage to him/herself\n", from.getName(), healthReduction - bonus));
        }
        if(damage > 0){
            builder.append(String.format("%s deals %s damage to %s\n", from.getName(), damage + bonus,to.getName()));
        }
        return  builder.toString();
    }

}
