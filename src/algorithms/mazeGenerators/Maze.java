package algorithms.mazeGenerators;

public class Maze {
    private int[][] mazeTable;
    private final Position startPos;
    private final Position goalPos;

    public Maze(int nLines, int nColumns, Position startPos, Position goalPos)
    {
        this.mazeTable = new int[nLines][nColumns];
        this.startPos = startPos;
        this.goalPos = goalPos;
    }

    public void resetMazeWithValue(int value)
    {
        for (int i = 0; i < this.mazeTable.length; i++)
        {
            for (int j = 0; j < this.mazeTable[0].length; j++)
            {
                this.mazeTable[i][j] = value;
            }
        }
    }

    public void setSingleValue(int row, int col, int value)
    {
        this.mazeTable[row][col] = value;
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

    public void print()
    {
        for (int i = 0; i < this.mazeTable.length; i++)
        {
            for (int j = 0; j < this.mazeTable[0].length; j++)
            {
                // We need to add: S for Start and E for Exit !!!!!!!!!!!!!!!
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
