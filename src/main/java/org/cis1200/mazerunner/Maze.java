package org.cis1200.mazerunner;

import java.io.*;

public class Maze {
    private char[][] grid;
    private int rows;
    private int cols;

    public Maze() {
    }

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new char[rows][cols];
    }

    public void loadFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] dimensions = br.readLine().split(" ");
            rows = Integer.parseInt(dimensions[0]);
            cols = Integer.parseInt(dimensions[1]);
            grid = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                grid[i] = br.readLine().toCharArray();
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setRow(int row, char[] rowData) {
        grid[row] = rowData;
    }

    public char getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCell(int x, int y, char cell) {
        grid[x][y] = cell;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
