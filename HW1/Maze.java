package HW1;

public class Maze {

    static boolean deadEnd = true;
    static boolean destroyFlag = false;

    public static int min(int a, int b){
        if(a < b)
            return a;
        return b;
    }

    public static void main(String[] args) {
        int[][] maze =
                {{-9, -1, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 },
                {-9, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -9 },
                {-9, -1, -1,  0,  0,  0,  0, -1,  0, -1, -1, -1, -9 },
                {-9, -1, -1,  0, -1, -1, -1, -1,  0, -1, -1, -1, -9 },
                {-9, -1,  0,  0, -1,  0,  0,  0,  0, -1, -1, -1, -9 },
                {-9, -1,  0, -1, -1, -1,  0, -1,  0, -1, -1, -1, -9 },
                {-9, -1,  0, -1, -1, -1,  0, -1, -1, -1, -1, -1, -9 },
                {-9, -1,  0, -1, -1,  0,  0, -1, -1, -1, -1, -1, -9 },
                {-9, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -9 },
                {-9, -1, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 }};
        int entryRow = 2;
        int entryColumn = 3;
        int exitRow = 8;
        int exitColumn = 5;
//        System.out.println(findMazePathWithWalls(maze, entryRow, entryColumn,exitRow, exitColumn));
        int[][] forward = bfs(maze,entryRow, entryColumn);
        int[][] backward = bfs(maze, exitRow, exitColumn);
        int leastPath = 10000000;
        for(int i=0;i<forward.length;i++){
            for(int j=0;j<forward[1].length;j++)
                System.out.print(forward[i][j] + " ");
            System.out.print("|| ");
            for(int j=0;j<backward[1].length;j++)
                System.out.print(backward[i][j] + " ");
            System.out.println("");
        }
        for(int i=0;i<forward.length;i++){
            for(int j=0;j<forward[i].length;j++){
                if(forward[i][j] > 0){
                    if(i-2 > 0 && backward[i-2][j] > 0)
                        leastPath = min(leastPath, forward[i][j] + backward[i - 2][j]);
                    if(j-2 > 0 && backward[i][j-2] > 0)
                        leastPath = min(leastPath, forward[i][j] + backward[i][j-2]);
                    if(i+2 < maze.length && backward[i+2][j] > 0)
                        leastPath = min(leastPath, forward[i][j] + backward[i + 2][j]);
                    if(j+2 < maze[0].length && backward[i][j+2] > 0)
                        leastPath = min(leastPath, forward[i][j] + backward[i][j+2]);
                }
            }
        }
        System.out.println(leastPath+2);

    }

    public static int[][] bfs(int[][] maze, int row, int col){
        int m = maze.length;
        int n = maze[0].length;
//        System.out.println(m + " " + n);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] forward = new int[maze.length][maze[0].length];
        String q = "/";
        int idx = 0;
        visited[row][col] = true;
        forward[row][col] = 1;
        q += row + "/" + col + "/";
        while(true){
            String tempRow = "";
            idx++;
            while(q.charAt(idx) != '/'){
                tempRow += q.charAt(idx);
                idx++;
            }
            String tempCol = "";
            idx++;
            while(q.charAt(idx) != '/'){
                tempCol += q.charAt(idx);
                idx++;
            }
            int tRow = Integer.valueOf(tempRow);
            int tCol = Integer.valueOf(tempCol);
//            System.out.println(tRow + " " + tCol);
//            System.out.println(maze[tRow-1][tCol] + " " + maze[tRow][tCol-1] + " " +  maze[tRow+1][tCol] + " " + maze[tRow][tCol+1] );
            if(tRow-1 >= 0 && visited[tRow-1][tCol] == false && maze[tRow-1][tCol] == 0){
                q += (tRow-1) + "/" + tCol + "/";
                forward[tRow-1][tCol] = forward[tRow][tCol]+1;
                visited[tRow-1][tCol] = true;
            }
            if(tCol-1 >= 0 && visited[tRow][tCol-1] == false && maze[tRow][tCol-1] == 0){
                q += tRow + "/" + (tCol-1) + "/";
                forward[tRow][tCol-1] = forward[tRow][tCol]+1;
                visited[tRow][tCol-1] = true;
            }
            if(tRow+1 < m && visited[tRow+1][tCol] == false && maze[tRow+1][tCol] == 0){
                q += (tRow+1) + "/" + tCol + "/";
                forward[tRow+1][tCol] = forward[tRow][tCol]+1;
                visited[tRow+1][tCol] = true;
            }
            if(tCol+1 < n && visited[tRow][tCol+1] == false && maze[tRow][tCol+1] == 0) {
                q += tRow + "/" + (tCol + 1) + "/";
                forward[tRow][tCol + 1] = forward[tRow][tCol] + 1;
                visited[tRow][tCol + 1] = true;
            }
//            System.out.println(q);
//            System.out.println("--------------------");
            if(idx == q.length()-1)
                break;
        }
        return forward;
    }

    public static int findMazePathWithWalls(int[][] maze, int entryRow, int entryColumn, int exitRow, int exitColumn){
        return dfsWithWalls(maze, entryRow, entryColumn, exitRow, exitColumn,0);
    }

    public static int dfsWithWalls(int[][] maze, int row, int col, int exitRow, int exitColumn,int count){
        boolean left = row >= 0;
        boolean right = row < maze.length;
        boolean up = col >= 0;
        boolean down = col < maze[0].length;
        int u=0,d=0,l=0,r=0;

        if (!left || !right || !up || !down) {
            return 0;
        }

        if (row==exitRow && col==exitColumn ) {
            deadEnd=false;
            return count;
        }

        if (maze[row][col] == -1) {
            if(!destroyFlag){
                destroyFlag=true;
                return count;
            }
            return 0;
        }
        maze[row][col] = -1;
        d = dfsWithWalls(maze, row + 1, col, count + 1,exitRow,exitColumn);
        if(deadEnd)
            l = dfsWithWalls(maze, row, col - 1, count + 1,exitRow,exitColumn);
        if(deadEnd)
            r = dfsWithWalls(maze, row, col + 1, count + 1,exitRow,exitColumn);
        if(deadEnd)
            u = dfsWithWalls(maze, row - 1, col, count + 1,exitRow,exitColumn);

        return l + r + u + d;
    }
}
