package test;

import org.junit.jupiter.api.Test;
import test.Model.SortHighScore;

import static org.junit.jupiter.api.Assertions.*;

/**
 * To test if the scores are stored into a string value
 */
class SortHighScoreTest {
    SortHighScore s = new SortHighScore(3,5, 4, 2, "A", "B","C");


    @Test
    void highScoreLeaderboard() {
        assertEquals("5,A 4,B 3,D", s.HighScoreLeaderboard());
    }
}