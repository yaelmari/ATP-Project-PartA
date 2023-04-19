package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> sol;
    public ArrayList<AState> getSolutionPath() {
        return sol;
    }

    public Solution() {
        this.sol = new ArrayList<>();
    }

    public void addToEnd(AState state)
    {
        this.sol.add(state);
    }
    public void addToStart(AState state){ this.sol.add(0,state);}

    public AState removeByIndex(int index)
    {
        return sol.remove(index);
    }

    public AState removeLast()
    {
        if(sol.size() -1 < 0){return null;}
        return sol.remove(sol.size() - 1);
    }

    public int getSize()
    {
        return sol.size();
    }

    public AState getFirst(){
        return this.sol.get(0);
    }
}
