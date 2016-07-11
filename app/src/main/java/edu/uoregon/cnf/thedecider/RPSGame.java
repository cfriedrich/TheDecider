package edu.uoregon.cnf.thedecider;

import java.util.Random;

/**
 * Created by Chris on 7/6/2016.
 */


public class RPSGame {

    Player player1;
    Player player2;

    Random random;

    String winMessage;
    String[] choices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};

    public RPSGame()
    {
        player1 = new Player();
        player2 = new Player();
        random = new Random();
    }

    private class Player {
        String choice;
        String name;
        String decisionName;
        int decision;
        boolean winner;
    }

    public String getPlayer1Weapon()
    {
        return player1.decisionName;
    }

    public String getPlayer2Weapon()
    {
        return player2.decisionName;
    }

    public String getWinMessage()
    {
        return winMessage;
    }


    public void newGame(){
        player1 = new Player();
        player2 = new Player();
    }

    public void decide(){
        player1.decision = random.nextInt(6);
        player1.decisionName = choices[player1.decision];

        player2.decision = random.nextInt(6);
        player2.decisionName = choices[player2.decision];

        determineWinner();
    }

    public int determineWinner() {
        switch (player1.decisionName) {
            case "Rock":
                if (player2.decisionName == "Scissors") {
                    winMessage = "ROCK SMASHES SCISSORS";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Lizard") {
                    winMessage = "ROCK CRUSHES LIZARD";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Paper") {
                    winMessage = "PAPER WRAPS ROCK";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else if (player2.decisionName == "Spock") {
                    winMessage = "SPOCK VAPORIZES ROCK";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else {
                    winMessage = "DRAW";
                    player1.winner = false;
                    player2.winner = false;
                    return 0;
                }
            case "Paper":
                if (player2.decisionName == "Rock") {
                    winMessage = "PAPER WRAPS ROCK";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Spock") {
                    winMessage = "SPOCK VAPORIZES ROCK";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Scissors") {
                    winMessage = "SCISSORS CUT PAPER";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else if (player2.decisionName == "Lizard") {
                    winMessage = "LIZARD EATS PAPER";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else {
                    winMessage = "DRAW";
                    player1.winner = false;
                    player2.winner = false;
                    return 0;
                }
            case "Scissors":
                if (player2.decisionName == "Paper") {
                    winMessage = "SCISSORS CUT PAPER";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Lizard") {
                    winMessage = "SCISSORS DECAPITATE LIZARD";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Rock") {
                    winMessage = "PAPER WRAPS ROCK";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else if (player2.decisionName == "Spock") {
                    winMessage = "SPOCK BENDS SCISSORS";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else {
                    winMessage = "DRAW";
                    player1.winner = false;
                    player2.winner = false;
                    return 0;
                }
            case "Lizard":
                if (player2.decisionName == "Spock") {
                    winMessage = "LIZARD POISONS SPOCK";
                    player1.winner = true;
                    player2.winner = true;
                    return 1;
                } else if (player2.decisionName == "Paper") {
                    winMessage = "LIZARD EATS PAPER";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Rock") {
                    winMessage = "ROCK CRUSHES LIZARD";
                    player1.winner = false;
                    player2.winner = false;
                    return 2;
                } else if (player2.decisionName == "Scissors") {
                    winMessage = "SCISSORS DECAPITATE LIZARD";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else {
                    winMessage = "DRAW";
                    player1.winner = false;
                    player2.winner = false;
                    return 0;
                }
            case "Spock":
                if (player2.decisionName == "Rock") {
                    winMessage = "SPOCK VAPORIZES ROCK";
                    player1.winner = false;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Scissors") {
                    winMessage = "SPOCK BENDS SCISSORS";
                    player1.winner = true;
                    player2.winner = false;
                    return 1;
                } else if (player2.decisionName == "Paper") {
                    winMessage = "PAPER DISPROVES SPOCK";
                    player1.winner = true;
                    player2.winner = false;
                    return 2;
                } else if (player2.decisionName == "Lizard") {
                    winMessage = "LIZARD POISONS SPOCK";
                    player1.winner = false;
                    player2.winner = true;
                    return 2;
                } else {
                    winMessage = "DRAW";
                    player1.winner = false;
                    player2.winner = false;
                    return 0;
                }
        }
        return 0;
    }
}
