package org.test.zoosimulator;

import org.test.zoosimulator.model.*;

import java.util.List;
import java.util.Scanner;

public class ZooSimulatorApplication {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        // Create some animals and add them to the zoo
        Lion lion = new Lion("Simbha", 5, 100, 80);
        Tiger tiger = new Tiger("Sher Khan", 8, 90, 70);
        Zebra zebra = new Zebra("Zara", 10, 80, 90);

        zoo.addAnimal(lion);
        zoo.addAnimal(tiger);
        zoo.addAnimal(zebra);

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("1. Open zoo");
            System.out.println("2. Close zoo");
            System.out.println("3. Feed animals");
            System.out.println("4. AI-based feeding");
            System.out.println("5. Print daily report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> zoo.open();
                case 2 -> zoo.close();
                case 3 -> zoo.feedAnimal(zoo.selectAnimal());
                case 4 -> zoo.zookeeperFeedAnimal();
                case 5 -> zoo.printDailyReport();
                case 6 -> isRunning = false;
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }


}