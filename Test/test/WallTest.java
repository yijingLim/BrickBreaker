package test;

import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Controller.BrickController;
import test.Controller.PlayerController;
import test.Model.ClayBrick;
import test.Model.Powerup;
import test.Model.RubberBall;
import test.Model.Wall;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing of Wall class
 */
class WallTest {
    private Rectangle Area = new Rectangle(0,0,20,20);
    private Point ballPos = new Point(1,1);
    private Wall walltesting = new Wall(Area,30,3,1.0,ballPos);

    PlayerController a = new PlayerController(new Point(10,10), 10, 10, new Rectangle(0,0,100,20));
    BallController b = new RubberBall(new Point(10,10));

    /**
     * Test the total number of brick count in a new level
     */
    @Test
    void TestBrickCount(){
        walltesting.nextLevel();
        assertEquals(31, walltesting.getBrickCount());
    }

    /**
     * Test the move of player
     */
    @Test
    void move() {
        PlayerController p = new PlayerController(new Point(10,10), 10, 10, new Rectangle(0,0,100,20));

        BallController b = new RubberBall(new Point(10,10));
        b.setXSpeed(5);
        b.setYSpeed(5);
        walltesting.move();

        assertEquals(new Point(5,10), p.getPlayerFace().getBounds().getLocation());
        assertEquals(new Point(10,10), b.getPosition());
    }

    /**
     * Test impact of ball and brick on wall in different direction
     */
    @Test
    void findImpacts() {
        int BallspeedY = b.getSpeedY();
        int BallspeedX = b.getSpeedX();

        /**Test if ball reverse when ball impact the player */
        a.impact(b);
        assertEquals(-BallspeedY, b.getSpeedY());

        /**Test if ball reverse when ball impact border */
        b.moveTo(new Point (601, 451));
        walltesting.findImpacts();
        assertEquals(-BallspeedX, b.getSpeedX());

        /**Test if ball reverse when ball in the area frame */
        b.moveTo(new Point (601, 20));
        walltesting.findImpacts();
        assertEquals(-BallspeedY, b.getSpeedY());

    }


    /**
     * Test if when impact power up with player in type1, playerface width increase by 15
     */
    @Test
    void powerDropDownExtraBall() {
        Powerup p = new Powerup(10,10, 0);
        a.getPlayerFace();
        BallController b = new RubberBall(new Point(10,10));
        b.setXSpeed(5);
        b.setYSpeed(5);
        a.impactPower(p);
        walltesting.PowerDropDown();
        assertEquals(5,b.getSpeedX());
    }

    @Test
    void powerDropDownExpandPlayer() {
//        Powerup p = new Powerup(10, 10, 1);
//        walltesting.setType(1);
//        a.impactPower(p);
//        walltesting.PowerDropDown();
       //assertEquals(25, a.getPlayerFace());
    }



    /**
     * get total number of brick Count
     */
    @Test
    void getBrickCount() {
        assertEquals(0,walltesting.getBrickCount());
    }

    /**
     * get the number of ball in the wall
     */
    @Test
    void getBallCount() {
        assertEquals(0, walltesting.getBallCount());
    }

    /**
     * check if when ball is lost, return false
     */
    @Test
    void isBallLost() {
        assertFalse(walltesting.isBallLost());
    }

    /**
     * test if ball is reset, ball lost is false
     */
    @Test
    void ballReset() {
    walltesting.ballReset();
    assertFalse(walltesting.isBallLost());
    }

    /**
     * Test if wall is reset, the total ball count is reset to 3
     */
    @Test
    void wallReset() {
        BrickController b = new ClayBrick(new Point(1,1), new Dimension(10,10));
        if (b.isBroken()){
            walltesting.wallReset();
        }
        assertEquals(3, walltesting.getBallCount());
    }

    /**
     * Test when player is reset
     */
    @Test
    void resetPlayer() {
        assertEquals(150, walltesting.resetPlayer());
    }

    /**
     * Test if wall is done
     */
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


    /**
     * Test if when ball is reset, the ball count is reset to 3
     */
    @Test
    void resetBallCount() {
        walltesting.resetBallCount();
        assertEquals(3, walltesting.getBallCount());
    }

    /**
     * Test if has level return true
     */
    @Test
    void hasLevel() {
        assertTrue(walltesting.hasLevel());
    }

    /**
     * Test ball speed x is set
     */
    @Test
    void setBallXSpeed() {
        walltesting.setBallXSpeed(3);
        assertEquals(3,walltesting.ball.getSpeedX());
    }

    /**
     * Test if ball speed y is set
     */
    @Test
    void setBallYSpeed() {
        walltesting.setBallYSpeed(5);
        assertEquals(5,walltesting.ball.getSpeedY());
    }
}