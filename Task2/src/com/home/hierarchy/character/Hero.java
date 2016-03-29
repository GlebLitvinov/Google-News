package com.home.hierarchy.character;

import com.home.hierarchy.CombatLog;
import com.home.hierarchy.race.Race;
import com.home.hierarchy.role.RoleEnum;

import java.lang.*;

public class Hero extends CharacterCreature {
    private RoleEnum role;
    private int level;

    public Hero(String name, Race race, RoleEnum role, int level) throws HeroException {
        super(name, role.getMaxHealth(), race);
        if (race == Race.HUMAN && role == RoleEnum.WARLOCK
                || race == Race.UNDEAD && role == RoleEnum.PALADIN) {
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

    public String cast(Creature creature) {
        String log = CombatLog.getLog(this,creature,role.getSpell());
        role.castSpell(this,creature);
        return log;
    }

    public void heal(int heal) {
        super.setHealth(Math.min(getHealth() + heal, role.getMaxHealth()));
    }
}
