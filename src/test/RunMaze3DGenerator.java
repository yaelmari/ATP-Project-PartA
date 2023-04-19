package test;

import algorithms.maze3D.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
//        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(1000/*rows*/, 1000/*columns*/)));
        // generate another maze
//        Maze3D maze3d = mazeGenerator.generate(100/*rows*/, 100/*columns*/);

        Maze3D maze3d = new Maze3D(3, 3, 5, new Position3D(0, 0, 0),
                new Position3D(0, 2, 4));
        // prints the maze
        maze3d.print();
        ISearchable maze = new SearchableMaze3D(maze3d);
        solveProblem(maze,new BreadthFirstSearch());
        solveProblem(maze,new DepthFirstSearch());
        solveProblem(maze,new BestFirstSearch());

    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        /* Test time */
        long startTime, endTime;
        startTime = System.currentTimeMillis();

        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println();
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int i = 0; i < solutionPath.size(); i++) {
                System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
            }
//        System.out.println(solutionPath.size());
        endTime = System.currentTimeMillis();
        System.out.println("The time is: " + (endTime - startTime));
    }



        // get the maze entrance
//        Position startPosition = maze.getStartPosition();
        // print the start position
//        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
//        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }

