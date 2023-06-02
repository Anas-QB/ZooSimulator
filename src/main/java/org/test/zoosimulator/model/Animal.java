package org.test.zoosimulator.model;

public abstract class Animal {
    private String name;
    private int age;
    private int health;
    private int energy;

    public Animal(String name, int age, int health, int energy) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    // Animal actions - eat, sleep, play, make sound
    public void eat() {
        this.energy++;
        this.health++;
    }

    public void sleep() {
        this.energy++;
    }

    public void play() {
        this.energy--;
    }

    public abstract void makeSound();
}
