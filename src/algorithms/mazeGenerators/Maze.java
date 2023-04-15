package algorithms.mazeGenerators;

public class Maze {
    private int[][] mazeTable;
    private Position startPos;
    private Position goalPos;
    private int nRows;
    private int nColumns;

    public Maze(int nRows, int nColumns, Position startPos, Position goalPos)
    {
        this.mazeTable = new int[nRows][nColumns];
        this.nColumns = nColumns;
        this.nRows = nRows;
        this.startPos = startPos;
        this.goalPos = goalPos;
    }

    public void resetMazeWithValue(int value)
    {
        for (int i = 0; i < this.nRows; i++)
        {
            for (int j = 0; j < this.nColumns; j++)
            {
                this.mazeTable[i][j] = value;
            }
        }
    }

    public void setSingleValue(int row, int col, int value)
    {
        this.mazeTable[row][col] = value;
    }

    public void setRowWithValue(int row, int value)
    {
        for(int col = 0; col <nColumns; col++)
        {
            this.mazeTable[row][col] = value;
        }
    }
    public Position getStartPosition()
    {
        return startPos;
    }

    public boolean isEmptyCell(int row,int col){
        return this.mazeTable[row][col] == 0;
    }
    public Position getGoalPosition()
    {
        return goalPos;
    }

    public int getNColumns()
    {
        return this.nColumns;
    }
    public int getNRows()
    {
        return this.nRows;
    }

    public void print()
    {
        for (int i = 0; i < this.mazeTable.length; i++)
        {
            for (int j = 0; j < this.mazeTable[0].length; j++)
            {
                if(i == startPos.getRowIndex() && j == startPos.getColumnIndex())
                {
                    System.out.print("S ");
                }
                else if(i == goalPos.getRowIndex() && j == goalPos.getColumnIndex())
                {
                    System.out.print("E ");
                }
                else {
                    System.out.print(this.mazeTable[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
