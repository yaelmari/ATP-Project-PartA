package algorithms.mazeGenerators;

import algorithms.mazeGenerators.Maze;

public interface IMazeGenerator {
    public Maze generate(int nRows, int nColumns);
    public long measureAlgorithmTimeMillis(int nRows, int nColumns);
}
