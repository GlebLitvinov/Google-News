package com.home.hierarchy.character;

import com.home.hierarchy.race.Race;

public class CharacterCreature extends Creature{
    private Race race;

    public Race getRace() {
        return race;
    }

    public CharacterCreature(String name, int health, Race race) {
        super(name,health);
        this.race = race;
    }
}
