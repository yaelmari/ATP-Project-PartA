package algorithms.maze3D;

public class Maze3D {
    private int[][][] maze3dTable;
    private Position3D startPos;
    private Position3D goalPos;
    private int nDepth;
    private int nRows;
    private int nColumns;
    public Maze3D(int nDepth, int nRows, int nColumns, Position3D startPos, Position3D goalPos){
       this.nDepth = nDepth;
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.startPos = new Position3D(0, 0, 0);
        this.goalPos = new Position3D(0, nRows - 1, nColumns - 1);
        maze3dTable = new int[][][]{
                {
                        {0, 0, 1, 0, 1},
                        {0, 1, 1, 1, 0},
                        {0, 1, 1, 0, 0}
                },
                {
                        {1, 1, 1, 0, 1},
                        {1, 0, 0, 1, 0},
                        {0, 0, 1, 0, 1}
                },
                {
                        {1, 1, 1, 0, 1},
                        {1, 1, 0, 0, 0},
                        {1, 1, 1, 0, 1}
                }
        };

    }
    public int[][][] getMap(){
        return null;
    }
    public Position3D getStartPosition(){
        return null;
    }
    public Position3D getGoalPosition(){
        return null;
    }

    public void print()
    {
        for (int depth = 0; depth < this.maze3dTable.length; depth++) {
            for (int row = 0; row < this.maze3dTable[0].length; row++){
                for (int col = 0; col < this.maze3dTable[0][0].length; col++){
                    if (startPos.checkEqualIndexes(depth, row, col)) {
                        System.out.print("S ");
                    } else if (goalPos.checkEqualIndexes(depth, row, col)) {
                        System.out.print("E ");
                    } else {
                        System.out.print(this.maze3dTable[depth][row][col] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public Position3D getStartPos() {
        return startPos;
    }

    public Position3D getGoalPos() {
        return goalPos;
    }

    public int getnDepth() {
        return nDepth;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnColumns() {
        return nColumns;
    }
}
