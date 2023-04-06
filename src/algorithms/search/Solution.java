package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> sol;
    public ArrayList<AState> getSolutionPath() {
        return sol;
    }

    public void addToEnd(AState state)
    {
        this.sol.add(state);
    }

    public AState removeByIndex(int index)
    {
        return sol.remove(index);
    }

    public AState removeLast()
    {
        return sol.remove(sol.size() - 1);
    }

    public int getSize()
    {
        return sol.size();
    }
}
