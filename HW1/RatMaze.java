package HW1;

/* Java program to solve Rat in
a Maze problem using backtracking */

import java.util.Arrays;

public class RatMaze {

    // Size of the maze
    static int N;
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

    static int rowLength = maze.length;
    static int columnLength = maze[0].length;

    static int sol[][] = { {-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 },
                            {-9, 1, 1,  1, 1, 1, 1, 1, 1, 1, 1, 1, -9 },
                            {-9, 1, 1,  1,  1,  1,  1, 1,  1,  1,  1, 1, -9 },
                            {-9, 1, 1,  1, 1, 1, 1, 1,  1, 1,  1, 1, -9 },
                            {-9, 1, 1,  1,  1,  1,  1,  1,  1, 1,  1, 1, -9 },
                            {-9, 1,  1, 1,1, 1,  1, 1,  1,  1,  1, 1, -9 },
                            {-9, 1,  1, 1, 1, 1,  1, 1, 1, 1, 1, 1, -9 },
                            {-9, 1,  1, 1, 1,  1,  1, 1, 1, 1, 1, 1, -9 },
                            {-9, 1,  1, 1, 1,  1, 1, 1, 1, 1, 1, 1, -9 },
                            {-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 } };

    /* A utility function to print
    solution matrix sol[N][N] */
    void printSolution(int sol[][])
    {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++)
                System.out.print(
                        " " + sol[i][j] + " ");
            System.out.println();
        }
    }

    /* A utility function to check
        if x, y is valid index for N*N maze */
    boolean isSafe(
            int maze[][], int x, int y)
    {
        // if (x, y outside maze) return false
        return ( maze[x][y] == 0);
    }

    /* This function solves the Maze problem using
    Backtracking. It mainly uses solveMazeUtil()
    to solve the problem. It returns false if no
    path is possible, otherwise return true and
    prints the path in the form of 1s. Please note
    that there may be more than one solutions, this
    function prints one of the feasible solutions.*/
    boolean solveMaze(int maze[][])
    {


        if (solveMazeUtil(maze, 1, 3, sol) == false) {
            System.out.println("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }

    /* A recursive utility function to solve Maze
    problem */
    boolean solveMazeUtil(int maze[][], int x, int y,
                          int sol[][])
    {
        // if (x, y is goal) return true
        if (maze[x][y] == -9) {
            sol[x][y] = -9;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y) == true) {
            System.out.println("hello");
            // Check if the current block is already part of solution path.\
            if (sol[x][y] == 0)
                return false;

            // mark x, y as part of solution path
            sol[x][y] = 0;

            /* Move forward in x direction */
            if (solveMazeUtil(maze, x + 1, y, sol))
                return true;

			/* If moving in x direction doesn't give
			solution then Move down in y direction */
            if (solveMazeUtil(maze, x, y + 1, sol))
                return true;

			/* If none of the above movements works then
			BACKTRACK: unmark x, y as part of solution
			path */
            sol[x][y] = -1;
            return false;
        }

        return false;
    }

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

    public static void main(String args[])
    {
        RatMaze rat = new RatMaze();
        N = maze.length;
        rat.solveMaze(maze);
        printMaze(maze);
        printMaze(sol);
        System.out.println(Arrays.deepToString(sol));
    }
}

