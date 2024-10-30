package DFSMaze;

import java.util.*;

public class DFSMaze {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("DFS Maze Generator");
            System.out.println("Please enter the dimensions of the maze (between 5 and 50).");
            System.out.println("");

            int width = getValidDimension(scanner, "width");
            int height = getValidDimension(scanner, "height");

    
            System.out.println("");
            MazeGenerator maze = new MazeGenerator(width, height);
            maze.generateMaze();
            maze.printMaze();

            System.out.println("----------------------------------------");
            System.out.print("Would you like to generate another maze? (Y/N): ");
            String choice = scanner.next().toUpperCase();

            if (!choice.equals("Y")) {
                System.out.println("Exiting...");
                break;
            }
        }
        scanner.close();
    }

    private static int getValidDimension(Scanner scanner, String dimensionType) {
        int dimension = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(dimensionType + ": ");
            try {
                dimension = scanner.nextInt();
                if (dimension >= 5 && dimension <= 50) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please enter a value between 5 and 50.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
        return dimension;
    }
}

