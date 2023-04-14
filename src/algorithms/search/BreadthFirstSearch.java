package algorithms.search;

import java.util.*;

public class BreadthFirstSearch implements ISearchingAlgorithm{
    protected int numberOfNodesEvaluated = 0;
    protected PriorityQueue<AState> queue = new PriorityQueue<AState>();
    @Override
    public String getNumberOfNodesEvaluated() {
        return Integer.toString(numberOfNodesEvaluated);
    }

    @Override
    public String getName() {
        return "BFS";
    }
    protected void inQueue(AState aState,AState parent){

        queue.add(aState);
    }

    protected AState deQueue(){
        return queue.poll();
    }

    @Override
    public Solution solve(ISearchable domain) {
        ArrayList<AState> visited = new ArrayList<>();
        MazeState parentStartNode = new MazeState(-1,-1);
        domain.getStartState().setParent(parentStartNode);
        inQueue(domain.getStartState(),null);
        AState checked;
        while(!queue.isEmpty()) {
            checked = deQueue();
            this.numberOfNodesEvaluated++;
            for (AState neighbor : checked.getNeighbors()) {
                if(neighbor.getParent() == null){
                    neighbor.setParent(checked);
                    if(!visited.contains(neighbor)){
                        visited.add(neighbor);
                    }
                    inQueue(neighbor,checked);


                }
            }
            if(checked == domain.getGoalState()){
              break; // The solution was found
            }
        }
        Solution solution = new Solution();
        checked = domain.getGoalState();
        while(checked != parentStartNode){
            solution.addToStart(checked);
            checked = checked.getParent();
        }
        for (AState aState :
                visited) {
            aState.setParent(null);
            aState.setCost(0);
        }

         return solution;
    }

}
