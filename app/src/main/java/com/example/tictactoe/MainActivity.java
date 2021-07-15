package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    //player Representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning
    // 0 - X
    // 1 - O
    // 2 - null

    int [][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        // takes the tag of the image and parse it into integer
        int tappedImage = Integer.parseInt(img.getTag().toString());
        // checks if the tapped section is empty or not
        if (!gameActive){
            gameReset(view);
        }
        else if(gameState[tappedImage] == 2){
            // fills the empty state with the number 1 of 0, depending upon the chance of the player
            gameState[tappedImage] = activePlayer;
            // gives the effect such that the image is coming from Y axis, you can set it from X axis also
            img.setTranslationY(-1000f);
            // if it is X's turn (activePlayer = 0) sets next turn to be O's turn
            if(activePlayer == 0){
                // sets the image resource, due to which the image is visible
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                // displays the turn in the status bar below
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            }
            else{
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
            // animates the object such that it is coming from up[
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if player has won
        for( int[] winPosition: winPositions){
            // Winning conditions
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2){
                // somebody has won --> find out who
                String winnerStr;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X won the game! ";
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
                else{
                    winnerStr = "O won the game! ";
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            }
        }

    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i<gameState.length;  i++){
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}