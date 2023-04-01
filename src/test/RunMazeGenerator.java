package test;
import algorithms.mazeGenerators.*;
//public class RunMazeGenerator {
//    public static void main(String[] args) {
////        testMazeGenerator(new EmptyMazeGenerator());
////        testMazeGenerator(new SimpleMazeGenerator());
////        testMazeGenerator(new MyMazeGenerator());
//        AMazeGenerator mazaG = new SimpleMazeGenerator();
//        long time = mazaG.measureAlgorithmTimeMillis(1000, 1000);
//        System.out.print(time);
//    }
//    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
//        // prints the time it takes the algorithm to run System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));
//        // generate another maze
//        Maze maze = mazeGenerator.generate(100/*rows*/, 100/*columns*/);
//        // prints the maze
//        maze.print();
//        // get the maze entrance
//        Position startPosition = maze.getStartPosition();
//        // print the start position
//        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
//        // prints the maze exit position
//        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
//    }
//}

import algorithms.mazeGenerators.IMazeGenerator; import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator; import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath(); for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        } }
}