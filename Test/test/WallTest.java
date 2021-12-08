package test;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private Rectangle Area = new Rectangle(0,0,20,20);
    private Point ballPos = new Point(1,1);
    private Wall walltesting = new Wall(Area,10,10,1.0,ballPos);

    @Test
    void move() {
    }

    @Test
    void findImpacts() {
    }

    @Test
    void powerDropDown() {
    }

    @Test
    void getBrickCount() {
        assertEquals(0,walltesting.getBrickCount());
    }

    @Test
    void getBallCount() {
        assertEquals(0, walltesting.getBallCount());
    }

    @Test
    void isBallLost() {
        assertFalse(walltesting.isBallLost());
    }

    @Test
    void ballReset() {
    walltesting.ballReset();
    assertFalse(walltesting.isBallLost());
    }

    @Test
    void wallReset() {
        Brick b = new ClayBrick(new Point(1,1), new Dimension(10,10));
        if (b.isBroken()){
            walltesting.wallReset();
        }
        assertEquals(0, walltesting.getBrickCount());
        assertEquals(3, walltesting.getBallCount());
    }

    @Test
    void resetPlayer() {
        assertEquals(150, walltesting.resetPlayer());
    }

    @Test
    void isDone() {
        assertTrue(walltesting.isDone());
    }

    @Test
    void getLevelCount() {
        if (walltesting.hasLevel()) {
            assertEquals(0, walltesting.getLevelCount());
        }
    }


    @Test
    void resetBallCount() {
        walltesting.resetBallCount();
        assertEquals(3, walltesting.getBallCount());
    }

    @Test
    void hasLevel() {
        assertTrue(walltesting.hasLevel());
    }

    @Test
    void setBallXSpeed() {
        walltesting.setBallXSpeed(3);
        assertEquals(3,walltesting.ball.getSpeedX());
    }

    @Test
    void setBallYSpeed() {
        walltesting.setBallYSpeed(5);
        assertEquals(5,walltesting.ball.getSpeedY());
    }
}