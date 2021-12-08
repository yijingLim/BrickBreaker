package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

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
//        Brick b = new ClayBrick(new Point(1,1), new Dimension(10,10));
//        b.repair();
//        walltesting.wallReset();
//
//        assertEquals(, walltesting.getBrickCount());
        //assertEquals(3,walltesting.getBallCount());
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
    void nextLevel() {

    }

    @Test
    void getLevelCount() {

    }

    @Test
    void setBallXSpeed() {
    }

    @Test
    void setBallYSpeed() {
    }

    @Test
    void resetBallCount() {
    }

    @Test
    void hasLevel() {
        assertTrue(walltesting.hasLevel());
    }
}