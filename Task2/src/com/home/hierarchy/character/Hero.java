package com.home.hierarchy.character;

import com.home.hierarchy.CombatLog;
import com.home.hierarchy.race.Race;
import com.home.hierarchy.role.Paladin;
import com.home.hierarchy.role.Role;
import com.home.hierarchy.role.Warlock;

import java.lang.*;

public class Hero extends Character {
    private Role role;
    private int level;

    public Hero(String name, Race race, Role role, int level) throws HeroException {
        super(name, role.getMaxHealth(), race);
        if (race == Race.HUMAN && role instanceof Warlock
                || race == Race.UNDEAD && role instanceof Paladin) {
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

    public void cast(Creature creature) {
        CombatLog.log(this,creature,role.getSpell());
        role.castSpell(this,creature);
    }

    public void heal(int heal) {
        super.setHealth(Math.min(getHealth() + heal, role.getMaxHealth()));
    }
}
