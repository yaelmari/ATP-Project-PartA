package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    public long measureAlgorithmTimeMillis(int nRows, int nColumns)
    {
        long startTime, endTime;
        Maze maze;
        startTime = System.currentTimeMillis();
        maze = this.generate(nRows, nColumns);
        maze.print();
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
