package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D extends Position{

    private final int depthIndex;

    public Position3D(int depthIndex, int rowIndex, int columnIndex){
        super(rowIndex,columnIndex);
        this.depthIndex = depthIndex;
    }
    public int getDepthIndex()
    {
        return depthIndex;
    }

    public boolean checkEqualIndexes(int depthIndex, int rowIndex, int columnIndex){
        // Check if the given indexes are equal to position's indexes.
        return (depthIndex == this.getDepthIndex() && rowIndex == this.getRowIndex() && columnIndex == this.getColumnIndex());
        }

    @Override
    public int getDistance(Position other){
        return 1;
    }

    @Override
    public String toString()
    {
        // format "{depth,row,column}"
        return "{" + String.valueOf(this.getDepthIndex()) + "," + String.valueOf(this.getRowIndex()) +"," + String.valueOf(this.getColumnIndex()) + "}";
    }
}
