package algorithms.search;
import java.util.ArrayList;

public class DepthFirstSearch implements ISearchingAlgorithm{
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
        start.setParent(start);
        openP.add(start);

        while (openP.size() > 0) {
            curr = openP.remove(openP.size() - 1); // Remove the current cell from the list
            numberOfNodesEvaluated++;

            if(curr == domain.getGoalState()){
                // Restore the track
                return restoreTrack(start, curr); // The solution was found
            }

            neighbors = curr.getNeighbors(); // Get the neighbors of the current state
            // Add the neighbors to the open list
            for (AState neighbor : neighbors) {
                if (neighbor.getParent() == null) {
                    neighbor.setParent(curr);
                    openP.add(neighbor); // if the state wasn't visited, add it to the open list
                }
            }
        }

        return null;
    }

    private Solution restoreTrack(AState startState, AState goalState)
    {
        Solution sol = new Solution();
        AState curr = goalState;

        while(curr != startState)
        {
            sol.addToStart(curr);
            curr = curr.getParent();
        }
        sol.addToStart(startState);

        return sol;
    }
}
