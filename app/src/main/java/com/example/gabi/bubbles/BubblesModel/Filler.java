package com.example.gabi.bubbles.BubblesModel;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a utility that finds the area of the board that needs to be replaced with new tiles.
 * The area is identified by finding the maximum number (the largest area) of adjacent squares
 * of the same color as the tile that has been chosen (by tapping).
 */
public class Filler {

    private static Model model;
    private static boolean[][] filledBoard;
    private static List< Pair<Integer, Integer > > squares;
    private static final int[] moveRow = {-1, 0, 1, 0};
    private static final int[] moveColumn = {0, 1, 0, -1};

    /**
     * Returns true if there are no more possible moves.
     * @return true, if the game is over, false otherwise.
     */
    public boolean checkGameOver() {

        filledBoard = new boolean[model.getBoard().length][model.getBoard()[0].length];

        for (int i = 0; i < model.getBoard().length; ++i) {

            for (int j = 0; j < model.getBoard()[0].length; ++j) {

                squares = new ArrayList<>();

                fill(i, j, model.getColor(i, j));

                if (squares.size() > 1) {

                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a list of the squares that form an area (of the same color) around the tile
     * with the specified coordinate. The 'fill' algorithm begins at row 'row' and column 'column'
     * @param row the row of the tile
     * @param column the column of the tile
     * @return the list of squares to be replaced.
     */
    public List< Pair<Integer, Integer > > getChangedSquares(int row, int column) {

        filledBoard = new boolean[model.getBoard().length][model.getBoard()[0].length];
        squares = new ArrayList<>();

        fill(row, column, model.getColor(row, column));

        return squares;
    }

    /**
     * 'Fills' the maximum possible area of tiles of the same color: creates a list with
     * the tiles forming this area. Starts 'filling' at a position specified as
     * row, column, color tuples
     * @param row the current row
     * @param column the current column
     * @param color the color of the starting tile
     */
    private void fill(int row, int column, int color) {

        if( isValid(row, column) && filledBoard[row][column] == false
                && model.getColor(row, column) == color) {

            squares.add(new Pair <> (row, column));
            filledBoard[row][column] = true;

            for(int i = 0; i < 4; ++i) {

                fill(row + moveRow[i], column + moveColumn[i], color);
            }
        }
    }
    
    /**
     * Sets the model of this 'filler' to the specified one
     * @param model the model to associate with this 'filler'
     */
    public void setModel(Model model) {

        this.model = model;
    }

    /**
     * Checks if a given position in the matrix is valid.
     * @param row the row of the tile
     * @param column the column of the tile
     * @return true if the row and column are within the bounds of the array, false otherwise
     */
    private boolean isValid(int row, int column ) {

        return (row >= 0 && row < filledBoard.length && column >= 0 && column < filledBoard[0].length);
    }
}
