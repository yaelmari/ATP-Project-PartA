package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    public long measureAlgorithmTimeMillis(int depth, int row, int column)
    {
        long startTime, endTime;
        Maze3D maze3d;
        startTime = System.currentTimeMillis();
        maze3d = this.generate(depth, row, column);
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
