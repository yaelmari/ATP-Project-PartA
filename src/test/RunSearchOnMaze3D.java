package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {

        public static void main(String[] args) {
            IMaze3DGenerator mg = new MyMaze3DGenerator();
//            IMazeGenerator mg = new SimpleMazeGenerator();
            Maze3D maze3d = mg.generate(100, 100, 100  );
            maze3d.print();
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze3d);

            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
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
            System.out.println(solutionPath.size());
            endTime = System.currentTimeMillis();
            System.out.println("The time is: " + (endTime - startTime));
        }
    }


