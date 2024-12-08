package org.cis1200.mazerunner;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class TestGameLogic {

    GameLogic game;

    @BeforeEach
    public void setUp() throws IOException {
        game = new GameLogic("files/mazes/testmaze.txt"); // load a sample test maze
    }

    @Test
    public void testLoadMaze() {
        assertEquals(10, game.getMaze().getRows(), "Maze should have 10 rows");
        assertEquals(10, game.getMaze().getCols(), "Maze should have 10 columns");
        assertFalse(game.isGameOver(), "Game should not be over initially");
        assertFalse(game.isGameWon(), "Game should not be won initially");
    }

    @Test
    public void testMovePlayer() {
        assertTrue(game.movePlayer("DOWN"), "Player should be able to move down");
        assertEquals('.', game.getMaze().getCell(1, 1), "Old position should be empty");
        assertEquals('P', game.getMaze().getCell(2, 1), "New position should have the player");
    }

    @Test
    public void testWallCollision() {
        // wall above player should prevent movement in that direction
        assertFalse(game.movePlayer("UP"), "Player should not move into a wall");
        assertEquals(
                'P', game.getMaze().getCell(1, 1), "Player should remain in original position"
        );
    }

    @Test
    public void testTrapdoor() {
        game.movePlayer("DOWN");
        game.movePlayer("DOWN"); // move to 3,1
        assertTrue(game.isGameOver(), "Game should be over when stepping on a trapdoor");
        assertFalse(game.isGameWon(), "Game should not be won when stepping on a trapdoor");
    }

    @Test
    public void testWinGame() throws IOException {
        game.movePlayer("RIGHT");
        game.movePlayer("RIGHT");
        game.movePlayer("RIGHT");
        game.movePlayer("RIGHT");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.movePlayer("DOWN");
        game.movePlayer("RIGHT");
        game.movePlayer("RIGHT");
        game.movePlayer("RIGHT");
        assertTrue(game.isGameOver(), "Game should be over when reaching the exit");
        assertTrue(game.isGameWon(), "Game should be won when reaching the exit");
    }

    @Test
    public void testResetGame() throws IOException {
        game.movePlayer("DOWN"); // Move player
        game.resetFromFile(game.getFilepath());

        assertEquals('P', game.getMaze().getCell(1, 1), "Player should reset to original position");
        assertFalse(game.isGameOver(), "Game should not be over after reset");
    }

    @Test
    public void testSolvabilityCheck() throws IOException {
        // test solvable maze
        GameLogic solvableGame = new GameLogic("files/mazes/easy10_10.txt");
        assertTrue(solvableGame.isSolvable(), "Solvable maze should return true");

        // test unsolvable maze
        GameLogic unsolvableGame = new GameLogic("files/mazes/crazy35_100.txt");
        assertFalse(unsolvableGame.isSolvable(), "Unsolvable maze should return false");
    }
}