package test;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private Rectangle Area = new Rectangle(0,0,20,20);
    private Point ballPos = new Point(1,1);
    private Wall walltesting = new Wall(Area,10,10,1.0,ballPos);

    PlayerController a = new PlayerController(new Point(10,10), 10, 10, new Rectangle(0,0,100,20));
    BallController b = new RubberBall(new Point(10,10));

    @Test
    void move() {
        PlayerController p = new PlayerController(new Point(10,10), 10, 10, new Rectangle(0,0,100,20));

        BallController b = new RubberBall(new Point(10,10));
        b.setSpeed(5, 5);
        walltesting.move();

        assertEquals(new Point(5,10), p.getPlayerFace().getBounds().getLocation());
        assertEquals(new Point(10,10), b.getPosition());
    }

    @Test
    void findImpacts() {
        int BallspeedY = b.getSpeedY();
        int BallspeedX = b.getSpeedX();
        Brick Claybrick = new ClayBrick(new Point(1,1),new Dimension(10,10));

        /**Test if ball reverse when ball impact the player */
        a.impact(b);
        assertEquals(-BallspeedY, b.getSpeedY());

        /**Test if ballcount subtracted when ball impact the Bricks */
//        walltesting.findImpacts();
//        assertEquals(29, walltesting.getBrickCount());

        /**Test if ball reverse when ball impact border */
        b.moveTo(new Point (601, 451));
        walltesting.findImpacts();
        assertEquals(-BallspeedX, b.getSpeedX());

        /**Test if ball reverse when ball in the area frame */
        b.moveTo(new Point (601, 20));
        walltesting.findImpacts();
        assertEquals(-BallspeedY, b.getSpeedY());

//        /**Test if ball does not reverse when ball in out of the area frame */
//        b.moveTo(new Point (601, -20));
//
//        walltesting.findImpacts();
//        assertTrue(walltesting.isBallLost());

    }

    @Test
    void powerDropDown() {
//        Powerup p= new Powerup(100,100, 1);
//        PlayerController A = new PlayerController(new Point(10,10), 10, 10, new Rectangle(0,0,100,20));
//        BallController b = new RubberBall(new Point(10,10));
//        walltesting.PowerDropDown();
//        A.impact(b);
//        assertTrue(b.reverseY());

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