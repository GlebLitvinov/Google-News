package com.home.legohouse.infrastructure;

import com.home.legohouse.model.lego.LegoHouse;
import com.home.legohouse.model.lego.part.LegoPart;

import java.util.Map;
import java.util.Set;

public class CheckPrinter {

    private CheckPrinter(){}

    public static void printCheck(LegoHouse house) {
        try {
            if (house.getParts().size() > 0) {
                System.out.println("==============================");
                System.out.println("Collector:  " + house.getCollectorId());
                System.out.println("Name:  " + house.getName());
                System.out.println("------------------------------");
                double totalCost = 0.;
                Set<Map.Entry<LegoPart, Integer>> entrySet = house.getParts().entrySet();
                for (Map.Entry<LegoPart, Integer> entry : entrySet) {
                    String name = entry.getKey().getName();
                    Integer count = entry.getValue();
                    double cost = entry.getKey().getCost() * count;
                    totalCost += cost;
                    System.out.print(String.format("%-15s%-10s%-4.2f$\n", name, count, cost));
                }
                System.out.println("------------------------------");
                int count = house.getNumber();
                System.out.println("Coust:  " + totalCost);
                System.out.println("Count:  " + count);
                System.out.println("------------------------------");
                System.out.println("Total cost: " + totalCost * count);
                System.out.println("==============================");
            } else print(house);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public static void print(LegoHouse house) {
        System.out.print(String.format("[%s : %s : %s : %s]\n",
                house.getCollectorId(), house.getId(), house.getName(), house.getNumber()));
    }
}
