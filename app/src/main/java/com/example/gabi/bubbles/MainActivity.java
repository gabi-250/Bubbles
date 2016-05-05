package com.example.gabi.bubbles;

import android.content.pm.ActivityInfo;
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
        textView = (TextView) findViewById(R.id.textView);

        textView.setText("Score: 0");

        initialiseButtons();
    }

    private void initialiseButtons() {

        buttons = new Button[6][4];

        for(int i = 0; i < 6; ++i) {

            for(int j = 0; j < 4; ++j) {

                String buttonName = "button" + i + j;

                int id = getResources().getIdentifier(buttonName, "id", "com.example.gabi.bubbles");

                buttons[i][j] = (Button) findViewById(id);

                buttons[i][j].setBackgroundColor(model.getColor(i, j));

                final int row = i, column = j;

                buttons[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        List<Pair<Integer, Integer>> squares = filler.getChangedSquares(model.getBoard(), row, column);

                        if (squares.size() > 1) {

                            model.resetColor(squares);
                            textView.setText("Score: " + model.getScore());
                            repaintBoard();
                        }
                    }
                });
            }
        }
    }

    private void repaintBoard() {

        for(int i = 0; i < 6; ++i) {

            for(int j = 0; j < 4; ++j) {

                buttons[i][j].setBackgroundColor(model.getColor(i, j));
            }
        }
    }
}
