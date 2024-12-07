package org.cis1200.mazerunner;

import java.io.*;

public class GameLogic {
    private Maze maze;
    private int playerX;
    private int playerY;
    private boolean gameOver;
    private boolean gameWon;
    private String filepath;
    private boolean solvable;

    public GameLogic(String file) throws IOException {
        maze = new Maze();
        loadFromFile(file);
        filepath = file;
        SolvabilityChecker checker = new SolvabilityChecker(maze);
        boolean solvable = checker.isSolvable(playerX, playerY);
        System.out.println("Maze solvable?:" + solvable);
    }

    private void findPlayerPosition() {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                if (maze.getCell(i, j) == 'P') {
                    playerX = i;
                    playerY = j;
                    return;
                }
            }
        }
        throw new IllegalStateException("Player start position not found in the maze.");
    }

    public boolean movePlayer(String direction) {
        int newX = playerX;
        int newY = playerY;
        System.out.println("Player moving!");
        switch (direction) {
            case "UP" -> newX--;
            case "DOWN" -> newX++;
            case "LEFT" -> newY--;
            case "RIGHT" -> newY++;
            default -> {
                System.out.println("Invalid direction: " + direction);
                return false;
            }
        }

        if (newX < 0 || newX >= maze.getRows() || newY < 0 || newY >= maze.getCols()) {
            return false; // Out of bounds
        }

        char destinationCell = maze.getCell(newX, newY);

        if (destinationCell == '#') {
            return false; // Wall collision
        } else if (destinationCell == 'T') {
            System.out.println("Trapdoor! You lose.");
            gameOver = true;
            gameWon = false;
        } else if (destinationCell == 'E') {
            System.out.println("Found the exit! You win.");
            maze.setCell(playerX, playerY, '.');
            playerX = newX;
            playerY = newY;
            maze.setCell(playerX, playerY, 'P');
            gameOver = true;
            gameWon = true;
        } else {
            maze.setCell(playerX, playerY, '.');
            playerX = newX;
            playerY = newY;
            maze.setCell(playerX, playerY, 'P');
            return true;
        }
        return false;
    }

    public void saveGame(String saveFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files/mazes/savegames/" + saveFileName))) {
            writer.write(maze.getRows() + " " + maze.getCols());
            writer.newLine();
            writer.write("P=" + playerX + "," + playerY);
            writer.newLine();
            writer.write("GAMEOVER=" + gameOver);
            writer.newLine();
            writer.write("GAMEWON=" + gameWon);
            writer.newLine();
            for (char[] row : maze.getGrid()) {
                writer.write(new String(row));
                writer.newLine();
            }
        }
    }

    public void loadSaveFile(String saveFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("files/mazes/savegames/" + saveFileName))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            maze = new Maze(rows, cols);
            String playerPosition = reader.readLine();
            String[] pos = playerPosition.substring(2).split(",");
            playerX = Integer.parseInt(pos[0]);
            playerY = Integer.parseInt(pos[1]);

            gameOver = Boolean.parseBoolean(reader.readLine().split("=")[1]);
            gameWon = Boolean.parseBoolean(reader.readLine().split("=")[1]);

            for (int i = 0; i < rows; i++) {
                maze.setRow(i, reader.readLine().toCharArray());
            }
        }
    }

    public void loadFromFile(String filePath) throws IOException {
        maze.loadFromFile(filePath);
        findPlayerPosition();
        gameOver = false;
        gameWon = false;
        filepath = filePath;
    }

    public void resetFromFile(String filePath) throws IOException {
        maze.loadFromFile(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            maze = new Maze(rows, cols);

            for (int i = 0; i < rows; i++) {
                maze.setRow(i, reader.readLine().toCharArray());
            }
        }
        findPlayerPosition();
        gameOver = false;
        gameWon = false;
        filepath = filePath;
    }

    public Maze getMaze() {
        return maze;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public String getFilepath() {
        return filepath;
    }
}
