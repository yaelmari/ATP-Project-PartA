package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position {
    private final int depthIndex;

    public Position3D(int depthIndex, int rowIndex, int columnIndex){
        super(rowIndex, columnIndex);
        this.depthIndex = depthIndex;
    }
    public int getDepthIndex()
    {
        return depthIndex;
    }

    public boolean checkEqualIndexes(int depthIndex, int rowIndex, int columnIndex){
        // Check if the given indexes are equal to position's indexes.
        return (depthIndex == this.depthIndex && rowIndex == this.rowIndex && columnIndex == this.columnIndex);
    }
}
