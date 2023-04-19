package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected PriorityQueue<AState> queue = new PriorityQueue<AState>();


    @Override
    public String getName() {
        return "BFS";
    }
    protected void inQueue(AState aState,AState parent){

        if(parent != null){
            aState.setCost(parent.getCost()+1);
        }
         queue.add(aState);
    }

    protected AState deQueue(){
        return queue.poll();
    }

    @Override
    public Solution solve(ISearchable domain) {

        numberOfNodesEvaluated = 0;
        MazeState parentStartNode = new MazeState(-1,-1); // create a special Astate that will be used as the parent
                                                                      //for the start state
        domain.getStartState().setParent(parentStartNode);
        inQueue(domain.getStartState(),null);
        AState checked;
        while(!queue.isEmpty()) {
            checked = deQueue();
            this.numberOfNodesEvaluated++;      // add 1 to the nodes avaluated counter
//            System.out.println(checked);
            if(!visitedContains(checked)){      // if not in checked add it
                addToVisited(checked);
            }
            for (AState neighbor : checked.getNeighbors()) {
                if(neighbor.getParent() == null && !visitedContains(neighbor)){       // add all the neighbors to the queue and update its parent
                    neighbor.setParent(checked);
                    inQueue(neighbor,checked);


                }
            }
            if(Objects.equals(checked.toString(), domain.getGoalState().toString())){
                int size = queue.size();
                for(int i = 0; i < size;i++){           // clears the queue after finding the target state
                    addToVisited(deQueue());
                }
                break; // The solution was found
            }
        }
        Solution solution = new Solution();     // create the returned solution
        checked = domain.getGoalState();
        int total = 0;
        while(checked != parentStartNode){
            solution.addToStart(checked);

            if (checked != domain.getStartState() && checked.getPos().getDistance(checked.getParent().getPos()) == 1){
                total = total + 10;
            }
            else if (checked != domain.getStartState() && checked.getPos().getDistance(checked.getParent().getPos()) == 2){

                total = total + 15;
            }

            checked = checked.getParent();
        }
//        domain.getGoalState().setCost(total);
        System.out.println("the cost is: "+ total);
//        System.out.println("last price: " + domain.getGoalState().getCost());

        resetVisited();         // use the reset visited function to clear the visited array


         return solution;
    }

}
