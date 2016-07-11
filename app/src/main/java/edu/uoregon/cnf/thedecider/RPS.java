package edu.uoregon.cnf.thedecider;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RPS extends AppCompatActivity implements View.OnClickListener {

    RPSGame game;
    MediaPlayer mediaPlayer;
    Button btnDecide;
    ImageView p1ImageView;
    ImageView p2ImageView;
    ImageView versusImageView;
    TextView winMessageTextView;
    TextView p1ChoiceTextView;
    TextView p2ChoiceTextView;

    int player1WeaponDraw;
    int player2WeaponDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);

        btnDecide = (Button)findViewById(R.id.decideButton);
        p1ImageView = (ImageView)findViewById(R.id.player1ImageView);
        p2ImageView = (ImageView)findViewById(R.id.player2ImageView);
        versusImageView = (ImageView)findViewById(R.id.versusImageView);
        winMessageTextView = (TextView)findViewById(R.id.winnerDesciptionTextView);

        btnDecide.setOnClickListener(this);
        game = new RPSGame();
    }

    public void showPlayer1Weapon()
    {
        btnDecide.setVisibility(View.GONE);
        switch (game.getPlayer1Weapon()) {
            case "Rock":
                player1WeaponDraw = R.drawable.rockleft;
                p1ImageView.setImageResource(R.drawable.rockleft);
                break;
            case "Paper":
                player1WeaponDraw = R.drawable.paperleft;
                p1ImageView.setImageResource(R.drawable.paperleft);
                break;
            case "Scissors":
                player1WeaponDraw = R.drawable.scissorsleft;
                p1ImageView.setImageResource(R.drawable.scissorsleft);
                break;
            case "Lizard":
                player1WeaponDraw = R.drawable.lizardleft;
                p1ImageView.setImageResource(R.drawable.lizardleft);
                break;
            case "Spock":
                player1WeaponDraw = R.drawable.spockleft;
                p1ImageView.setImageResource(R.drawable.spockleft);
                break;
        }
    }

    public void showPlayer2Weapon()
    {
        switch (game.getPlayer2Weapon()) {
            case "Rock":
                player2WeaponDraw = R.drawable.rockright;
                p2ImageView.setImageResource(R.drawable.rockright);
                break;
            case "Paper":
                player2WeaponDraw = R.drawable.paperright;
                p2ImageView.setImageResource(R.drawable.paperright);
                break;
            case "Scissors":
                player2WeaponDraw = R.drawable.scissorsright;
                p2ImageView.setImageResource(R.drawable.scissorsright);
                break;
            case "Lizard":
                player2WeaponDraw = R.drawable.lizardright;
                p2ImageView.setImageResource(R.drawable.lizardright);
                break;
            case "Spock":
                player2WeaponDraw = R.drawable.spockright;
                p2ImageView.setImageResource(R.drawable.spockright);
                break;
        }
    }

    public void showVersus()
    {
        versusImageView.setImageResource(R.drawable.vs);
    }

    public void showWinMessage()
    {
        winMessageTextView.setText(game.getWinMessage());
        btnDecide.setVisibility(View.VISIBLE);
    }

    public void displayWinner()
    {
        p1ImageView.setVisibility(View.GONE);
        p2ImageView.setVisibility(View.GONE);
        switch(game.determineWinner())
        {
            case 1:
                versusImageView.setImageResource(player1WeaponDraw);
                break;
            case 2:
                versusImageView.setImageResource(player2WeaponDraw);
                break;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.decideButton:
                game.decide();
                game.determineWinner();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPlayer1Weapon();
                    }
                }, 1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showVersus();
                    }
                }, 1500);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showPlayer2Weapon();
                    }
                }, 2000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showWinMessage();
                    }
                }, 2500);
        }
    }
}
