package com.home.legohouse.model.lego;

import com.home.legohouse.infrastructure.lego.HouseIdGenerator;
import com.home.legohouse.model.lego.part.LegoPart;

import java.util.HashMap;

public class LegoHouse {

    private final int id;
    private final String name;
    private final int collectorId;
    private final int number;

    private HashMap<LegoPart, Integer> parts;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public int getNumber() {
        return number;
    }

    public HashMap<LegoPart, Integer> getParts() {
        return parts;
    }


    public LegoHouse(int collectorId, String name, int number) {
        parts = new HashMap<>();
        this.collectorId = collectorId;
        this.number = number;
        id = HouseIdGenerator.generateId();
        int length = name.length();
        if (length > 3 && length < 21) {
            this.name = name;
        } else {
            this.name = "House#" + collectorId;
        }
    }

    public LegoHouse(int collectorId, String name) {
        this(collectorId, name, 1);
    }

    public void newPart(LegoPart part, int number) {
        try {
            if (number < 0) {
                throw new IllegalArgumentException("Number of parts can't be less then 0");
            }
            int oldNumber = 0;
            if (parts.containsKey(part)) {
                oldNumber = parts.get(part);
            } else if (parts.keySet().size() == 5) {
                throw new IllegalArgumentException("Home is already built");
            }
            parts.put(part, oldNumber + number);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void newPart(LegoPart part) {
        newPart(part, 1);
    }


}
