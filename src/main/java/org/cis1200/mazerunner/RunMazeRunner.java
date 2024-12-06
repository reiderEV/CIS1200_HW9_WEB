package org.cis1200.mazerunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class RunMazeRunner implements Runnable {
    @Override
    public void run() {
        try {
            GameLogic game = new GameLogic("files/mazes/easy10_10.txt");

            JFrame frame = new JFrame("Maze Runner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            MazePanel mazePanel = new MazePanel(game);
            frame.add(mazePanel, BorderLayout.CENTER);

            StatusPanel statusPanel = new StatusPanel(game, mazePanel, frame);
            frame.add(statusPanel, BorderLayout.SOUTH);

            frame.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    String direction = switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> "UP";
                        case KeyEvent.VK_DOWN -> "DOWN";
                        case KeyEvent.VK_LEFT -> "LEFT";
                        case KeyEvent.VK_RIGHT -> "RIGHT";
                        default -> null;
                    };

                    if (direction != null && !game.isGameOver() && game.movePlayer(direction)) {
                        mazePanel.repaint();
                        if (game.isGameOver()) {
                            if (game.isGameWon()) {
                                statusPanel.updateStatus("You Win!");
                            } else {
                                statusPanel.updateStatus("Game Over!");
                            }
                        }
                    }
                }
            });

            frame.setSize(600, 600);
            frame.setVisible(true);
        } catch (IOException e) {
            System.err.println("Failed to load maze: " + e.getMessage());
        }
    }
}
