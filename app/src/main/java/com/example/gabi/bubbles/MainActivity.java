package com.example.gabi.bubbles;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gabi.bubbles.BubblesModel.Filler;
import com.example.gabi.bubbles.BubblesModel.Model;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    private Button buttonReset;
    private TextView textView;
    private Filler filler;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        model = new Model();
        filler = new Filler();
        filler.setModel(model);

        initialiseTextView();
        initialiseButtons();
    }

    /**
     * Initialises the score to the one inside the model.
     */
    private void initialiseTextView() {

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(getResources().getString(R.string.score) + model.getScore());
    }

    private void initialiseButtons() {

        initialiseTiles();
        buttonReset = (Button) findViewById(R.id.buttonRestart);

        buttonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                model.reset();
                repaintBoard();
            }
        });
    }

    /**
     *  Paints the buttons as instructed by model.
     */
    private void initialiseTiles() {

        buttons = new Button[6][4];

        for(int i = 0; i < 6; ++i) {

            for(int j = 0; j < 4; ++j) {

                buttons[i][j] = getButton(i, j);
                buttons[i][j].setBackgroundColor(model.getColor(i, j));
                buttons[i][j].setOnClickListener(getOnClickListener(i, j) );
            }
        }
    }

    private View.OnClickListener getOnClickListener(final int row, final int column) {

        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                List<Pair<Integer, Integer>> squares = filler.getChangedSquares(row, column);

                /*
                 * More than one square matched
                 */
                if (squares.size() > 1) {

                    model.resetColor(squares);
                    repaintBoard();
                }

                /*
                 * No more possible areas to remove
                 */
                if (filler.checkGameOver()) {

                    textView.setTextColor(Color.RED);
                    textView.setText("Game Over");
                }
            }
        };
    }

    private Button getButton(int i, int j) {

        String buttonName = "button" + i + j;

        int id = getResources().getIdentifier(buttonName, "id", "com.example.gabi.bubbles");

        return (Button) findViewById(id);
    }

    private void repaintBoard() {

        textView.setText( getResources().getString(R.string.score) + model.getScore());
        textView.setTextColor(Color.WHITE);

        for(int i = 0; i < 6; ++i) {

            for(int j = 0; j < 4; ++j) {

                buttons[i][j].setBackgroundColor(model.getColor(i, j));
            }
        }
    }
}
