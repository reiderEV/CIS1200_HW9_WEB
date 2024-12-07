package org.cis1200.mazerunner;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StatusPanel extends JPanel {
    private final JLabel statusLabel;
    private final JButton saveButton;
    private final JButton loadButton;
    private final JButton resetButton;

    public StatusPanel(GameLogic game, MazePanel mazePanel, JFrame frame) {
        setLayout(new FlowLayout());

        statusLabel = new JLabel("Move using arrow keys.");
        add(statusLabel);

        saveButton = new JButton("Save Game");
        saveButton.addActionListener(e -> {
            try {
                game.saveGame("save1.txt");
                frame.requestFocus();
                JOptionPane.showMessageDialog(frame, "Game saved successfully!",
                        "Save Game", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Failed to save game: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(saveButton);

        loadButton = new JButton("Load Game");
        loadButton.addActionListener(e -> {
            try {
                game.loadSaveFile("save1.txt");
                mazePanel.repaint();
                statusLabel.setText("Game loaded!");
                frame.requestFocus();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Failed to load game: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(loadButton);

        resetButton = new JButton("Reset Game");
        resetButton.addActionListener(e -> {
            try {
                game.resetFromFile(game.getFilepath()); //add here
                mazePanel.repaint();
                statusLabel.setText("Game reset!");
                frame.requestFocus();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Failed to reset game: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(resetButton);
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }
}
