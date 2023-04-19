package algorithms.search;

import algorithms.mazeGenerators.*;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    BestFirstSearch bestFS = new BestFirstSearch();
    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Best",bestFS.getName());
    }

    @org.junit.jupiter.api.Test
    void inQueue() {
        MazeState ms1,ms2,ms3,ms4;
        ms1 = new MazeState(0,0);
        ms2 = new MazeState(0,1);
        ms3 = new MazeState(1,1);
        ms4 = new MazeState(1,0);
        bestFS.inQueue(ms1,null);
        bestFS.inQueue(ms2,ms1);
        bestFS.inQueue(ms3,ms1);
        bestFS.inQueue(ms4,ms1);
        assertEquals(ms1.getCost(),0);
        assertEquals(ms2.getCost(),10);
        assertEquals(ms3.getCost(),15);
        assertEquals(ms4.getCost(),10);

        assertEquals(bestFS.queue.size(),4);


    }

    @org.junit.jupiter.api.Test
    void deQueue() {
        MazeState ms1,ms2,ms3,ms4;
        ms1 = new MazeState(0,0);
        ms2 = new MazeState(0,1);
        ms3 = new MazeState(1,1);
        ms4 = new MazeState(1,0);
        bestFS.inQueue(ms1,null);
        bestFS.inQueue(ms2,ms1);
        bestFS.inQueue(ms3,ms1);
        bestFS.inQueue(ms4,ms1);
        assertEquals(ms1.getCost(),0);
        assertEquals(ms2.getCost(),10);
        assertEquals(ms3.getCost(),15);
        assertEquals(ms4.getCost(),10);

        assertEquals(bestFS.queue.size(),4);


        assertEquals(bestFS.deQueue(),ms1);
        assertEquals(bestFS.queue.size(),3);
        assertEquals(bestFS.deQueue(),ms2);
        assertEquals(bestFS.queue.size(),2);
        assertEquals(bestFS.deQueue(),ms4);
        assertEquals(bestFS.queue.size(),1);
        assertEquals(bestFS.deQueue(), ms3);
        assertEquals(bestFS.queue.size(),0);
    }

    @org.junit.jupiter.api.Test

    void checkLength(){
        IMazeGenerator mg = new SimpleMazeGenerator();
//            IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10  );
        SearchableMaze sm =new  SearchableMaze(maze);
        Solution solBFS = new BreadthFirstSearch().solve(sm);
        Solution solDFS = new DepthFirstSearch().solve(sm);
        assertTrue(solDFS.getSize() >= solBFS.getSize());

    }

    @org.junit.jupiter.api.Test
    void checkCost(){
        IMazeGenerator mg = new SimpleMazeGenerator();
//            IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(100, 100  );
//        maze.print();
        SearchableMaze sm =new  SearchableMaze(maze);
        Solution solBFS = new BreadthFirstSearch().solve(sm);
        Solution solBest = bestFS.solve(sm);
        int bestCost, bfsCost;
        bestCost = getCost(solBest);

        bfsCost = getCost(solBFS);

        System.out.println("BFS : " + bfsCost +", BEST : " + bestCost);
        assertTrue(bestCost <= bfsCost);




    }

    int getCost(Solution solution ){

        int total = 0;
        int dis,size = solution.getSize();


        for(int i = 0; i < size -1  ; i++){
            AState checked = solution.removeLast();

            dis = checked.getPos().getDistance(solution.getSolutionPath().get(solution.getSize()-1).getPos());


            if (dis == 1){
                total = total + 10;
            }
            else if (dis == 2){

                total = total + 15;
            }

        }
        return total;
    }

    @org.junit.jupiter.api.Test

    void checkMazes(){
        checkSingleMazeGenerator(new MyMazeGenerator());
        checkSingleMazeGenerator(new SimpleMazeGenerator());
        checkSingleMazeGenerator(new EmptyMazeGenerator());
    }

    void checkSingleMazeGenerator(IMazeGenerator mazeGenerator){
        assertNull(mazeGenerator.generate(0, 5));
        assertNull(mazeGenerator.generate(5, 0));
        assertNull(mazeGenerator.generate(0, 0));
    }


}