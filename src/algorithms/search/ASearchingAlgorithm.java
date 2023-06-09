package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    private ArrayList<AState> visited = new ArrayList<>();
    protected int numberOfNodesEvaluated = 0;


    /**
     * this function is used to reset all the data in the visited array which means it sets the parent
     * as null and the cost as 0
     * @return int for the number of Astate that their parent is not null.
     */
    protected int resetVisited(){
//        System.out.println("\n\nbefore: " + visited.size());
        AState aState;
        int size = visited.size();
//        this.numberOfNodesEvaluated = size;
        int cnt = 0;
        for (int i = 0 ; i<size;i++) {
            aState = visited.get(0);
            if(aState.getParent() != null){cnt++;}
            aState.setParent(null);
            aState.setCost(0);
            visited.remove(0);
        }
        return cnt;
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
