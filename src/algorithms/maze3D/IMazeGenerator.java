package algorithms.maze3D;

import algorithms.mazeGenerator.Maze;

public interface IMazeGenerator {
    public Maze generate(int nLines, int nColumns);
    public long measureAlgorithmTimeMillis(int nLines, int nColumns);
}
