/**
 * Project Name: Tower of Hanoi
 * File Name: TowerOfHanoi.java
 * Author: Fin Martinez, with help from ChatGPT and CoPilot
 * Date: 9 September 2024
 * Description: This program solves the Tower of Hanoi puzzle for a given number of disks.
 *  This program utilizes a multi-branched recursive algorithm to solve the Tower of Hanoi puzzle.
 *  There are two classes in this program: TowerOfHanoi and HanoiProcessor.
 *      1. The TowerOfHanoi class contains the main method and user interface for the program. 
 *         This method also validates user input for the number of disks.
 *      2. The HanoiProcessor class contains the recursive algorithm to solve the Tower of Hanoi puzzle.
 *         The solveTowerOfHanoi method initiates the recursive process, and the towerOfHanoi method is the recursive function.
 * Version: 1.0
 */

import java.util.Scanner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HanoiProcessor hanoiProcessor = new HanoiProcessor();

        boolean exitProgram = false;

        while (!exitProgram){
            // User Menu
            System.out.println("\nTower of Hanoi Solver");
            System.out.println("1. Solve Tower of Hanoi");
            System.out.println("2. Exit");
            System.out.println("Enter your choice (1-2): ");

            String userSelection = scanner.nextLine();

            switch (userSelection) {
                case "1":
                    int numberOfDisks = getValidNumberOfDisks(scanner);

                    //Measure processing time for solving the Tower of Hanoi puzzle
                    long startTime = System.currentTimeMillis(); // Start time
                    hanoiProcessor.solveTowerOfHanoi(numberOfDisks);
                    long endTime = System.currentTimeMillis(); // End time
                    long duration = endTime - startTime; // Calculate duration

                    System.out.println("Time taken to solve the Tower of Hanoi puzzle with " 
                        + numberOfDisks + " disks: " + duration + " milliseconds");
                    break;

                case "2":
                    exitProgram = true;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }

    /* 
     * Get a valid number of disks from the user.
     *
     * @param scanner Scanner object for user input
     * @return Valid number of disks
     */

    private static int getValidNumberOfDisks(Scanner scanner) {
        int numberOfDisks = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the number of disks (must be a positive integer): ");
            String userInput = scanner.nextLine();

            try {
                numberOfDisks = Integer.parseInt(userInput);
                if (numberOfDisks > 0) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a positive integer greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        return numberOfDisks;
    }
}

class HanoiProcessor {

    /**
     * Solve the Tower of Hanoi puzzle with the given number of disks.
     * 
     * @param n The number of disks
     */

    public void solveTowerOfHanoi(int n) {
        System.out.println("Solving Tower of Hanoi with " + n + " disks...");
        towerOfHanoi(n, 'A', 'C', 'B'); //Call recursive function
    }
        

    /**
     * Recursive function to solve the Tower of Hanoi puzzle.
     *
     * @param n Number of disks
     * @param fromRod Source rod
     * @param toRod   Destination rod
     * @param auxRod  Auxiliary rod
     */

    private void towerOfHanoi(int n, char fromRod, char toRod, char auxRod) {
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + fromRod + " to rod " + toRod);
            return;
        }
        // Step 1: Move (n-1) disks from source to auxiliary, using destination as a buffer
        towerOfHanoi(n - 1, fromRod, auxRod, toRod);
        
        // Step 2: Move the nth (largest) disk from source to destination
        System.out.println("Move disk " + n + " from " + fromRod + " to " + toRod);
        
        // Step 3: Move the (n-1) disks from auxiliary to destination, using source as a buffer
        towerOfHanoi(n - 1, auxRod, toRod, fromRod);
    }
    
}


