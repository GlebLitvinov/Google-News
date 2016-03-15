package com.home.hierarchy.role;

import com.home.hierarchy.spell.Spell;
import com.home.hierarchy.spell.SpellException;

public class Warlock extends Role {
    public static final int WARLOCK_MAX_HEALTH = 80;

    public Warlock(Spell spell) throws RoleException {
        super(spell, WARLOCK_MAX_HEALTH);
        if (spell.getHeal() > 0) {
            throw new RoleException("Warlock can't have spell with heal");
        }
    }

    public Warlock() throws RoleException, SpellException {
        super(new Spell("Dark pact", 0, 13, 20), WARLOCK_MAX_HEALTH);
    }

}
