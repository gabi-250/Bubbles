package com.example.gabi.bubbles.BubblesModel;

import android.graphics.Color;
import android.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Represents the model of the 'Bubbles' game. It contains a matrix of integers representing
 * the color of each tile and it permits the removal of tiles: the selected tiles (adjacent tiles
 * of the same color) are replaced by the tiles on top and the space at the 'top' of the matrix
 * is filled with random colors.
 *
 */
public class Model {

    private static int[][] board;
    private static int score;
    private static Random random;
    private static boolean created = false;

    /**
     * Creates a default model, for a board that has 6 rows and 4 columns.
     */
    public Model() {

        this(6, 4);
    }

    /**
     * Creates a model for the game board of a specified number of rows and columns
     * @param rows the number of rows the board should have
     * @param columns the number of columns the board should have
     */
    public Model(int rows, int columns) {

        if (created == false) {

            board = new int[rows][columns];
            random = new Random(Colors.colors.length);
            score = 0;
            created = true;
            initialiseColors();
        }
    }

    /**
     * Resets the game by generating new tiles for the entire board and setting the score to 0.
     */
    public void reset() {

        score = 0;
        initialiseColors();
    }

    private void initialiseColors() {

        for(int i = 0; i < board.length; ++i) {

            for(int j = 0; j < board[0].length; ++j) {

                board[i][j] = getRandomColor();
            }
        }
    }

    /**
     * Returns the color of a given tile.
     * @param row the row of the tile
     * @param column the column of the tile
     * @return an integer representing the color of the tile
     */
    public int getColor(int row, int column) {

        return board[row][column];
    }

    /**
     * Returns the board of the game.
     * @return an array of integers representing colors of tiles.
     */
    public int[][] getBoard() {

        return board;
    }

    /**
     * Resets the color of the squares, after selected ones are removed.
     * @param squares the list of squares selected for removal.
     */
    public void resetColor(List <Pair< Integer, Integer> > squares) {

        Collections.sort(squares, new Comparator<Pair<Integer, Integer>>() {

            @Override
            public int compare(Pair<Integer, Integer> lhs, Pair<Integer, Integer> rhs) {

                if (lhs.first < rhs.first) {

                    return -1;

                } else if (lhs.first > rhs.first) {

                    return 1;

                } else {

                    return lhs.second - rhs.second;
                }
            }
        });

        for( Pair <Integer, Integer> square: squares) {

            removeSquare(square.first, square.second);
        }
    }

    /**
     * Returns the current score of the game.
     * @return the score
     */
    public int getScore() {

        return score;
    }

    /**
     * Removes square at position (row, column) by replacing it with all the squares above.
     * @param row the row of the tile to be removed
     * @param column the column of the tile to be removed
     */
    private void removeSquare(int row, int column) {

        ++score;

        int i = row;

        for(i = row; i > 0; --i) {

            board[i][column] = board[i - 1][column];
        }

        board[0][column] = getRandomColor();
    }

    /**
     * Generates a random color from the array of allowed colors.
     * @return a random color
     */
    private int getRandomColor() {

        return Colors.colors[random.nextInt(Colors.colors.length)];
    }
}