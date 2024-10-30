package DFSMaze;

import java.util.*;

public class MazeGenerator {
    private int width, height;
    private Cell[][] grid;
    private Stack<Cell> stack = new Stack<>();
    private Random rand = new Random();

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];

        // Initialize grid with cells
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
    }

    public void generateMaze() {
        Cell start = grid[0][0]; // Start from the top-left corner
        start.visited = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            Cell next = getRandomUnvisitedNeighbor(current);

            if (next != null) {
                next.visited = true;
                removeWalls(current, next);
                stack.push(next);
            } else {
                stack.pop();
            }
        }
    }

    private Cell getRandomUnvisitedNeighbor(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        int x = cell.x;
        int y = cell.y;

        // Top neighbor
        if (y > 0 && !grid[x][y - 1].visited) neighbors.add(grid[x][y - 1]);
        // Right neighbor
        if (x < width - 1 && !grid[x + 1][y].visited) neighbors.add(grid[x + 1][y]);
        // Bottom neighbor
        if (y < height - 1 && !grid[x][y + 1].visited) neighbors.add(grid[x][y + 1]);
        // Left neighbor
        if (x > 0 && !grid[x - 1][y].visited) neighbors.add(grid[x - 1][y]);

        if (neighbors.isEmpty()) return null;
        return neighbors.get(rand.nextInt(neighbors.size()));
    }

    private void removeWalls(Cell current, Cell next) {
        int dx = current.x - next.x;
        int dy = current.y - next.y;

        // Remove walls between current and next cell
        if (dx == 1) { // Next is left of current
            current.walls[3] = false;
            next.walls[1] = false;
        } else if (dx == -1) { // Next is right of current
            current.walls[1] = false;
            next.walls[3] = false;
        } else if (dy == 1) { // Next is above current
            current.walls[0] = false;
            next.walls[2] = false;
        } else if (dy == -1) { // Next is below current
            current.walls[2] = false;
            next.walls[0] = false;
        }
    }

    public void printMaze() {
        // Print the maze to console
        for (int y = 0; y < height; y++) {
            // Print the top row of walls
            for (int x = 0; x < width; x++) {
                System.out.print(grid[x][y].walls[0] ? "+---" : "+   ");
            }
            System.out.println("+");

            // Print the left walls and spaces
            for (int x = 0; x < width; x++) {
                System.out.print(grid[x][y].walls[3] ? "|   " : "    ");
            }
            System.out.println("|");
        }

        // Print the bottom row of walls
        for (int x = 0; x < width; x++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
