package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator) {
        // prints the time it takes the algorithm to run
//        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(1000/*rows*/, 1000/*columns*/)));
        // generate another maze
        Maze3D maze3d = mazeGenerator.generate(4, 4, 4);

//        Maze3D maze3d = new Maze3D(3, 3, 5, new Position3D(0, 0,0 ),
//                new Position3D(0, 2, 4));
        // prints the maze
        maze3d.print();
        // get the maze entrance
//        Position startPosition = maze.getStartPosition();
        // print the start position
//        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
//        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
