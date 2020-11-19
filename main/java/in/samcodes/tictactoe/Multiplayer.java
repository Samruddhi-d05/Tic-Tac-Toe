package in.samcodes.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Multiplayer extends AppCompatActivity {
    // 0=Empty, 1=X, 2=O;
    int activeplayer, tappedCounter, NoTappedCounters;
    int[] gameState={0,0,0,0,0,0,0,0,0};
    boolean gameActive;
    int[][] winningPositions={{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
    TextView winDisplay;
    Button button;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        activeplayer=1; gameActive=true; NoTappedCounters=0;
        winDisplay= findViewById(R.id.textView);
        button= findViewById(R.id.button);
        gridLayout= findViewById(R.id.gridLayout);
        winDisplay.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
    }

    public void Deploy(View view){
        ImageView counter= (ImageView) view;
        tappedCounter= Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter-1]==0 && gameActive) {
            NoTappedCounters+=1;
            counter.setTranslationY(-1500);
            gameState[tappedCounter - 1] = activeplayer;
            if (activeplayer == 1) {
                counter.setImageResource(R.drawable.x);
                activeplayer = 2;
            } else if (activeplayer == 2) {
                counter.setImageResource(R.drawable.o);
                activeplayer = 1;
            }
            counter.animate().translationYBy(1500).rotation(3600);
        }
        CheckWinner();
    }

    public void CheckWinner(){
        for(int[]winningPosition: winningPositions){
            if(gameState[winningPosition[0]-1]==gameState[winningPosition[1]-1] && gameState[winningPosition[1]-1]==gameState[winningPosition[2]-1] && gameState[winningPosition[2]-1]!=0){
                gameActive=false;
                if(activeplayer==2){
                    //X has Won
                    winDisplay.setText("X has Won!!");
                }
                else if(activeplayer==1){
                    //O has won
                    winDisplay.setText("O has Won!!");
                }
                winDisplay.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
            else if(NoTappedCounters==9){
                //Tie
                winDisplay.setText("It is a Tie...");
                winDisplay.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        }
    }
    public void playAgain(View view){
        activeplayer=1; gameActive=true; NoTappedCounters=0;
        winDisplay.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        for(int i=0;i<gridLayout.getChildCount();i++){
            gameState[i]=0;
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }
}