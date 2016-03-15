package com.home.hierarchy.character;

import com.home.hierarchy.race.Human;
import com.home.hierarchy.race.Race;
import com.home.hierarchy.race.Undead;
import com.home.hierarchy.role.Paladin;
import com.home.hierarchy.role.Role;
import com.home.hierarchy.role.Warlock;
import com.home.hierarchy.spell.Spell;

import java.lang.*;

public class Hero extends Character {
    private Role role;
    private int level;

    public Hero(String name, Race race, Role role, int level) throws HeroException {
        super(name, role.getMaxHealth(), race);
        if (race instanceof Human && role instanceof Warlock
                || race instanceof Undead && role instanceof Paladin) {
            throw new HeroException("Race and role are incommensurable");
        }
        if (level < 1 || level > 10) {
            throw new HeroException("Level must be between 1 and 10");
        }
        this.role = role;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void cast() {
        System.out.println(role.spellDescription(name));
        Spell spell = role.getSpell();
        heal(spell.getHeal() + level);
        onDamageTaken(spell.getHealthReduction() - level);
        onAttack(spell.getDamage() + level);
    }

    public void heal(int heal) {
        System.out.println(String.format("%s restores %s health\n", name, heal));
        super.setHealth(Math.min(getHealth() + heal, role.getMaxHealth()));
    }

    public void onAttack(int damage) {
        System.out.println(String.format("%s deals %s damage\n", name, damage));
    }
}
