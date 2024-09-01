package HW1;

public class FindPathThroughMazeOne {
    /*		---> column
     *		|
     *		|
     *		v
     *		row
     * The maze has no circles
     */

    static int[][] maze =
            {{-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 },
            {-9, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -9 },
            {-9, -1, -1,  0,  0,  0,  0, -1,  0,  0,  0, -1, -9 },
            {-9, -1, -1,  0, -1, -1, -1, -1,  0, -1,  0, -1, -9 },
            {-9, -1, -1,  0,  0,  0,  0,  0,  0, -1,  0, -1, -9 },
            {-9, -1,  0, -1, -1, -1,  0, -1,  0,  0,  0, -1, -9 },
            {-9, -1,  0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -9 },
            {-9, -1,  0, -1, -1,  0,  0, -1, -1, -1, -1, -1, -9 },
            {-9, -1,  0, -1, -1,  0, -1, -1, -1, -1, -1, -1, -9 },
            {-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 } };
    static int entryRow = 1;
    static int entryColumn = 3;

    static int rowLength = maze.length;
    static int columnLength = maze[0].length;

    static int solvedMaze[][] = new int[rowLength][columnLength];

    public static void printMaze(int maze[][])	{
        for ( int row = 0; row < maze.length; row++ )	{
            for ( int column = 0; column < maze[0].length; column++ ){
                if(maze[row][column]==-1)
                    System.out.print("*\t");
                else if(maze[row][column]==-9)
                    System.out.print("-\t");
                else if(maze[row][column]==0)
                    System.out.print(":\t");
            }
            System.out.println("");
        }
    }
    static int pathLength = 0;
    public static void findNextFree(int row, int column) {


        if(maze[row][column]==-9) {
            System.out.println("path length: " + pathLength);
            return;
        }

        if(maze[row][column]==0) {
            pathLength++;
            solvedMaze[row][column]=0;
            findNextFree(row,column+1);
            findNextFree(row+1,column);
        }
        else if(maze[row][column]==-1){
            solvedMaze[row][column]=-1;
        }
        printMaze(solvedMaze);
        System.out.println(pathLength);
    }

    public static void main(String[] args){
        findNextFree(entryRow, entryColumn);
        //printMaze(maze);
    }
}