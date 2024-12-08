package org.cis1200.mazerunner;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class TestMaze {
    private Maze maze;

    @BeforeEach
    public void setUp() {
        maze = new Maze();
    }

    @Test
    public void testLoadFromFile() throws IOException {
        maze.loadFromFile("files/mazes/testmaze.txt");

        // Validate dimensions
        assertEquals(10, maze.getRows());
        assertEquals(10, maze.getCols());

        // Validate corners
        assertEquals('#', maze.getCell(0, 0)); // Top-left
        assertEquals('#', maze.getCell(9, 9)); // Bottom-right

        // Validate player position
        assertEquals('P', maze.getCell(1, 1));

        // Validate exit position
        assertEquals('E', maze.getCell(8, 8));

        // Validate a wall
        assertEquals('#', maze.getCell(3, 4));

        // Validate open space
        assertEquals('.', maze.getCell(2, 2));
    }

    @Test
    public void testSetCell() {
        maze = new Maze(10, 10);
        maze.setCell(5, 5, 'P');
        assertEquals('P', maze.getCell(5, 5));
    }

    @Test
    public void testSetRow() {
        char[] rowData = {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'};
        maze = new Maze(10, 10);
        maze.setRow(0, rowData);
        assertArrayEquals(rowData, maze.getGrid()[0]);
    }

}