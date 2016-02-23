package com.home.legohouse;

import com.home.legohouse.infrastructure.CheckPrinter;
import com.home.legohouse.model.lego.LegoHouse;
import com.home.legohouse.model.lego.part.LegoPart;
import com.home.legohouse.model.lego.part.LegoPartsFactory;

public class Main {

    public static void main(String[] args) {
        LegoHouse house = new LegoHouse(666,"Dreamhouse",2);
        LegoPart part = LegoPartsFactory.createPart("Roof");
        house.newPart(part);
        part = LegoPartsFactory.createPart("Door");
        house.newPart(part,3);
        part = LegoPartsFactory.createPart("Floor");
        house.newPart(part);
        part = LegoPartsFactory.createPart("window");
        house.newPart(part);
        part = LegoPartsFactory.createPart("WALL INNER ");
        house.newPart(part);
        part = LegoPartsFactory.createPart("WALL outer");
        house.newPart(part);
        CheckPrinter.printCheck(house);
    }
}
