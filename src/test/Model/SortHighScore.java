package test.Model;

import javax.swing.*;

public class SortHighScore {


    boolean flag = false;
    public String firstPlace;
    public String secondPlace;
    public String thirdPlace;
    public String leaderboard;
    public String HighScore;

    /**
     * @param Score  New Score of the game
     * @param score1 first place score in previous leaderboard
     * @param score2 second place score in previous leaderboard
     * @param score3 third place score in previous leaderboard
     * @param name1  first place name in previous leaderboard
     * @param name2  second place name in previous leaderboard
     *
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
                setFirstPlace(score1 + "," + name);
            } else if (Score == score2) {
                setSecondPlace(score2 + "," + name);
            } else if (Score == score3) {
                setThirdPlace(score3 + "," + name);
            }
        }
        if (getFirstPlace() != null) {
            setSecondPlace(score2 + "," + name1);
            setThirdPlace(score3 + "," + name2);
        } else if (getSecondPlace() != null) {
            setFirstPlace(score1 + "," + name1);
            setThirdPlace(score3 + "," + name2);
        } else if (getThirdPlace() != null) {
            setFirstPlace(score1 + "," + name1);
            setSecondPlace(score2 + "," + name2);
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


    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(String thirdPlace) {
        this.thirdPlace = thirdPlace;
    }
}



