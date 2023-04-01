package algorithms.mazeGenerators;

public class Position {
    private final int rowIndex;
    private final int columnIndex;

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
        return "(" + String.valueOf(this.rowIndex) + ", " + String.valueOf(this.columnIndex) + ")";
    }
}
