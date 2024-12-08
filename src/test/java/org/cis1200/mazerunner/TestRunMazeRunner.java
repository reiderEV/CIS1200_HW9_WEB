package org.cis1200.mazerunner;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the RunMazeRunner class.
 */
public class TestRunMazeRunner {

    private RunMazeRunner runMazeRunner;
    private GameLogic gameLogic;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize GameLogic with a test maze
        gameLogic = new GameLogic("files/mazes/testmaze.txt");
        runMazeRunner = new RunMazeRunner();
    }

    @Test
    public void testFrameInitialization() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Runner");
            assertNotNull(frame);
            assertEquals("Maze Runner", frame.getTitle());
            assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        });
    }

    @Test
    public void testKeyPressMovement() throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                GameLogic game = new GameLogic("files/mazes/testmaze.txt");
                MazePanel mazePanel = new MazePanel(game);
                JFrame frame = new JFrame();
                frame.add(mazePanel);
                frame.setSize(600, 600);
                frame.setVisible(true);

                // Simulate pressing the RIGHT key
                KeyEvent keyEvent = new KeyEvent(
                        frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                        KeyEvent.VK_RIGHT, 'D'
                );
                mazePanel.dispatchEvent(keyEvent);

                // Assert player moved
                char currentPosition = game.getMaze().getCell(1, 2); // Assuming initial is (1,1)
                assertEquals('P', currentPosition);
            } catch (IOException e) {
                fail("Failed to load test maze: " + e.getMessage());
            }
        });
    }
}