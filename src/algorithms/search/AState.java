package algorithms.search;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AState {
    // need to create 2 string function
    protected ArrayList<AState> neighbors;

    public void addNeighbor(AState state)
    {
        this.neighbors.add(state);
    }

    public Iterator<AState> getIterator()
    {
        return neighbors.iterator();
    }
    public ArrayList<AState> getNeighbors()
    {
        return neighbors;
    }

    public int getSize()
    {
        return neighbors.size();
    }


}
