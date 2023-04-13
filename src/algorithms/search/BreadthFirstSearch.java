package algorithms.search;

import java.util.*;

public class BreadthFirstSearch implements ISearchingAlgorithm{
    private int numberOfNodesEvaluated = 0;
    @Override
    public String getNumberOfNodesEvaluated() {
        return Integer.toString(numberOfNodesEvaluated);
    }

    @Override
    public String getName() {
        return "BFS";
    }

    @Override
    public Solution solve(ISearchable domain) {
        HashMap<String,AState> parents = new HashMap<>();
        parents.put(domain.getStartState().toString(), null);
        ArrayList<AState> queue = new ArrayList<>();
        queue.add(0,domain.getStartState());
        AState checked;
        while(!queue.isEmpty()) {
            checked = queue.remove(queue.size()-1);
            this.numberOfNodesEvaluated++;
            for (AState neighbor : checked.getNeighbors()) {
                if(!parents.containsKey(neighbor.toString())){
                    parents.put(neighbor.toString(),checked);
                    queue.add(0,neighbor);

                }
            }
            if(checked == domain.getGoalState()){
              break; // The solution was found
            }
        }
        Solution solution = new Solution();
        checked = domain.getGoalState();
        while(checked != null){
            solution.addToStart(checked);
            checked = parents.get(checked.toString());


        }

         return solution;
    }

//    @Override
//    public void search() {
//
//    }
}
