package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    public long measureAlgorithmTimeMillis(int depth, int row, int column)
    {
        long startTime, endTime;
        Maze maze;
        startTime = System.currentTimeMillis();
//        maze = this.generate(nRows, nColumns);
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
