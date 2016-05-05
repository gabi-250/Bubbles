package com.example.gabi.bubbles.BubblesModel;

import android.graphics.Color;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabi on 05/05/16.
 */
public class Filler {

    private static Model model;
    private static boolean[][] filledBoard;
    private static List< Pair<Integer, Integer > > squares;
    private static final int[] moveRow = {-1, 0, 1, 0};
    private static final int[] moveColumn = {0, 1, 0, -1};

    public static List< Pair<Integer, Integer > > getChangedSquares(int[][] board, int row, int column) {

        filledBoard = new boolean[ board.length ][ board[0].length ];
        squares = new ArrayList <> ();

        fill(row, column, board[row][column]);

        return squares;
    }

    public void setModel(Model model) {

        this.model = model;
    }

    private static void fill(int row, int column, int color) {

        if( isValid(row, column) && filledBoard[row][column] == false && model.getColor(row, column) == color) {

            squares.add(new Pair <> (row, column));
            filledBoard[row][column] = true;

            for(int i = 0; i < 4; ++i) {

                fill(row + moveRow[i], column + moveColumn[i], color);
            }
        }
    }

    private static boolean isValid(int row, int column ) {

        return (row >= 0 && row < filledBoard.length && column >= 0 && column < filledBoard[0].length);
    }
}
