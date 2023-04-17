package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

import java.util.ArrayList;

public class Maze3DState extends AState {

    public Maze3DState(int xPos, int yPos,int zPos){
        this.parent = null;
        this.cost = 0;
        this.pos = new Position3D(xPos, yPos,zPos);
        this.neighbors = new ArrayList<>();
    }

    @Override
    public String toString(){
        String ans = this.pos.toString();
        return ans;
    }



}
