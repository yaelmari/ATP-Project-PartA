package algorithms.search;

import java.util.*;

public class BreadthFirstSearch implements ISearchingAlgorithm{
    @Override
    public String getNumberOfNodesEvaluated() {
        return null;
    }

    @Override
    public String getName() {
        return null;
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
            for (AState neighbor : checked.getNeighbors()) {
                if(!parents.containsKey(neighbor.toString())){
                    parents.put(neighbor.toString(),checked);
                    queue.add(neighbor);
                }
            }
        }
        Solution solution = new Solution();
        checked = domain.getExit();
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
