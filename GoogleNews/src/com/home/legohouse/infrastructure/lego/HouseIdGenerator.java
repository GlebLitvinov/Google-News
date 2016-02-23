package com.home.legohouse.infrastructure.lego;

public final class HouseIdGenerator {

    private static int id = 10000;

    private HouseIdGenerator() {
    }

    public static int generateId() {
        if (id == 50001) {
            id = 10000;
        }
        return id++;
    }

}

