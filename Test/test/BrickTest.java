package test;

import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Controller.BrickController;
import test.Model.ClayBrick;
import test.Model.ImpactedDirection;
import test.Model.RubberBall;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {

    public Dimension dimension = new Dimension(1,1);
    public final Color inner = new Color(1,1,1);
    public final Color border = new Color(1,1,1);
    private Point2D BallPos = new Point(1,1);

    public BrickController bricktesting;

    private BrickTest() {
        bricktesting = new BrickController("Name", new Point(1, 1), dimension, border, inner, 1) {
            @Override
            public Shape makeBrickFace(Point pos, Dimension size)
            {
                return null;
            }

            @Override
            public Shape getBrick() {
                return null;
            }
        };
    }

    @Test
    Shape makeBrickFace(Point2D ballPos, Dimension dimension) {

        return null;
    }

    /**
     * Test if brick is impacted
     */
    @Test
    void setImpact() {
        BrickController brick1 = new ClayBrick(new Point(100, 100), new Dimension(100,100));
        brick1.setImpact(new Point(1, 1), 1);
        assertTrue(brick1.isBroken());
    }

    /**
     * Test if ball is impacted at all direction
     */
    @Test
    void findImpact() {
        BallController ball = new RubberBall(new Point(100, 100));

        bricktesting.impact();
        ImpactedDirection choice = bricktesting.findImpact(ball);
        assertEquals(ImpactedDirection.NO_IMPACT,choice);
    }


    /**
     * Test if Brick is broken
     */
    @Test
    void isBroken() {
        assertFalse(bricktesting.isBroken());
    }

    /**
     * Test if brick is repaired
     */
    @Test
    void repair() {
        bricktesting.repair();
        assertEquals(bricktesting.getStrength(), bricktesting.getFullStrength());
        assertFalse(bricktesting.isBroken());
    }

    /**
     * Test if brick is impact
     */
    @Test
    void impact() {
       bricktesting.setStrength(3);
       bricktesting.impact();
       assertEquals(2,bricktesting.getStrength());
       assertTrue(!bricktesting.isBroken());
    }

    /**
     * Get initial strength of brick
     */
    @Test
    void getFullStrength() {
        assertEquals(1,bricktesting.getFullStrength());
    }
}