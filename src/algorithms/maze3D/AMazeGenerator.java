package algorithms.maze3D;

import algorithms.mazeGenerator.Maze;

public abstract class AMazeGenerator implements IMazeGenerator{
    public long measureAlgorithmTimeMillis(int nLines, int nColumns)
    {
        long startTime, endTime;
        Maze maze;
        startTime = System.currentTimeMillis();
        maze = this.generate(nLines, nColumns);
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
