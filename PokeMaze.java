/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author Taqy
 */
import java.util.Scanner;
import java.util.Stack;

public class PokeMaze {
    private char[][] maze;
    private Stack<int[]> pathStack; // To store the player's path
    private int playerRow;
    private int playerCol; // Player's current position
    private boolean gameRunning;

    public PokeMaze() {
        // Initialize the maze here with the correct layout including walls ('#'), paths ('.'), Ghastly ('G'), start ('S') and end ('E').
        // Assume the starting point (S) is at (1, 1)
        playerRow = 1;
        playerCol = 1;
        pathStack = new Stack<>();
        gameRunning = true;
        
        // You'll need to populate this layout based on the maze provided in the PDF.
        maze = new char[][]{
                // This is a simplified example layout, adjust according to the provided maze
                "#####################".toCharArray(),
                "#S...#.......G.....E#".toCharArray(),
                "###.#.#.###.###.###.#".toCharArray(),
                "#...#...#G#...#.....#".toCharArray(),
                "#.#####.#.#.#.#####.#".toCharArray(),
                "#.............#.....#".toCharArray(),
                "#####################".toCharArray()
        };
    }

    public void runMaze() {
        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            printMaze();
            System.out.println("Enter direction (up, down, left, right): ");
            String direction = scanner.nextLine();
            processInput(direction);
        }
    }

    private void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print('P'); // Use 'P' to indicate the player's current position
                } else {
                    System.out.print(maze[i][j]);
                }
            }
            System.out.println();
        }
    }

    private void processInput(String input) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (input.trim().toLowerCase()) {
            case "up":
                newRow--;
                break;
            case "down":
                newRow++;
                break;
            case "left":
                newCol--;
                break;
            case "right":
                newCol++;
                break;
            default:
                System.out.println("Invalid input. Please enter 'up', 'down', 'left', or 'right'.");
                return;
        }

        if (isValidMove(newRow, newCol)) {
            // Update player position
            playerRow = newRow;
            playerCol = newCol;
            // Push the new position onto the stack
            pathStack.push(new int[]{newRow, newCol});

            // Check for the end of the maze
            if (maze[newRow][newCol] == 'E') {
                System.out.println("Congratulations! You've reached the end of the maze.");
                gameRunning = false;
            }

            // Check for an encounter with a Ghastly
            if (maze[newRow][newCol] == 'G') {
                System.out.println("Oh no! You encountered a Ghastly and got caught. Game Over.");
                gameRunning = false;
            }
        } else {
            System.out.println("You can't move in that direction. Try again.");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[row].length && maze[row][col] != '#';
    }

    public static void main(String[] args) {
        PokeMaze pokeMaze = new PokeMaze();
        pokeMaze.runMaze();
    }
}

