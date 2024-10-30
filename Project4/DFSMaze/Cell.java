package DFSMaze;
public class Cell {
    int x, y;
    boolean visited = false;
    boolean[] walls = {true, true, true, true}; // Top, Right, Bottom, Left

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
