package com.home.hierarchy.role;

import com.home.hierarchy.spell.Spell;
import com.home.hierarchy.spell.SpellException;

public class Paladin extends Role {
    public static final int PALADIN_MAX_HEALTH = 100;

    public Paladin(Spell spell) throws RoleException {
        super(spell, PALADIN_MAX_HEALTH);
        if (spell.getHeal() <= 0) {
            throw new RoleException("Paladin's spell must heal");
        }
    }

    public Paladin() throws RoleException, SpellException {
        super(new Spell("Holy light", 10, 0, 10), PALADIN_MAX_HEALTH);
    }
}
