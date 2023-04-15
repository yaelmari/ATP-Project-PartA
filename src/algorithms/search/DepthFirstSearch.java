package algorithms.search;
import java.util.ArrayList;

public class DepthFirstSearch extends ASearchingAlgorithm{


    public DepthFirstSearch(){}



    @Override
    public String getName() {
        return "DFS";
    }

    @Override
    public Solution solve(ISearchable domain) {
        numberOfNodesEvaluated = 0;
        AState start = domain.getStartState(), curr;
        ArrayList<AState> openP = new ArrayList<>(), neighbors;
        Solution sol;
        start.setParent(start);
        openP.add(start);

        addToVisited(start);


        while (!openP.isEmpty()) {
            curr = openP.remove(openP.size() - 1); // Remove the current cell from the list
            numberOfNodesEvaluated++;


            if(curr == domain.getGoalState()){
                // Restore the track
                sol = restoreTrack(start, curr); // The solution was found
//                int size = openP.size();
//                for(int i = 0; i < size;i++){
//                    addToVisited(openP.get(0));
//                }

                resetVisited();
                return sol;

            }

            neighbors = curr.getNeighbors(); // Get the neighbors of the current state
//             Add the neighbors to the open list
//            for (AState neighbor : neighbors) {
//                if (!visitedContains(neighbor)) {
//                    neighbor.setParent(curr);
//                    addToVisited(neighbor);
//                    openP.add(neighbor); // if the state wasn't visited, add it to the open list
//                }
//            }




            for (AState neighbor : neighbors) {
                if (neighbor.getParent() == null) {
                    neighbor.setParent(curr);
                  //  if (!visitedContains(neighbor)){
                    addToVisited(neighbor);//}
                    openP.add(neighbor); // if the state wasn't visited, add it to the open list
                }
            }

        }

        resetVisited();
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
