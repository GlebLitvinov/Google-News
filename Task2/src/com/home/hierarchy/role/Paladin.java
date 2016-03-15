package com.home.hierarchy.role;

import com.home.hierarchy.character.Creature;
import com.home.hierarchy.character.Hero;
import com.home.hierarchy.spell.Spell;
import com.home.hierarchy.spell.SpellException;

public class Paladin extends Role {
    public static final int PALADIN_MAX_HEALTH = 100;

    public Paladin(Spell spell) throws RoleException {
        super(spell, PALADIN_MAX_HEALTH);
        if (spell.getHeal() < 1) {
            throw new RoleException("Paladin's spell must heal");
        }
        if(spell.getHealthReduction() > 0){
            throw  new RoleException("Paladin's spell can't deal damage to owner");
        }
    }

    public Paladin() throws RoleException, SpellException {
        super(new Spell("Holy light", 10, 0, 10), PALADIN_MAX_HEALTH);
    }

    @Override
    public void castSpell(Hero from, Creature to) {
        int bonus = from.getLevel();
        int damage = getSpell().getDamage();
        if(damage > 0 && null != to) {
            to.onDamageTaken(getSpell().getDamage() + bonus);
        }
        from.heal(getSpell().getHeal()+bonus);
    }
}
