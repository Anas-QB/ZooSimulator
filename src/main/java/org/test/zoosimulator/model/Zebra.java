package org.test.zoosimulator.model;

public class Zebra extends Animal {
    public Zebra(String name, int age, int health, int energy) {
        super(name, age, health, energy);
    }

    @Override
    public void makeSound() {
        System.out.println("Brahh !!");
    }
}
