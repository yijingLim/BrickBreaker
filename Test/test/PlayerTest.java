package test;

import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Controller.PlayerController;
import test.Model.Powerup;
import test.Model.RubberBall;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private PlayerController playertesting;

    public PlayerTest() {
        playertesting = new PlayerController(new Point(10,10), 10, 10, new Rectangle(0,0,100,20));
    }


    @Test
    void impact() {
        BallController b = new RubberBall(new Point(10,10));
        Boolean outcome = playertesting.impact(b);
        assertTrue(outcome);

    }

    @Test
    void impactPower() {
        Powerup x = new Powerup(5, 10, 0);
        playertesting.getPlayerFace();
        Boolean outcome = playertesting.impactPower(x);
        assertTrue(outcome);

    }

    @Test
    void move() {
        playertesting.move();
        assertEquals(new Point(5,10), playertesting.getPlayerFace().getBounds().getLocation()); //(ballPoint.x - (int) playerFace.getWidth() / 2, ballPoint.y) = (10-10/2,10)
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