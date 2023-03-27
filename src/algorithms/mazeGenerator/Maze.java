package algorithms.mazeGenerator;

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
    public Position getStartPosition()
    {
        return startPos;
    }

    public Position getGoalPosition()
    {
        return goalPos;
    }

    public void Print()
    {
        for (int i = 0; i < this.mazeTable.length; i++)
        {
            for (int j = 0; j < this.mazeTable[0].length; j++)
            {
                // We need to add: S for Start and E for Exit !!!!!!!!!!!!!!!
                System.out.print(this.mazeTable[i][j] + " ");
            }
            System.out.println();
        }
    }
}
