package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    private ArrayList<AState> visited = new ArrayList<>();
    protected int numberOfNodesEvaluated = 0;


    protected void resetVisited(){
//        System.out.println("\n\nbefore: " + visited.size());
        AState aState;
        int size = visited.size();
//        this.numberOfNodesEvaluated = size;
        for (int i = 0 ; i<size;i++) {
            aState = visited.get(0);
            aState.setParent(null);
            aState.setCost(0);
            visited.remove(0);
        }
//        System.out.println("after: " + visited.size());
    }
    protected void addToVisited(AState aState){
        visited.add(aState);
    }

    protected boolean visitedContains(AState aState){
        return visited.contains(aState);
    }

    public String getNumberOfNodesEvaluated() {
        return Integer.toString(numberOfNodesEvaluated);
    }

}
