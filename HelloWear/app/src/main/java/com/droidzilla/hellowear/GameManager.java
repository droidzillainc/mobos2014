package com.droidzilla.hellowear;

import java.util.Random;

/**
 * Created by root on 7/1/2014.
 */
public class GameManager {

    private static GameManager instance = null;

    private int[][] matrix = new int[4][4];
    private Random random = new Random();

    public static class GameOverException extends Exception {

    }

    private GameManager() {

    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private void checkGameOver() throws GameOverException {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    return;
                }
            }
        }
        throw new GameOverException();

    }

    private void addRandomTile() throws GameOverException {
        checkGameOver();
        int x, y;
        do {
            x = random.nextInt(matrix.length);
            y = random.nextInt(matrix[0].length);
        } while (matrix[x][y] != 0);
        matrix[x][y] = random.nextInt(10) != 0 ? 2 : 4;

    }

    public void newGame() throws GameOverException {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }

        addRandomTile();
        addRandomTile();
    }

    public void display() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }


    public void up() throws GameOverException {
        for (int i = 0; i < matrix[0].length; i++) {
            int[] values = new int[matrix.length];
            for (int j = 0; j < matrix.length; j++) {
                values[j] = matrix[j][i];
            }
            int[] result = compress(values);
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = result[j];
            }
        }
        addRandomTile();
    }

    public void down() throws GameOverException {
        for (int i = 0; i < matrix[0].length; i++) {
            int[] values = new int[matrix.length];
            for (int j = 0; j < matrix.length; j++) {
                values[j] = matrix[matrix.length - j - 1][i];
            }
            int[] result = compress(values);
            for (int j = 0; j < matrix.length; j++) {
                matrix[matrix.length - j - 1][i] = result[j];
            }
        }
        addRandomTile();
    }

    public void left() throws GameOverException {
        for (int i = 0; i < matrix.length; i++) {
            int[] values = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                values[j] = matrix[i][j];
            }
            int[] result = compress(values);
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = result[j];
            }
        }
        addRandomTile();
    }

    public void right() throws GameOverException {
        for (int i = 0; i < matrix.length; i++) {
            int[] values = new int[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                values[j] = matrix[i][matrix[i].length - j - 1];
            }
            int[] result = compress(values);
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][matrix[i].length - j - 1] = result[j];
            }
        }
        addRandomTile();
    }

    // this should work with size 4 arrays
    public static int[] compress(int[] values) {
        // size should be 4 ;)
        int size = values.length;

        int[] result = new int[size];
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (values[i] != 0) {
                result[newSize++] = values[i];
            }
        }

        for (int i = 0; i < newSize - 1; i++) {
            if (result[i] == result[i + 1]) {
                result[i] = result[i] * 2;
                for (int j = i + 1; j < newSize - 1; j++) {
                    result[j] = result[j + 1];
                }
                newSize--;
            }
        }
        for (int i = newSize; i < size; i++) {
            result[i] = 0;
        }
        return result;
    }

    public static void main(String[] args) throws GameOverException {
        int[][] tests = {
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 2, 0, 0},
                {2, 2, 0, 0},
                {0, 2, 2, 0},
                {4, 2, 2, 0},
                {2, 2, 4, 4},
                {2, 4, 2, 2},
                {2, 4, 2, 4}
        };
        for (int[] test : tests) {
            int[] result = GameManager.compress(test);
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }

        GameManager manager = new GameManager();
        System.out.println("========");
        manager.newGame();
        manager.display();
        System.out.println("***UP***");
        manager.up();
        manager.display();
        System.out.println("========");

        manager.display();
        System.out.println("**DOWN**");
        manager.down();
        manager.display();
        System.out.println("========");

        manager.display();
        System.out.println("**LEFT**");
        manager.left();
        manager.display();
        System.out.println("========");

        manager.display();
        System.out.println("**RIGHT*");
        manager.right();
        manager.display();

    }

}
