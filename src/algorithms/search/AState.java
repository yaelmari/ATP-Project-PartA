package algorithms.search;
import java.util.ArrayList;

public abstract class AState implements Comparable<AState> {
    protected ArrayList<AState> neighbors;
    protected AState parent;

    public AState getParent() {
        return parent;
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
        this.cost += cost;
    }

    protected int cost;

    public void addNeighbor(AState state)
    {
        this.neighbors.add(state);
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
        return Integer.compare(this.cost, other.cost);
    }
}
