package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0][0] = findViewById(R.id.button1);
        buttons[0][1] = findViewById(R.id.button2);
        buttons[0][2] = findViewById(R.id.button3);
        buttons[1][0] = findViewById(R.id.button4);
        buttons[1][1] = findViewById(R.id.button5);
        buttons[1][2] = findViewById(R.id.button6);
        buttons[2][0] = findViewById(R.id.button7);
        buttons[2][1] = findViewById(R.id.button8);
        buttons[2][2] = findViewById(R.id.button9);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick((Button) v);
                    }
                });
            }
        }

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the current activity and return to the menu
            }
        });
    }

    private void onButtonClick(Button button) {
        if (!button.getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                showWinnerDialog("X wins!");
            } else {
                showWinnerDialog("O wins!");
            }
        } else if (roundCount == 9) {
            showWinnerDialog("Draw!");
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void showWinnerDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage("Game Over")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> resetBoard())
                .show();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }
}
