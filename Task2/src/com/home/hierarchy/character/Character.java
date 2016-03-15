package com.home.hierarchy.character;

import com.home.hierarchy.race.Race;

public class Character extends Creature{
    public Race race;

    public Character(String name,int health, Race race) {
        super(name,health);
        this.race = race;
    }
}
