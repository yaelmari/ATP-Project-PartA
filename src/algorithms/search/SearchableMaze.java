package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private AState startState;
    private AState goalState;
    public SearchableMaze(Maze maze){
        Position startPos = maze.getStartPosition();
        AState[][] allStates = new AState[maze.getNRows()][maze.getNColumns()];
        buildAdjacencyList(maze, allStates);
    }

    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }

    private void buildAdjacencyList(Maze maze, AState[][] allStates) {
        for(int row = 0; row < maze.getNRows(); row++){
            for(int col = 0; col < maze.getNColumns(); col++){
                // Check if the cell contains a wall
                if(!maze.isEmptyCell(row, col))
                {
                    allStates[row][col] = null;
                    continue;
                }

                // The cell is empty, we can add it to the allStates matrix
                allStates[row][col] = new MazeState(row, col);
            }
        }

        // update the adjacency list
        for(int row = 0; row < maze.getNRows(); row++) {
            for (int col = 0; col < maze.getNColumns(); col++) {
                if(maze.isEmptyCell(row, col)) {
                    updateNeighbors(allStates, row, col);
                }
            }
        }
        this.startState = allStates[0][0];
        this.goalState = allStates[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
    }

    private void updateNeighbors(AState[][] allStates, int statesRow, int statesCol)
    {
        boolean openUp = false, openDown = false, openRight = false, openLeft = false;
        boolean availableUp = false, availableDown = false, availableLeft = false, availableRight = false;
        if(statesRow - 1 >= 0){
            availableUp = true;
            openUp = checkSpecificNeighbor(allStates, -1, 0, statesRow, statesCol); // Check the top row
        }
        if(statesRow + 1 < allStates.length){
            availableDown = true;
            openDown = checkSpecificNeighbor(allStates, 1, 0, statesRow, statesCol); // Check the bottom row
        }
        if(statesCol - 1 >= 0){
            availableLeft = true;
            openLeft = checkSpecificNeighbor(allStates, 0, -1, statesRow, statesCol); // Check the left column
        }
        if(allStates.length > 0 && statesCol + 1 < allStates[0].length){
            availableRight = true;
            openRight = checkSpecificNeighbor(allStates, 0, 1, statesRow, statesCol); // Check the right column
        }
        if(availableUp){
            if(availableLeft && (openUp || openLeft)){
                checkSpecificNeighbor(allStates, -1, -1, statesRow, statesCol);
            }
            if(availableRight && (openUp || openRight)){
                checkSpecificNeighbor(allStates, -1, 1, statesRow, statesCol);
            }
        }
        if(availableDown){
            if(availableLeft && (openDown || openLeft)){
                checkSpecificNeighbor(allStates, 1, -1, statesRow, statesCol);
            }
            if(availableRight && (openDown || openRight)){
                checkSpecificNeighbor(allStates, 1, 1, statesRow, statesCol);
            }
        }
    }

    private boolean checkSpecificNeighbor(AState[][] allStates, int moveRow, int moveCol, int statesRow, int statesCol)
    {
        if(allStates[statesRow + moveRow][statesCol + moveCol] != null) {
            allStates[statesRow][statesCol].addNeighbor(allStates[statesRow + moveRow][statesCol + moveCol]);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return state.getNeighbors();
    }
}
