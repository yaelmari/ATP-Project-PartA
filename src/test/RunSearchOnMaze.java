package test;
import algorithms.mazeGenerators.IMazeGenerator; import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {

        public static void main(String[] args) {
            IMazeGenerator mg = new MyMazeGenerator();
//            IMazeGenerator mg = new SimpleMazeGenerator();
            Maze maze = mg.generate(1000, 1000  );
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            System.out.println("\n\n BFS \n\n");
            solveProblem(searchableMaze, new BreadthFirstSearch());
            System.out.println("\n\n DFS \n\n");
             solveProblem(searchableMaze, new DepthFirstSearch());
//             solveProblem(searchableMaze, new BestFirstSearch());
        }
        private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
            /* Test time */
            long startTime, endTime;
            startTime = System.currentTimeMillis();

            //Solve a searching problem with a searcher
            Solution solution = searcher.solve(domain);
            System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
            //Printing Solution Path
            System.out.println("Solution path:");
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int i = 0; i < solutionPath.size(); i++) {
                System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
            }
            endTime = System.currentTimeMillis();
            System.out.println("The time is: " + (endTime - startTime));
        }
    }


