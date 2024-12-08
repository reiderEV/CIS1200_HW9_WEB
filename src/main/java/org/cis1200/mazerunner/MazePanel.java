package org.cis1200.mazerunner;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private GameLogic game;

    public MazePanel(GameLogic game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int rows = game.getMaze().getRows();
        int cols = game.getMaze().getCols();

        // realtime cell resizing
        int cellWidth = panelWidth / cols;
        int cellHeight = panelHeight / rows;
        int cellSize = Math.min(cellWidth, cellHeight);

        char[][] grid = game.getMaze().getGrid();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row][col];

                if (cell == '#') {
                    g.setColor(Color.BLACK); // WALL
                } else if (cell == '.') {
                    g.setColor(Color.WHITE); // OPEN SPACE
                } else if (cell == 'P') {
                    g.setColor(Color.BLUE); // PLAYER (ADD IMAGE OVERLAY LATER)
                } else if (cell == 'T') {
                    g.setColor(Color.RED); // TRAPDOOR
                } else if (cell == 'E') {
                    g.setColor(Color.GREEN); // EXIT
                }

                int x = col * cellSize;
                int y = row * cellSize;
                g.fillRect(x, y, cellSize, cellSize);

                // g.setColor(Color.GRAY);
                // g.drawRect(x, y, cellSize, cellSize); //grid lines?
            }
        }
        // Draw grid lines
        g.setColor(Color.GRAY);
        for (int row = 0; row <= rows; row++) {
            int y = row * cellSize;
            g.drawLine(0, y, cols * cellSize, y); // Horizontal line
        }
        for (int col = 0; col <= cols; col++) {
            int x = col * cellSize;
            g.drawLine(x, 0, x, rows * cellSize); // Vertical line
        }
    }

}
