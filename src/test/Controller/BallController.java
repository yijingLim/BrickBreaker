package test.Controller;

import test.Model.Ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * @author limyijing
 * Ball Controller
 */
abstract public class BallController {

    public Shape ballFace;

    public Ball BallModel;

    /**
     * @param center the centre of the ball
     * @param radius the radius of the ball
     * @param inner the internal fill color of the ball
     * @param border the color of the border of the ball
     */
    public BallController(Point2D center,int radius,Color inner,Color border){
        BallModel = new Ball(center, radius,inner,border);
        ballFace = makeBall(center,radius);
    }

    /**
     * @param center
     * @param radius
     * @return a make ball
     */
    public abstract Shape makeBall(Point2D center,int radius);

    /**
     * Set the ball location and move the ball in the game
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        BallModel.getCenter().setLocation((BallModel.getCenter().getX() + BallModel.getSpeedX()),(BallModel.getCenter().getY() + BallModel.getSpeedY()));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.getCenter().getX() -(w / 2)),(BallModel.getCenter().getY() - (h / 2)),w,h);
        setPoints(w,h);

        ballFace = tmp;
    }
    /**
     * Reverse the speed X of ball when the ball imapcted wall, brick or borders
     */
    public void reverseX(){
        int reverse = BallModel.getSpeedX()* (-1);
        BallModel.setXSpeed(reverse);
    }

    /**
     * Reverse the speed of Y of ball when the ball imapcted wall, brick or borders
     */
    public void reverseY(){
        BallModel.setYSpeed(BallModel.getSpeedY()*(-1));
    }

    /**
     * @param p point with coordinate x and y to move towards
     * Moveto function help ball to move to a certain set location
     */
    public void moveTo(Point p){
        BallModel.getCenter().setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.getCenter().getX() -(w / 2)),(BallModel.getCenter().getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * To set the direction of the ball
     * @param width the width of the game frame
     * @param height the height of game frame
     */
    private void setPoints(double width,double height){
        BallModel.getUp().setLocation(BallModel.getCenter().getX(),BallModel.getCenter().getY()-(height / 2));
        BallModel.getDown().setLocation(BallModel.getCenter().getX(),BallModel.getCenter().getY()+(height / 2));

        BallModel.getLeft().setLocation(BallModel.getCenter().getX()-(width / 2),BallModel.getCenter().getY());
        BallModel.getRight().setLocation(BallModel.getCenter().getX()+(width / 2),BallModel.getCenter().getY());
    }

    /**
     * @return get the ball face
     */
    public Shape getBallFace() {
        return ballFace;
    }

    public void setXSpeed(int s){
        BallModel.setXSpeed(s);
    }

    public void setYSpeed(int s){
        BallModel.setYSpeed(s);
    }
    public int getSpeedX(){
        return BallModel.getSpeedX();
    }

    public int getSpeedY(){
        return BallModel.getSpeedY();
    }

    public Point2D getUp() {
        return BallModel.getUp();
    }

    public Point2D getDown() {
        return BallModel.getDown();
    }

    public Point2D getLeft() {
        return BallModel.getLeft();
    }

    public Point2D getRight() {
        return BallModel.getRight();
    }

    public Point2D getPosition(){
        return BallModel.getPosition();
    }

    public Color getBorderColor(){
        return BallModel.getBorderColor();
    }

    public Color getInnerColor(){
        return BallModel.getInnerColor();
    }

}
