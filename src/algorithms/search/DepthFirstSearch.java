package algorithms.search;

public class DepthFirstSearch implements ISearchingAlgorithm{
    private int[][] visitedCells;



    @Override
    public String getNumberOfNodesEvaluated() {
        return null;
    }


    @Override
    public String getName() {
        return "DFS";
    }

    @Override
    public Solution solve(ISearchable domain) {
        Solution sol = new Solution();
        AState start = domain.getStartState();




        return sol;
    }

//    @Override
//    public void search() {
//
//    }
}
