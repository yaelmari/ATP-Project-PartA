package algorithms.search;

public interface ISearchingAlgorithm {
    public String  getNumberOfNodesEvaluated();


    public String getName();

    Solution solve(ISearchable domain);

//    void search();
}
