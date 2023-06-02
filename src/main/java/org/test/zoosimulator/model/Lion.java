package org.test.zoosimulator.model;

public class Lion extends Animal {
    public Lion(String name, int age, int health, int energy) {
        super(name, age, health, energy);
    }

    @Override
    public void makeSound() {
        System.out.println("Roarr !!");
    }
}
