package com.home.legohouse.model.lego.part;


public class LegoPartsFactory {

    public static LegoPart createPart(String name) throws IllegalArgumentException{
        LegoPart part;
        String lowerCaseName = name.toLowerCase();
        lowerCaseName = lowerCaseName.trim();
        switch (lowerCaseName){
            case "roof":
                part = new LegoPart("Roof",5);
                break;
            case "floor":
                part = new LegoPart("Floor",3);
                break;
            case "stairways":
                part = new LegoPart("Stairways",3);
                break;
            case "wall inner":
                part = new LegoPart("Wall inner",1);
                break;
            case "wall outer":
                part = new LegoPart("Wall outer",3);
                break;
            case "midfloor":
                part = new LegoPart("Midfloor",3);
                break;
            case "door":
                part = new LegoPart("Door",1);
                break;
            case "window":
                part = new LegoPart("Window ",0.5);
                break;
            default: throw new IllegalArgumentException("Such part does not exists");

        }
        return part;
    }
}
