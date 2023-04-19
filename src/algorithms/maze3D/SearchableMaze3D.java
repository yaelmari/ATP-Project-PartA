package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private AState startState;
    private AState goalState;

    public SearchableMaze3D(Maze3D maze3D){
        AState[][][] allStates = new AState[maze3D.getnDepth()][maze3D.getnRows()][maze3D.getnColumns()];
        buildAdjacencyList(maze3D, allStates);

    }
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) { return state.getNeighbors();}

    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }


    public void buildAdjacencyList(Maze3D maze,AState[][][] allStates){
        for(int depth = 0 ; depth < maze.getnRows();depth++) {
            for (int column = 0; column < maze.getnColumns(); column++) {
                for (int row = 0; row < maze.getnRows(); row++) {

                    if (!maze.isEmptyCell(depth, row, column)) {
                        allStates[depth][row][column] = null;
                        continue;
                    }
                    allStates[depth][row][column] = new Maze3DState(depth,row, column);
                }
            }
        }
        for(int depth = 0 ; depth < maze.getnRows();depth++) {
            for (int column = 0; column < maze.getnColumns(); column++) {
                for (int row = 0; row < maze.getnRows(); row++) {
                    // The cell is empty, we can add it to the allStates matrix
                    if(maze.isEmptyCell(depth,row,column)){
                        updateNeighbors(allStates, row, column,depth);
                    }
                }
            }
        }
        this.startState = allStates[0][0][0];
        this.goalState = allStates[maze.getGoalPosition().getDepthIndex()][maze.getGoalPosition().getRowIndex()][maze.getGoalPosition().getColumnIndex()];
    }


    private void updateNeighbors(AState[][][] allStates,int row,int col,int dep){
        AState checked = allStates[dep][row][col];
        int[] indexes = {-1,1};
        for(int index: indexes){

            try {
                if (allStates[dep + index][row][col] != null) {
                    checked.addNeighbor(allStates[dep + index][row][col]);
                }
            }catch (IndexOutOfBoundsException ignored) {}

            try {
                if (allStates[dep][row + index][col] != null) {
                    checked.addNeighbor(allStates[dep][row + index][col]);
                }
            }catch (IndexOutOfBoundsException ignored) {}

            try {
                if (allStates[dep][row][col + index] != null) {
                    checked.addNeighbor(allStates[dep][row][col + index]);
                }
            }catch (IndexOutOfBoundsException ignored) {}
        }
    }
}
