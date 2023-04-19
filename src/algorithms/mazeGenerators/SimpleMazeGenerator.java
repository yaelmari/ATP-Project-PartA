package algorithms.mazeGenerators;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleMazeGenerator extends AMazeGenerator {
    public Maze generate(int nRows, int nColumns) {
        final int NUMBER_OF_TRACKS = 10;
        Position startPos = new Position(0, 0);
        Position goalPos = new Position(nRows - 1, nColumns - 1);
        Maze maze = new Maze(nRows, nColumns, startPos, goalPos);
        int maxPos, index;
        int[] currPos = {0, 0};

        maze.resetMazeWithValue(1);
        maze.setSingleValue(startPos.getRowIndex(), startPos.getColumnIndex(), 0);
        for (int i = 0 ; i < NUMBER_OF_TRACKS ;i++){
            while (currPos[0] < nRows - 1 && currPos[1] < nColumns - 1) {
                index = ThreadLocalRandom.current().nextInt(0, 2);
                currPos[index]++;
                maze.setSingleValue(currPos[0], currPos[1], 0);
            }
            if (currPos[0] == nRows - 1) {
                index = 1;
                maxPos = nColumns;
            } else {
                index = 0;
                maxPos = nRows;
            }
            while (currPos[index] < maxPos - 1) {
                currPos[index]++;
                maze.setSingleValue(currPos[0], currPos[1], 0);
            }

            currPos[0] = 0;
            currPos[1] = 0;
        }
        return maze;
    }
}
