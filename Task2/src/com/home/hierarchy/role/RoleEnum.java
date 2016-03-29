package com.home.hierarchy.role;

import com.home.hierarchy.character.Creature;
import com.home.hierarchy.character.Hero;
import com.home.hierarchy.spell.Spell;
import com.home.hierarchy.spell.SpellException;

public enum RoleEnum {
    PALADIN(new Spell("Holy light", 10, 0, 10)) {
        @Override
        public void castSpell(Hero from, Creature to) {
            int bonus = from.getLevel();
            int damage = getSpell().getDamage();
            if (damage > 0 && null != to) {
                to.onDamageTaken(getSpell().getDamage() + bonus);
            }
            from.heal(getSpell().getHeal() + bonus);
        }
    },
    WARLOCK(new Spell("Dark pact", 0, 13, 20)) {
        @Override
        public void castSpell(Hero from, Creature to) {
            if (null == to) return;
            int bonus = from.getLevel();
            int healthReduction = getSpell().getHealthReduction();
            if (healthReduction > 0) {
                from.onDamageTaken(healthReduction - bonus);
            }
            to.onDamageTaken(getSpell().getDamage() + bonus);
        }
    },
    ASSSSIN(new Spell("Shadow blade", 0, 0, 20)) {
        @Override
        public void castSpell(Hero from, Creature to) {
            int bonus = from.getLevel();
            int heal = getSpell().getHeal();
            if (heal > 0) {
                from.heal(heal + bonus);
            }
            if (null != to) {
                to.onDamageTaken(getSpell().getDamage() + bonus);
            }
        }
    };

    private Spell spell;
    private int maxHealth;
    public static final int ASSASSINS_MAX_HEALTH = 60;
    public static final int WARLOCK_MAX_HEALTH = 80;
    public static final int PALADIN_MAX_HEALTH = 100;


    RoleEnum(Spell spell) {
        this.spell = spell;
        switch (this) {
            case WARLOCK:
                this.maxHealth = WARLOCK_MAX_HEALTH;
                break;
            case ASSSSIN:
                this.maxHealth = ASSASSINS_MAX_HEALTH;
                break;
            case PALADIN:
                this.maxHealth = PALADIN_MAX_HEALTH;
                break;
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }


    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Spell getSpell() {
        return spell;
    }

    public abstract void castSpell(Hero from, Creature to);
}
