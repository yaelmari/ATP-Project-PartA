package algorithms.mazeGenerators;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyMazeGenerator extends AMazeGenerator{
    private int[][] visitedCells;
    private Maze maze;
    public Maze generate(int nRows, int nColumns)
    {
        Position startPos = new Position(0, 0);
        Position goalPos = new Position(nRows - 1, nColumns - 1);
        this.maze = new Maze(nRows, nColumns, startPos, goalPos);
        int nRowsVisitedArr = nRows / 2, nColsVisitedArr = nColumns / 2;

        if(nRows == 0 || nColumns == 0){
            return null;
        }

        resetDFSmaze();
        if(nRows % 2  == 1){
            nRowsVisitedArr++;
        }
        if(nColumns % 2  == 1){
            nColsVisitedArr++;
        }

        this.visitedCells = new int[nRowsVisitedArr][nColsVisitedArr];
        resetVisitedArr(nRowsVisitedArr, nColsVisitedArr);

        dfsMazeGenerator();

        return maze;
    }

    private void resetVisitedArr(int nRowsVisitedArr, int nColsVisitedArr) {
        for (int i = 0; i < nRowsVisitedArr; i++)
        {
            for (int j = 0; j < nColsVisitedArr; j++)
            {
                // visited = 1, did not visit = 0
                this.visitedCells[i][j] = 0;
            }
        }
    }

    private void resetDFSmaze()
    {
        // Create wall in every second row (starting from 1)
        for(int row = 1; row < this.maze.getNRows(); row+=2)
        {
            maze.setRowWithValue(row, 1);
        }

        // create wall in every second row (starting from 0), in every second cell.
        for(int row = 0; row < maze.getNRows(); row+=2){
            for(int col = 0; col < maze.getNColumns(); col++){
                maze.setSingleValue(row, col, col % 2);
            }
        }

    }

    private void dfsMazeGenerator()
    {
        Position startP = new Position(0, 0), curr, last;
        List<Position> unvisitedNeighbors, openP = new ArrayList<>();
        int moveInRow, moveInCol, neighborIndex;
        Dictionary<Position, int[]> movesDict = new Hashtable<>();

        visitedCells[0][0] = 1;
        last = startP;

        do{
            unvisitedNeighbors = getMyUnvisitedNeighbors(last); // Get the unvisited neighbors of the current position
            // Add the neighbors to the open list in a random order
            for(int maxNumOfNeighbor = unvisitedNeighbors.size(); maxNumOfNeighbor  > 0; maxNumOfNeighbor--){
                neighborIndex = ThreadLocalRandom.current().nextInt(0, maxNumOfNeighbor);
                curr = unvisitedNeighbors.remove(neighborIndex);
                openP.add(curr);
                // Calculate the amount of cells and their direction that we need to move in the maze
                moveInRow = curr.getRowIndex() - last.getRowIndex();
                moveInCol = curr.getColumnIndex() - last.getColumnIndex();
                movesDict.put(curr, new int[]{moveInRow, moveInCol});
            }

            handleCurrCell(openP, movesDict);
            last = openP.remove(openP.size() - 1); // Remove the current cell from the list
        }while(openP.size() > 0);

        openGoalCell();
    }

    public void handleCurrCell(List<Position> openP, Dictionary<Position, int[]> movesDict)
    {
        int currRowInVisitedArr, currColInVisitedArr, moveInRow, moveInCol;
        Position curr = openP.get(openP.size() - 1); // The curr position is the last in the list
        currRowInVisitedArr = curr.getRowIndex() / 2;
        currColInVisitedArr = curr.getColumnIndex() / 2;

        // Check if the current cell was already discovered by another path
        if(visitedCells[currRowInVisitedArr][currColInVisitedArr] == 0) {
            visitedCells[currRowInVisitedArr][currColInVisitedArr] = 1;
            // Calculate the amount of cells and their direction that we need to move in the maze
            moveInRow = curr.getRowIndex() - movesDict.get(curr)[0] / 2; // amount of moves (rows)
            moveInCol = curr.getColumnIndex() - movesDict.get(curr)[1] / 2; // amount of moves (columns)
            movesDict.remove(curr);
            this.maze.setSingleValue(moveInRow, moveInCol, 0); // Open the wall in the middle
        }
    }

    private void openGoalCell()
    {
        int randomNum;
        // open the goal cell and one next to it (if the amount of lines is even)
        maze.setSingleValue(maze.getNRows() - 1, maze.getNColumns() - 1, 0);
        if(maze.getNRows() % 2 == 0){
            randomNum = ThreadLocalRandom.current().nextInt(0, 2);
            if(randomNum == 0) {
                maze.setSingleValue(maze.getNRows() - 1, maze.getNColumns() - 2, 0);
            }
            else{
                maze.setSingleValue(maze.getNRows() - 2, maze.getNColumns() - 1, 0);
            }
        }
    }

    private List<Position> getMyUnvisitedNeighbors(Position p)
    {
        int row = p.getRowIndex() / 2, col = p.getColumnIndex() / 2;
        List<Position> unvisitedNeighbors = new ArrayList<>();

        if(row - 1 >= 0){
            // Check the "up" neighbor
            if(this.visitedCells[row - 1][col] == 0){
                unvisitedNeighbors.add(new Position((row - 1) * 2, col * 2));
            }
        }
        if(col - 1 >= 0){
            // Check the "left" neighbor
            if(this.visitedCells[row][col - 1] == 0){
                unvisitedNeighbors.add(new Position(row * 2, (col - 1) * 2));
            }
        }
        if(this.visitedCells.length > 0) {
            if (col + 1 <= this.visitedCells[0].length - 1) {
                // Check the "right" neighbor
                if(this.visitedCells[row][col + 1] == 0) {
                    unvisitedNeighbors.add(new Position(row * 2, (col + 1) * 2));
                }
            }
            if(row + 1 <= this.visitedCells.length - 1){
                // Check the "down" neighbor
                if(this.visitedCells[row + 1][col] == 0){
                    unvisitedNeighbors.add(new Position((row + 1) * 2, col * 2));
                }
            }
        }
        return unvisitedNeighbors;
    }
}
