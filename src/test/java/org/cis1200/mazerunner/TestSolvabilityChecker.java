package org.cis1200.mazerunner;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

/**
 * Test cases for SolvabilityChecker class.
 */
public class TestSolvabilityChecker {

    private Maze solvableMaze;
    private Maze unsolvableMaze;
    private Maze multipleExitsMaze;
    private SolvabilityChecker solvabilityChecker;

    @BeforeEach
    public void setUp() throws IOException {
        solvableMaze = new Maze();
        solvableMaze.loadFromFile("files/mazes/testmaze.txt");

        unsolvableMaze = new Maze();
        unsolvableMaze.loadFromFile("files/mazes/crazy35_100.txt");

        multipleExitsMaze = new Maze();
        multipleExitsMaze.loadFromFile("files/mazes/hard20_30.txt");
    }

    @Test
    public void testSolvableMaze() {
        solvabilityChecker = new SolvabilityChecker(solvableMaze);
        assertTrue(solvabilityChecker.isSolvable(1, 1), "Solvable maze should return true.");
    }

    @Test
    public void testUnsolvableMaze() {
        solvabilityChecker = new SolvabilityChecker(unsolvableMaze);
        assertFalse(solvabilityChecker.isSolvable(1, 1), "Unsolvable maze should return false.");
    }

    @Test
    public void testMazeWithMultipleExits() {
        solvabilityChecker = new SolvabilityChecker(multipleExitsMaze);
        assertTrue(solvabilityChecker.isSolvable(1, 1), "Maze with multiple exits should return true.");
    }

    @Test
    public void testStartingOnExit() {
        solvabilityChecker = new SolvabilityChecker(solvableMaze);
        assertTrue(solvabilityChecker.isSolvable(1, 8), "Starting on an exit should return true.");
    }

    @Test
    public void testSurroundedByWalls() throws IOException {
        Maze surroundedMaze = new Maze();
        surroundedMaze.loadFromFile("files/mazes/surroundedmaze.txt");

        solvabilityChecker = new SolvabilityChecker(surroundedMaze);
        assertFalse(solvabilityChecker.isSolvable(1, 1), "Maze with player surrounded by walls should return false.");
    }

    @Test
    public void testMazeWithoutExits() throws IOException {
        Maze noExitMaze = new Maze();
        noExitMaze.loadFromFile("files/mazes/noexitmaze.txt");

        solvabilityChecker = new SolvabilityChecker(noExitMaze);
        assertFalse(solvabilityChecker.isSolvable(1, 1), "Maze without exits should return false.");
    }
}