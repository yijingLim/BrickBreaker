package test.Model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Ball Model Class
 */
public class Ball {


    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;


    /**
     * @param center Center of the ball
     * @param radius Radius of the ball
     * @param inner the inner fill of the ball
     * @param border the color of border of the ball
     */
    public Ball(Point2D center, int radius, Color inner, Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radius / 2));
        down.setLocation(center.getX(),center.getY()+(radius / 2));

        left.setLocation(center.getX()-(radius /2),center.getY());
        right.setLocation(center.getX()+(radius /2),center.getY());

        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * @param s coordinate x of the ball
     * Set the horizontal speed of ball using x coordinate
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * @param s coordinate y of the ball
     * Set the vertical speed of ball using x coordinate
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public Point2D getCenter() {
        return center;
    }

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }


    public Point2D getRight() {
        return right;
    }

}
