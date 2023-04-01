package algorithms.mazeGenerators;

public class MyMazeGenerator extends AMazeGenerator{
    public Maze generate(int nRows, int nColumns)
    {
        Position startPos = new Position(0, 0);
        Position goalPos = new Position(nRows - 1, nColumns - 1);
        Maze maze = new Maze(nRows, nColumns, startPos, goalPos);

        maze.resetMazeWithValue(0);

        return maze;
    }
}
