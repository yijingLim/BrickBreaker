package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class BallController {

    private Shape ballFace;

    private Ball BallModel;

    public BallController(Point2D center,int radius,Color inner,Color border){
        BallModel = new Ball(center, radius,inner,border);
        ballFace = makeBall(center,radius);
    }

    protected abstract Shape makeBall(Point2D center,int radius);

    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        BallModel.getCenter().setLocation((BallModel.getCenter().getX() + BallModel.getSpeedX()),(BallModel.getCenter().getY() + BallModel.getSpeedY()));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.getCenter().getX() -(w / 2)),(BallModel.getCenter().getY() - (h / 2)),w,h);
        setPoints(w,h);

        ballFace = tmp;
    }

    public void reverseX(){
        //speedX *= -1;
        int reverse = BallModel.getSpeedX()* (-1);
        BallModel.setXSpeed(reverse);
    }

    public void reverseY(){
//        speedY *= -1;
        BallModel.setYSpeed(BallModel.getSpeedY()*(-1));
    }

    public void moveTo(Point p){
        BallModel.getCenter().setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.getCenter().getX() -(w / 2)),(BallModel.getCenter().getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        BallModel.getUp().setLocation(BallModel.getCenter().getX(),BallModel.getCenter().getY()-(height / 2));
        BallModel.getDown().setLocation(BallModel.getCenter().getX(),BallModel.getCenter().getY()+(height / 2));

        BallModel.getLeft().setLocation(BallModel.getCenter().getX()-(width / 2),BallModel.getCenter().getY());
        BallModel.getRight().setLocation(BallModel.getCenter().getX()+(width / 2),BallModel.getCenter().getY());
    }

    public Shape getBallFace() {
        return ballFace;
    }
    public void setSpeed(int x,int y){
        BallModel.setSpeed(x,y);

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
