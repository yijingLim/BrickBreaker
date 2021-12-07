package test;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    public final Color inner = new Color(244, 244, 245);
    public final Color border = new Color(245, 245, 250);
    public Point2D location = new Point(10, 10);

    public Ball BallTesting;

    public BallTest(){
        BallTesting = new Ball(location, 10, 10, inner, border) {
            @Override
            protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

                return null;
            }
        };
    }

    @Test
    void testSetSpeed() {
        BallTesting.setSpeed(1, 1);
        assertEquals(1, BallTesting.getSpeedX());
        assertEquals(1, BallTesting.getSpeedY());
    }

    @Test
    void testsetXSpeed() {
        BallTesting.setXSpeed(2);
        assertEquals(2, BallTesting.getSpeedX());
    }

    @Test
    void setYSpeed() {
        BallTesting.setYSpeed(2);
        assertEquals(2, BallTesting.getSpeedY());
    }
    @Test
    void getPosition() {
        Point2D expectedlocation =  new Point(10,10);
        assertEquals(expectedlocation, BallTesting.getPosition());
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
    void setSpeed() {
        BallTesting.setSpeed(1,1);
        assertEquals(1, BallTesting.getSpeedX());
        assertEquals(1, BallTesting.getSpeedY());
    }

//    @Test
//    void move() {
////        BallTesting.setSpeed(5, 5);
////        BallTesting.move();
////        assertEquals(new Point(5,5), BallTesting.getPo());
//    }
//    @Test
//    void moveTo() {
//        Point P = new Point(30,20);
//        BallTesting.moveTo(P);
//        assertEquals(new Point(30,20), BallTesting.moveTo(P));
//
//    }


    @Test
    void getBallFace() {
        Shape expectedBallFace = BallTesting.makeBall(location,10,10);
        assertEquals(expectedBallFace, BallTesting.getBallFace());
    }
}