package com.home.legohouse.model.lego.part;

public class LegoPart {
    private final String name;
    private double cost;

    protected LegoPart(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return String.format("%s(%s $)", name, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof LegoPart) {
            LegoPart part = (LegoPart) obj;
            String name = part.getName();
            double cost = part.getCost();
            return name.equals(this.name) && cost == this.cost;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash += name.hashCode();
        hash = hash * 30 + Double.hashCode(cost);
        return hash;
    }
}
