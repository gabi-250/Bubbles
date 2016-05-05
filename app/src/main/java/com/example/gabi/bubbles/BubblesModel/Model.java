package com.example.gabi.bubbles.BubblesModel;

import android.graphics.Color;
import android.util.Pair;

import java.util.List;
import java.util.Random;

/**
 * Created by gabi on 05/05/16.
 */
public class Model {

    private int[][] board;
    private static Random random;

    public Model() {

        this(6, 4);
    }

    public Model(int rows, int columns) {

        board = new int[rows][columns];
        random = new Random(Colors.colors.length);
        initialiseColors();
    }

    private void initialiseColors() {

        for(int i = 0; i < board.length; ++i) {

            for(int j = 0; j < board[0].length; ++j) {

                board[i][j] = getRandomColor();
            }
        }
    }

    public int getColor(int row, int column) {

        return board[row][column];
    }

    public int[][] getBoard() {

        return board;
    }

    public void resetColor(List <Pair< Integer, Integer> > squares) {

        for( Pair <Integer, Integer> square: squares) {

            board[square.first][square.second] = getRandomColor();
        }
    }

    private int getRandomColor() {

        return Colors.colors[random.nextInt(Colors.colors.length)];
    }
}
