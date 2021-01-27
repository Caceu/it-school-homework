package com.company;

import java.util.Arrays;
import java.util.Objects;

public class Hero {
    private int experience;
    private double damageLevel;
    private double health;
    private double speed;
    private int type;
    private int[] abilities;
    private int abilitiesSize;
    private int[] items;
    private int inventorySize;
    private int gold;
    private Location location;
    private boolean isDefending;
    private double heathMax;

    @Override
    public String toString() {
        return "Hero{\n" +
                "experience=" + experience + ",\n" +
                "damageLevel=" + damageLevel + ",\n" +
                "health=" + health + ",\n" +
                "speed=" + speed + ",\n" +
                "type=" + type + ",\n" +
                "abilities=" + Arrays.toString(abilities) + ",\n" +
                "items=" + Arrays.toString(items) + ",\n" +
                "gold=" + gold + ",\n" +
                "location=" + location + ",\n" +
                "}";
    }

    public Hero(Location location) {
        this.location = location;
        this.experience = 0;
        this.damageLevel = 1;
        this.health = 10;
        this.speed = 1;
        this.type = 0;
        this.abilitiesSize = 5;
        this.abilities = new int[abilitiesSize];
        this.inventorySize = 10;
        this.items = new int[inventorySize];
        this.gold = 15;
        this.isDefending = false;
        this.heathMax = 100;
    }

    public Hero(double x, double y, double z) {
        this.location = new Location(x, y, z);
        this.experience = 0;
        this.damageLevel = 1;
        this.health = 10;
        this.speed = 1;
        this.type = 0;
        this.abilitiesSize = 5;
        this.abilities = new int[abilitiesSize];
        for(int i = 0; i < this.abilitiesSize; i++) {
            this.abilities[i] = -1;
        }
        this.inventorySize = 10;
        this.items = new int[inventorySize];
        for(int i = 0; i < this.inventorySize; i++) {
            this.items[i] = -1;
        }
        this.gold = 15;
        this.isDefending = false;
        this.heathMax = 100;
    }

    public Hero() {
        this.experience = 0;
        this.damageLevel = 1;
        this.health = 10;
        this.speed = 1;
        this.type = 0;
        this.abilitiesSize = 5;
        this.abilities = new int[abilitiesSize];
        this.inventorySize = 10;
        this.items = new int[inventorySize];
        this.gold = 15;
        this.location = new Location();
        this.isDefending = false;
        this.heathMax = 100;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(double damageLevel) {
        this.damageLevel = damageLevel;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public void setAbilities(int[] abilities) {
        this.abilities = abilities;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAbilitiesSize() {
        return abilitiesSize;
    }

    public void setAbilitiesSize(int abilitiesSize) {
        this.abilitiesSize = abilitiesSize;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public double getHeathMax() {
        return heathMax;
    }

    public void setHeathMax(double heathMax) {
        this.heathMax = heathMax;
    }

    public boolean isDefending() {
        return isDefending;
    }

    public void setDefending(boolean defending) {
        this.isDefending = defending;
    }

    public boolean equals(Hero hero) {
        if (this == hero) return true;
        if (hero == null || getClass() != hero.getClass()) return false;
        return experience == hero.experience && Double.compare(hero.damageLevel, damageLevel) == 0 && Double.compare(hero.health, health) == 0 && Double.compare(hero.speed, speed) == 0 && type == hero.type && abilitiesSize == hero.abilitiesSize && inventorySize == hero.inventorySize && gold == hero.gold && Arrays.equals(abilities, hero.abilities) && Arrays.equals(items, hero.items) && location.equals(hero.location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return experience == hero.experience && Double.compare(hero.damageLevel, damageLevel) == 0 && Double.compare(hero.health, health) == 0 && Double.compare(hero.speed, speed) == 0 && type == hero.type && abilitiesSize == hero.abilitiesSize && inventorySize == hero.inventorySize && gold == hero.gold && Arrays.equals(abilities, hero.abilities) && Arrays.equals(items, hero.items) && location.equals(hero.location);
    }

    public boolean buy(int item) {
        if(this.gold >= 25) {
            for(int i = 0; i < this.inventorySize; i++){
                if(this.items[i] == -1) {
                    this.items[i] = item;
                    this.gold -= 25;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean damage(double damage) {
        this.health -= damage;
        if(this.health < 0)
            this.health = 0;
        return this.health <= 0;
    }

    public boolean attack(Hero otherHero) {
        return otherHero.damage(this.damageLevel);
    }

    public boolean defense() {
        if(this.isDefending) {
            this.isDefending = false;
        }
        else {
            this.health = this.heathMax;
            this.isDefending = true;
        }
        return this.isDefending;
    }

    public boolean carry(int item) {
        for(int i = 0; i < this.inventorySize; i++){
            if(this.items[i] == -1) {
                this.items[i] = item;
                return true;
            }
        }
        return false;
    }

    public boolean respawn() {
        if(gold >= 50) {
            this.health = this.heathMax;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean move(Location newLocation) {
        if (this.health > 0) {
            this.location = newLocation;
            return true;
        }
        return false;
    }
}
