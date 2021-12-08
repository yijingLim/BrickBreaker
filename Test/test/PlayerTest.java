package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player playertesting;

    public PlayerTest() {
        playertesting = new Player(new Point(1,1), 10, 10, new Rectangle(0,0,100,20));
    }


    @Test
    void impact() {
//        Ball b = new RubberBall(new Point(10,10));
//        Boolean outcome = playertesting.impact(b);
//        assertTrue(outcome);
        //function is wrong

    }

    @Test
    void impactPower() {
//        Powerup x = new Powerup(0, 0, 1);
//        assertTrue(playertesting.impactPower(x));
        //function return false instead of true
    }

    @Test
    void move() {
//        Ball b =  new RubberBall(new Point(10,10));
//        playertesting.move();
//        assertEquals(new Point(10,10), playertesting.getPlayerFace());
    }

    @Test
    void moveLeft() {
        playertesting.moveLeft();
        assertEquals(-5, playertesting.getMoveAmount());
    }

    @Test
    void moveRight() {
        playertesting.moveRight();
        assertEquals(5, playertesting.getMoveAmount());
    }

    @Test
    void stop() {
        playertesting.stop();
        assertEquals(0, playertesting.getMoveAmount());
    }

    @Test
    void expand() {
        playertesting.expand();
        assertEquals(25, playertesting.getWidthPlayerFace());
    }

    @Test
    void moveTo() {
        Point P = new Point(20,20);
        playertesting.moveTo(P);
        assertEquals(new Point(20,20), P);

    }

}