package com.home.hierarchy.role;

import com.home.hierarchy.character.Hero;
import com.home.hierarchy.spell.Spell;

public abstract class Role {
    private Spell spell;
    private int maxHealth;

    public Role(Spell spell, int maxHealth) throws RoleException {
        this.maxHealth = maxHealth;
        this.spell = spell;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Spell getSpell() {
        return spell;
    }

    public String spellDescription(String heroName){
        StringBuilder description = new StringBuilder();
        description.append(String.format("%s casts %s!\n",heroName,spell.getName()));
        return description.toString();
    }
}
