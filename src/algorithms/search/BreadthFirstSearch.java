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
        MazeState startNode = new MazeState(-1,-1);
        domain.getStartState().setParent(startNode);
        inQueue(domain.getStartState(),null);
        AState checked;
        while(!queue.isEmpty()) {
            checked = deQueue();
            this.numberOfNodesEvaluated++;
            for (AState neighbor : checked.getNeighbors()) {
                if(neighbor.getParent() == null){
                    neighbor.setParent(checked);
                    inQueue(neighbor,checked);

                }
            }
            if(checked == domain.getGoalState()){
              break; // The solution was found
            }
        }
        Solution solution = new Solution();
        checked = domain.getGoalState();
        while(checked != startNode){
            solution.addToStart(checked);
            checked = checked.getParent();


        }

         return solution;
    }

}
