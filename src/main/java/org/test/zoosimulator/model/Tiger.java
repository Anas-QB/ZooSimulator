package org.test.zoosimulator.model;

public class Tiger extends Animal  {
    public Tiger(String name, int age, int health, int energy) {
        super(name, age, health, energy);
    }

    @Override
    public void makeSound() {
        System.out.println("Grrh !!");
    }
}
