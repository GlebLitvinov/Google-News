package com.home.hierarchy;

import com.home.hierarchy.character.Hero;
import com.home.hierarchy.character.HeroException;
import com.home.hierarchy.race.Human;
import com.home.hierarchy.race.Undead;
import com.home.hierarchy.role.Paladin;
import com.home.hierarchy.role.RoleException;
import com.home.hierarchy.spell.SpellException;

public class Main {

    public static void main(String[] args) throws SpellException, RoleException, HeroException {
	// write your code here
        Paladin paladin = new Paladin();
        Human undead = new Human();
        Hero hero = new Hero("MyHero",undead,paladin,10);
        hero.cast();
    }
}
