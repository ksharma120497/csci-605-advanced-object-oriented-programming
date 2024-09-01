package HW1;

public class FindPathThroughMaze {


    static boolean deadEnd = true;



    public static void main(String[] args) {

        int[][] maze = {{-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 },
                        {-9, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -9 },
                        {-9, -1, -1,  0,  0,  0,  0, -1,  0,  0,  0, -1, -9 },
                        {-9, -1, -1,  0, -1, -1, -1, -1,  0, -1,  0, -1, -9 },
                        {-9, -1, -1,  0,  0,  0,  0,  0,  0, -1,  0, -1, -9 },
                        {-9, -1,  0, -1, -1, -1,  0, -1,  0,  0,  0, -1, -9 },
                        {-9, -1,  0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -9 },
                        {-9, -1,  0, -1, -1,  0,  0, -1, -1, -1, -1, -1, -9 },
                        {-9, -1,  0, -1, -1,  0, -1, -1, -1, -1, -1, -1, -9 },
                        {-9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 }};

        int entryRow = 2;
        int entryColumn = 3;


        System.out.println(findMazePath(maze, entryRow, entryColumn));


    }

    public static int findMazePath(int[][] maze, int entryRow, int entryColumn) {
        return dfs(maze, entryRow, entryColumn, 0);
    }

    public static int dfs(int[][] maze, int row, int col, int count) {
        boolean left = row >= 0;
        boolean right = row < maze.length;
        boolean up = col >= 0;
        boolean down = col < maze[0].length;
        int u=0,d=0,l=0,r=0;

        if (!left || !right || !up || !down) {
            return 0;
        }


        if (maze[row][col] == -9 ) {
            deadEnd=false;
            return count;
        }

        if (maze[row][col] == -1) {
            return 0;
        }
        maze[row][col] = -1;

        d = dfs(maze, row + 1, col, count + 1);
        if(deadEnd)
        l = dfs(maze, row, col - 1, count + 1);
        if(deadEnd)
        r = dfs(maze, row, col + 1, count + 1);
        if(deadEnd)
        u = dfs(maze, row - 1, col, count + 1);

        return l + r + u + d;

    }
}
