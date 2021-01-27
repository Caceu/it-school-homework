package com.company;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Честно, я не знаю что сюда вписать, закинул какую-то фигню по приколу
        Hero[] heroes = new Hero[2];
        heroes[0] = new Hero();
        heroes[0].setDamageLevel(10);
        heroes[1] = new Hero(2, 0, 0);
        heroes[0].attack(heroes[1]);
        heroes[0].buy(1);
        System.out.println(heroes[0]);
        System.out.println(heroes[1]);

        Hero[] heroes1 = new Hero[3];
        for (int i = 0; i < 3; i++) {
            heroes1[i] = new Hero(3, i - 1, 0);
            heroes1[i].attack(heroes[0]);
        }
        Random rand = new Random();
        heroes1[rand.nextInt(3)].attack(heroes[0]);
        System.out.println(heroes[0]);
    }
}
