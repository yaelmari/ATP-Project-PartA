package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MyMaze3DGenerator extends AMaze3DGenerator{
    private int[][][] visitedCells;
    private Maze3D maze3D;
    public Maze3D generate(int depth, int row, int column)
    {
        Position3D startPos = new Position3D(0, 0, 0);
        Position3D goalPos = new Position3D(0, row - 1, column - 1);
        this.maze3D = new Maze3D(depth, row, column, startPos, goalPos);
//        int nRowsVisitedArr = row / 2, nColsVisitedArr = column / 2;
//
        resetDFSmaze();
//        if(row % 2  == 1){
//            nRowsVisitedArr++;
//        }
//        if(column % 2  == 1){
//            nColsVisitedArr++;
//        }

        this.visitedCells = new int[depth][row][column];
        resetVisitedArr(depth, row, column);

        dfsMazeGenerator();
        if(depth % 2 == 0){
            handleThelastDepth();
        }

        return maze3D;
    }

    private void resetVisitedArr(int nDepthVisitedArr, int nRowsVisitedArr, int nColsVisitedArr) {
        for (int depth = 0; depth < nDepthVisitedArr; depth++) {
            for (int row = 0; row < nRowsVisitedArr; row++) {
                // visited = 1, did not visit = 0
                Arrays.fill(this.visitedCells[depth][row], 0);
            }
        }
    }

    private void resetDFSmaze()
    {
        int value;
        for (int depth = 0; depth < this.maze3D.getnDepth() - 1; depth++) {
            // Create wall in every second row (starting from 1)
            for (int row = 1; row < this.maze3D.getnRows(); row += 2) {
                maze3D.setRowWithValue(depth, row, 1);
            }

            // create wall in every second row (starting from 0), in every second cell.
            for (int row = 0; row < maze3D.getnRows(); row += 2) {
                for (int col = 0; col < maze3D.getnColumns(); col++) {
                    value = (depth % 2 + col % 2) % 2;
                    maze3D.setSingleValue(depth, row, col, value);
                }
            }
        }
//        maze3D.print();
//        System.out.println();
//        System.out.println();
//        System.out.println();
    }

    private void dfsMazeGenerator()
    {
        Position3D startP = new Position3D(0, 0, 0), curr, last;
        List<Position3D> unvisitedNeighbors, openP = new ArrayList<>();
        int moveInDepth, moveInRow, moveInCol, neighborIndex, currRowInVisitedArr, currColInVisitedArr;
        Dictionary<Position3D, int[]> movesDict = new Hashtable<>();
        visitedCells[0][0][0] = 1;
        last = startP;

        do{
            if(last.getDepthIndex() == maze3D.getGoalPos().getDepthIndex() &&
                    last.getRowIndex() == maze3D.getGoalPos().getRowIndex() &&
                    last.getColumnIndex() == maze3D.getGoalPos().getColumnIndex())
            {
                if(openP.isEmpty())
                {
                    break;
                }
            }
            else if(last != null){
                unvisitedNeighbors = getMyUnvisitedNeighbors(last); // Get the unvisited neighbors of the current position
                // Add the neighbors to the open list in a random order
                for (int maxNumOfNeighbor = unvisitedNeighbors.size(); maxNumOfNeighbor > 0; maxNumOfNeighbor--) {
                    neighborIndex = ThreadLocalRandom.current().nextInt(0, maxNumOfNeighbor);
                    curr = unvisitedNeighbors.remove(neighborIndex);
                    openP.add(curr);
                    // Calculate the amount of cells and their direction that we need to move in the maze
                    moveInDepth = curr.getDepthIndex() - last.getDepthIndex();
                    moveInRow = curr.getRowIndex() - last.getRowIndex();
                    moveInCol = curr.getColumnIndex() - last.getColumnIndex();
                    movesDict.put(curr, new int[]{moveInDepth, moveInRow, moveInCol});
                }
            }
            if(!openP.isEmpty()){
                last = handleCurrCell(openP, movesDict);
            }
            else {
                last = null;
            }

        }while(last != null && !openP.isEmpty());

        openGoalCell();
    }

    private void handleThelastDepth() {
        int myDepth = maze3D.getnDepth() - 1, depthToCopy, value;

        if(myDepth == 1){
            depthToCopy = 1;
        }
        else {
            depthToCopy = myDepth - 2;
        }

        for (int row = 0; row < maze3D.getnRows(); row ++) {
            for (int col = 0; col < maze3D.getnColumns(); col++) {
                value = maze3D.getValue(depthToCopy, row, col);
                maze3D.setSingleValue(myDepth, row, col, value);
            }
        }
    }

    public Position3D handleCurrCell(List<Position3D> openP, Dictionary<Position3D, int[]> movesDict)
    {
        Position3D curr = openP.get(openP.size() - 1); // The curr position is the last in the list
        int currsDepth = curr.getDepthIndex(), currsRow = curr.getRowIndex(), currsCol = curr.getColumnIndex();
        int moveInDepth, moveInRow, moveInCol;
        int wallsDepth, wallsRow, wallsCol;
        Position3D newPos;

        // Check if the current cell was already discovered by another path
//        if(visitedCells[depth][row][col] == 0) {
            if (maze3D.isEmptyCell(currsDepth, currsRow, currsCol) && visitedCells[currsDepth][currsRow][currsCol] != 1) { // it must be a open cell
                // Calculate the amount of cells and their direction that we need to move in the maze
                moveInDepth = movesDict.get(curr)[0]; // amount of moves (depth)
                moveInRow = movesDict.get(curr)[1]; // amount of moves (rows)
                moveInCol = movesDict.get(curr)[2]; // amount of moves (columns)

                movesDict.remove(curr);
                openP.remove(curr); // Remove the current cell from the list


//                wallsDepth = last.getDepthIndex() + moveInDepth / 2;
//                wallsRow = last.getRowIndex() + moveInRow / 2;
//                wallsCol = last.getColumnIndex() + moveInCol / 2;
                wallsDepth = curr.getDepthIndex() - moveInDepth / 2;
                wallsRow = curr.getRowIndex() - moveInRow / 2;
                wallsCol = curr.getColumnIndex() - moveInCol / 2;
                visitedCells[wallsDepth][wallsRow][wallsCol] = 1; // mark the cell as visited
                visitedCells[currsDepth][currsRow][currsCol] = 1; // mark the cell as visited
                this.maze3D.setSingleValue(wallsDepth, wallsRow, wallsCol, 0); // open the wall
//                try {
//                    if(visitedCells[wallsDepth][wallsRow][wallsCol] == 0) {
//                        newPos = new Position3D(wallsDepth, wallsRow, wallsCol);
//
//                        visitedCells[depth][row][col] = 1; // mark the cell as visited
//                        this.maze3D.setSingleValue(depth, row, col, 0); // open the wall
//
//                        // Add the new cell to the open list to complete the move
//                        openP.add(newPos);
////                    movesDict.put(newPos, new int[]{moveInDepth, moveInRow, moveInCol});
//                        // Complete the step: pass the wall and mark the next open cell as visited
//                        visitedCells[wallsDepth][wallsRow][wallsCol] = 1; // mark the cell as visited
//                    }
//                    if(!openP.isEmpty()) {
                        return curr;
//                    }
//                }catch (IndexOutOfBoundsException ignored){};
            }
//        }
        return null;
    }

    private void openGoalCell()
    {
        int randomNum;
        // open the goal cell and one next to it (if the amount of lines is even)
        maze3D.setSingleValue(0, maze3D.getnRows() - 1, maze3D.getnColumns() - 1, 0);
        if(maze3D.getnRows() % 2 == 0){
            randomNum = ThreadLocalRandom.current().nextInt(0, 2);
            if(randomNum == 0) {
                maze3D.setSingleValue(0, maze3D.getnRows() - 1, maze3D.getnColumns() - 2, 0);
            }
            else{
                maze3D.setSingleValue(0, maze3D.getnRows() - 2, maze3D.getnColumns() - 1, 0);
            }
        }
    }

    private List<Position3D> getMyUnvisitedNeighbors(Position3D p)
    {
        int row = p.getRowIndex(), col = p.getColumnIndex(), depth = p.getDepthIndex();
        List<Position3D> unvisitedNeighbors = new ArrayList<>();
        int stepCheck = 2;

        if(row - stepCheck >= 0){
            // Check the "back" neighbor
            checkPotentialUnvisitedNeighbor(depth, row - stepCheck, col, unvisitedNeighbors);
        }
        if(row + stepCheck < this.maze3D.getnRows()){
            // Check the "front" neighbor
            checkPotentialUnvisitedNeighbor(depth, row + stepCheck, col, unvisitedNeighbors);
        }
        if(col - stepCheck >= 0){
            // Check the "left" neighbor
            checkPotentialUnvisitedNeighbor(depth, row, col - stepCheck, unvisitedNeighbors);
        }
        if(col + stepCheck < this.maze3D.getnColumns()) {
            // Check the "right" neighbor
            checkPotentialUnvisitedNeighbor(depth, row, col + stepCheck, unvisitedNeighbors);
        }

        if(depth - stepCheck >= 0) {
            // Check the "up" neighbor
            checkPotentialUnvisitedNeighbor(depth - stepCheck, row, col, unvisitedNeighbors);
        }
        if(depth + stepCheck < this.maze3D.getnDepth()){
            // Check the "down" neighbor
            checkPotentialUnvisitedNeighbor(depth + stepCheck, row, col, unvisitedNeighbors);
        }

        return unvisitedNeighbors;
    }

    private void checkPotentialUnvisitedNeighbor(int depth, int row, int col, List<Position3D> unvisitedNeighbors){
        try {
            if(this.visitedCells[depth][row][col] == 0){
                unvisitedNeighbors.add(new Position3D(depth, row, col));
            }
        }catch (IndexOutOfBoundsException ignored){};
    }
}
