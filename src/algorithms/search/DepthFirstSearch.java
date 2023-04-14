package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

public class DepthFirstSearch implements ISearchingAlgorithm{
    private int[][] visitedCells;
    private int numberOfNodesEvaluated = 0;


    public DepthFirstSearch(){}


    @Override
    public String getNumberOfNodesEvaluated() {
        return Integer.toString(numberOfNodesEvaluated);
    }


    @Override
    public String getName() {
        return "DFS";
    }

    @Override
    public Solution solve(ISearchable domain) {
        AState start = domain.getStartState(), curr;
        ArrayList<AState> openP = new ArrayList<>(), neighbors;
        HashMap<AState,AState> visited = new HashMap<>(); // <child, parent>
        visited.put(start, null);
        openP.add(start);

        while (openP.size() > 0) {
            curr = openP.remove(openP.size() - 1); // Remove the current cell from the list
            numberOfNodesEvaluated++;

            if(curr == domain.getGoalState()){
                // Restore the track
                return restoreTrack(visited, curr); // The solution was found
            }

            neighbors = curr.getNeighbors(); // Get the neighbors of the current state
            // Add the neighbors to the open list
            for (AState neighbor : neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, curr);
                    openP.add(neighbor); // if the state wasn't visited, add it to the open list
                }
            }
        }

        return null;
    }

    private Solution restoreTrack(HashMap<AState,AState> visited, AState goalState)
    {
        Solution sol = new Solution();
        AState curr = goalState;

        do{
            sol.addToStart(curr);
            curr = visited.get(curr); // Get the parent
        }while(curr != null); // uUtil we get to the start state (the parent of the start state is null)

        return sol;
    }

//    @Override
//    public void search() {
//
//    }
}
