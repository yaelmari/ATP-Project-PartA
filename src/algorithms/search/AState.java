package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public abstract class AState implements Comparable<AState> {
    protected ArrayList<AState> neighbors;
    protected AState parent;
    protected Position pos;
    protected int cost;

    public AState getParent() {
        return parent;
    }
    public Position getPos()
    {
        return pos;
    }

    public void setParent(AState parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void addCost(int cost) {
        this.cost = this.cost + cost;
    }



    public void addNeighbor(AState state)
    {
        this.neighbors.add(0,state);
    }

    public ArrayList<AState> getNeighbors()
    {
        return neighbors;
    }

    public int getSize()
    {
        return neighbors.size();
    }

    @Override
    public int compareTo(AState other){
        if(this.getCost() <= other.getCost()){return 1;}
        else {return -1;}

//        return Integer.compare(this.getCost(), other.getCost());


    }
}
