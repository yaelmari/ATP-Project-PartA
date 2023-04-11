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
                updateNeighbors(allStates, row, col);
            }
        }
        this.startState = allStates[0][0];
        this.goalState = allStates[maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
    }

    private void updateNeighbors(AState[][] allStates, int statesRow, int statesCol)
    {
        for(int row = -1; row < 2; row++)
        {
            for(int col = -1; col < 2; col++)
            {
                // Check is the current cell isn't itself
                if(row == 0 && col == 0)
                {
                    continue;
                }

                // Check if the index isn't out of bounds of the array
                if(row + statesRow < 0 || row + statesRow >= allStates.length)
                {
                    continue;
                }

                if(col + statesCol < 0 || (allStates.length > 0 && col + statesCol >= allStates[0].length))
                {
                    continue;
                }

                // Check if the neighbor isn't a wall
                if(allStates[row + statesRow][col + statesCol] != null && allStates[statesRow][statesCol] != null)
                {
                    allStates[statesRow][statesCol].addNeighbor(allStates[row + statesRow][col + statesCol]);
                }
            }
        }
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return state.getNeighbors();
    }
}
