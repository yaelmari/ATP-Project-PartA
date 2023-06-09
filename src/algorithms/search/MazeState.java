package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState{
        protected Position pos;

        public MazeState(int xPos, int yPos)
        {
                this.parent = null;
                this.cost = 0;
                this.pos = new Position(xPos, yPos);
                this.neighbors = new ArrayList<>();
        }
        @Override
        public Position getPos()
        {
                return pos;
        }
        @Override
        public String toString(){
                return this.pos.toString();
        }
}
