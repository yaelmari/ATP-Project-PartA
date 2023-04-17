package algorithms.mazeGenerators;

public class Position {
    protected final int rowIndex;
    protected final int columnIndex;

    public Position(int rowIndex, int columnIndex)
    {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }
    public int getRowIndex()
    {
        return rowIndex;
    }
    public int getColumnIndex()
    {
        return columnIndex;
    }

    @Override
    public String toString()
    {
        // format "{row,column}"
        return "{" + String.valueOf(this.rowIndex) + "," + String.valueOf(this.columnIndex) + "}";
    }

    public int getDistance(Position other){
        int deltaX,deltaY;
        deltaX = Math.abs(this.getColumnIndex() - other.getColumnIndex());
        deltaY = Math.abs(this.getRowIndex() - other.getRowIndex());
        return  deltaX + deltaY;
    }
}
