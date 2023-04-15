package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected PriorityQueue<AState> queue = new PriorityQueue<AState>();


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

        numberOfNodesEvaluated = 0;
        MazeState parentStartNode = new MazeState(-1,-1);
        domain.getStartState().setParent(parentStartNode);
        inQueue(domain.getStartState(),null);
        AState checked;
        while(!queue.isEmpty()) {
            checked = deQueue();
            this.numberOfNodesEvaluated++;
            if(!visitedContains(checked)){
                addToVisited(checked);
            }
            for (AState neighbor : checked.getNeighbors()) {
                if(neighbor.getParent() == null){
                    neighbor.setParent(checked);
                    inQueue(neighbor,checked);


                }
            }
            if(Objects.equals(checked.toString(), domain.getGoalState().toString())){
                int size = queue.size();
                for(int i = 0; i < size;i++){
                    addToVisited(deQueue());
                }
                break; // The solution was found
            }
        }
        Solution solution = new Solution();
        checked = domain.getGoalState();
        while(checked != parentStartNode){
            solution.addToStart(checked);
            checked = checked.getParent();
        }
        resetVisited();


         return solution;
    }

}
