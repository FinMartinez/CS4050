/**
 * Project Name: Tower of Hanoi
 * File Name: TowerOfHanoi.java
 * Author: Fin Martinez, with help from ChatGPT and CoPilot
 * Date: 9 September 2024
 * Description: [Brief description of the purpose and functionality of the class or file]
 * (Uses  multi-recursive recursion with a divide and conquer strategy. 
 * Specifically, it is indirect multi-branch recursion because the function makes two recursive calls to itself within each function call.)
 * Version: 1.0
 */

    /* Must prompt user for number of disks. Rods will just be three
     * Define if the recursion is mutual, linear, tail, etc.
     * Minimum 2 classes, one for the main and one for the recursive function
     * Include time taken to solve the puzzle and menu for user to input number of disks and validate input
     */

import java.util.Scanner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;

        while (true) {
            System.out.println("Enter the number of disks (must be a positive integer): ");
            try {
                if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    break; // Validation passed, exit loop
                } else {
                    System.out.println("Please enter a positive integer greater than 0.");
                }
            } 
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        towerOfHanoi(n, 'A', 'C', 'B');
        scanner.close();
    }

    /**
     * Recursive function to solve the Tower of Hanoi puzzle.
     *
     * @param n Number of disks
     * @param fromRod Source rod
     * @param toRod   Destination rod
     * @param auxRod  Auxiliary rod
     */

    public static void towerOfHanoi(int n, char fromRod, char toRod, char auxRod) {
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


