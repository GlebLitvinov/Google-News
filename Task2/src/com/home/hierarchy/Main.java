package com.home.hierarchy;

import com.home.hierarchy.character.Creature;
import com.home.hierarchy.character.Hero;
import com.home.hierarchy.character.HeroException;
import com.home.hierarchy.race.Race;
import com.home.hierarchy.role.Paladin;
import com.home.hierarchy.role.RoleException;
import com.home.hierarchy.spell.SpellException;

public class Main {

    public static void main(String[] args) throws SpellException, RoleException, HeroException {
	// write your code here
        Paladin paladin = new Paladin();
        Race undead = Race.HUMAN;
        Hero hero = new Hero("MyHero",undead,paladin,10);
        Creature troll = new Creature("Troll",50);
        hero.cast(troll);
    }
}
