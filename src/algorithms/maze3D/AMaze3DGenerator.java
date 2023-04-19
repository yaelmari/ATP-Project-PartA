package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    public long measureAlgorithmTimeMillis(int depth, int row, int column)
    {
        long startTime, endTime;
        Maze3D maze3d;
        startTime = System.currentTimeMillis();
        maze3d = this.generate(depth, row, column);
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
