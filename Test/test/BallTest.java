package test;

import org.junit.jupiter.api.Test;
import test.Controller.BallController;
import test.Model.RubberBall;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    public final Color inner = new Color(244, 244, 245);
    public final Color border = new Color(245, 245, 250);
    public Point2D location = new Point(10, 10);

    public BallController BallTesting;

    public BallTest(){
        BallTesting = new BallController(location, 10, inner, border) {
            @Override
            public Shape makeBall(Point2D center, int radius) {

                return null;
            }
        };
    }
    @Test
    void reverseX() {
        BallTesting.setXSpeed(1);
        BallTesting.reverseX();

        assertEquals(-1, BallTesting.getSpeedX());
    }
    @Test
    void reverseY() {
        BallTesting.setYSpeed(1);
        BallTesting.reverseY();

        assertEquals(-1, BallTesting.getSpeedY());
    }

    @Test
    void move() {
        Point2D location = new Point(50,50);
        BallController b = new RubberBall(location);

        b.setSpeed(5, 5);
        b.move();
        assertEquals(new Point(55,55), b.getPosition()); // Position = point + speed
    }
    @Test
    void moveTo() {
        Point location = new Point(50,50);
        BallController b = new RubberBall(location);
        RectangularShape tmp = (RectangularShape) b.getBallFace();
        b.moveTo(new Point(100,100));
        assertEquals(95, tmp.getX()); //BallModel.getCenter().getX() -(w / 2) = 100 - (10/5) = 95

    }


    @Test
    void getBallFace() {
        Shape expectedBallFace = BallTesting.makeBall(location,10);
        assertEquals(expectedBallFace, BallTesting.getBallFace());
    }
}