package com.home.hierarchy.spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellFactory {
    public static List<Spell> getSpells(File file) throws FileNotFoundException, SpellException {
        Scanner scanner = new Scanner(file);
        List<Spell> spellList = new ArrayList<>();
        while (scanner.hasNext()){
            String name = scanner.next();
            int heal = scanner.nextInt();
            int healthReduction = scanner.nextInt();
            int damage = scanner.nextInt();
            spellList.add(new Spell(name,heal,healthReduction,damage));
        }
        return spellList;
    }
}
