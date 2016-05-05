package com.example.gabi.bubbles.BubblesModel;

import android.graphics.Color;
import android.util.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by gabi on 05/05/16.
 */
public class Model {

    private int[][] board;
    private static Random random;
    private int score;

    public Model() {

        this(6, 4);
    }

    public Model(int rows, int columns) {

        board = new int[rows][columns];
        random = new Random(Colors.colors.length);
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

    public int getColor(int row, int column) {

        return board[row][column];
    }

    public int[][] getBoard() {

        return board;
    }

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

    public int getScore() {

        return score;
    }

    private void removeSquare(int row, int column) {

        ++score;

        int i = row;

        for(i = row; i > 0; --i) {

            board[i][column] = board[i - 1][column];
        }

        board[0][column] = getRandomColor();
    }

    private int getRandomColor() {

        return Colors.colors[random.nextInt(Colors.colors.length)];
    }
}
