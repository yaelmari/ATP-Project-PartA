package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState{
        private Position pos;

        public MazeState(int xPos, int yPos)
        {
                this.pos = new Position(xPos, yPos);
                this.neighbors = new ArrayList<>();
        }

        public Position getPos()
        {
                return pos;
        }
}
