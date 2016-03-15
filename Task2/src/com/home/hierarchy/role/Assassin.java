package com.home.hierarchy.role;

import com.home.hierarchy.spell.Spell;
import com.home.hierarchy.spell.SpellException;

public class Assassin extends Role {
    public static final int ASSASSINS_MAX_HEALTH = 60;

    public Assassin(Spell spell) throws RoleException {
        super(spell, ASSASSINS_MAX_HEALTH);
        if (spell.getHealthReduction() > 0) {
            throw new RoleException("Assassin can't have spells with health reduction");
        }
    }

    public Assassin() throws RoleException, SpellException {
        super(new Spell("Shadow blade", 0, 0, 20), ASSASSINS_MAX_HEALTH);

    }

}
