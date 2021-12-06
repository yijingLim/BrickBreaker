package test;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class SortHighScore {


    boolean flag = false;
    String firstPlace = null;
    String secondPlace = null;
    String thirdPlace = null;
    String leaderboard;
    String HighScore;


    /**
     * @param Score  New Score of the game
     * @param score1 first place score in previous leaderboard
     * @param score2 second place score in previous leaderboard
     * @param score3 third place score in previous leaderboard
     * @param name1  first place name in previous leaderboard
     * @param name2  second place name in previous leaderboard
     * @param name3  third place name in previous leaderboard
     */
    public SortHighScore(int Score, int score1, int score2, int score3, String name1, String name2, String name3) {
        if (Score > score3) {
            score3 = Score;
            flag = true;
        }

        if (Score > score2) {
            score3 = score2;
            score2 = Score;
            flag = true;
        }

        if (Score > score1) {
            score2 = score1;
            score1 = Score;
            flag = true;

        }
        if (flag == true) {
            String name = JOptionPane.showInputDialog("Congratulations, You've Set a New High Score! What's your name? ");
            if (Score == score1) {
                firstPlace = score1 + "," + name;
            } else if (Score == score2) {
                secondPlace = score2 + "," + name;
            } else if (Score == score3) {
                thirdPlace = score3 + "," + name;
            }
        }
        if (firstPlace != null) {
            secondPlace = score2 + "," + name1;
            thirdPlace = score3 + "," + name2;
        } else if (secondPlace != null) {
            firstPlace = score1 + "," + name1;
            thirdPlace = score3 + "," + name2;
        } else if (thirdPlace != null) {
            firstPlace = score1 + "," + name1;
            secondPlace = score2 + "," + name2;
        }

        System.out.println("Score of the game: " + Score);
        System.out.println(firstPlace + " " + secondPlace + " " + thirdPlace);
        HighScore = HighScoreLeaderboard();
    }


    public String HighScoreLeaderboard() {
        if(flag ==true) {
            leaderboard =  firstPlace + " " + secondPlace + " " + thirdPlace;
        }
        return leaderboard;
    }
}



