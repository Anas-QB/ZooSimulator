package org.test.zoosimulator.model;

import java.util.*;

public class Zoo {
    private List<Animal> animals;
    private boolean isOpen;

    public Zoo() {
        this.animals = new LinkedList<>();
        this.isOpen = false;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Animal selectAnimal() {
        System.out.println("Which animal do you want to feed?");
        // listing all animal names
        int i = 0;
        for (Animal animal : this.animals) {
            System.out.println((i + 1) + ": " + animal.getName());
            i++;
        }

        System.out.println("Select the number");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice <= 0 || choice > animals.size())
            throw new IllegalArgumentException("Invalid choice !!");

        return animals.get(choice - 1);
    }

    public void feedAnimal(Animal animal) {
        if (!this.animals.contains(animal)) {
            throw new IllegalArgumentException("Unknown animal");
        }

        if (!this.isOpen) {
            System.out.println("Zoo is closed !!");
            return;
        }

        System.out.println("Feeding " + animal.getName() + "...");
        animal.eat();
        animal.makeSound();
    }

    public void zookeeperFeedAnimal() {
        if (!this.isOpen) {
            System.out.println("Zoo is closed!");
        }

        if (this.animals.isEmpty()) {
            System.out.println("No animals in the zoo!");
            return;
        }

        Animal animalToFeed = this.animals.stream()
                .max(Comparator.comparingDouble(Animal::getHealth).thenComparingDouble(Animal::getEnergy))
                .orElse(null);

        if (animalToFeed != null) {
            System.out.printf("Zookeeper is feeding %s [ Health: %d, Energy: %d]%n", animalToFeed.getName(), animalToFeed.getHealth(), animalToFeed.getEnergy());
            animalToFeed.eat();
        } else {
            System.out.println("No animal found to feed!");
        }
    }

    public void open() {
        if (this.isOpen) {
            System.out.println("The zoo is already open");
            return;
        }

        this.isOpen = true;
        for (Animal animal : this.animals) {
            animal.play();
        }
    }

    public void close() {
        if (!this.isOpen) {
            System.out.println("The zoo is already closed");
            return;
        }

        this.isOpen = false;
        for (Animal animal : this.animals) {
            animal.sleep();
        }
    }

    public void printDailyReport() {
        if (this.animals.isEmpty()) {
            System.out.println("No animals in the zoo!");
            return;
        }

        for (Animal animal : this.animals) {
            System.out.println("Name: " + animal.getName());
            System.out.println("Age: " + animal.getAge());
            System.out.println("Health: " + animal.getHealth());
            System.out.println("Energy: " + animal.getEnergy());
            System.out.println("===============");
        }
    }


}

