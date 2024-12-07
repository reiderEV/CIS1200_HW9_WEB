package org.cis1200.mazerunner;

public class SolvabilityChecker {

    private Maze maze;
    private boolean[][] visited;

    public SolvabilityChecker(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
    }

    public boolean isSolvable(int startX, int startY) {
        return dfs(startX, startY);
    }

    private boolean dfs(int x, int y) {
        if (!isValidCell(x, y)) { //if out of bounds, wall, or already visited, return false
            return false;
        }

        if (maze.getCell(x, y) == 'E') {
            return true;
        }

        visited[x][y] = true; //mark current as visited

        for (int[] dir : new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (dfs(newX, newY)) {
                return true; //found a path to the exit
            }
        }

        return false; //no path from this cell
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < maze.getRows()
                && y >= 0 && y < maze.getCols()
                && !visited[x][y]
                && maze.getCell(x, y) != '#'; // '#' is a wall
    }
}