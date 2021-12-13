package test.Controller;

import test.Model.ImpactedDirection;
import test.Model.Brick;

import java.awt.*;
import java.awt.geom.Point2D;


abstract public class BrickController{
    public Shape brickFace;
    public Brick BrickModel;


    /**
     * Constructor of the brick
     * @param name Name of the brick
     * @param pos Position of brick in coordinate x and y
     * @param size size of brick
     * @param border the border color of brick
     * @param inner the inner fill of brick
     * @param strength the strength of the brick
     */
    public BrickController(String name, Point pos, Dimension size, Color border, Color inner, int strength) {

        BrickModel = new Brick(name, pos,size,border,inner,strength);
        brickFace = makeBrickFace(pos, size);

    }

    /**
     * Method to make the brick surface
     * @param pos Position of brick
     * @param size Size of the brick
     */
    public abstract Shape makeBrickFace(Point pos, Dimension size);

    public boolean setImpact(Point2D point, int dir) {
        if (isBroken())
            return false;
        impact();
        return isBroken();
    }

    public abstract Shape getBrick();

    /**
     * @param b Ball controller
     * @return impacted direction by the ball on brick
     */
    public final ImpactedDirection findImpact(BallController b) {
        if (BrickModel.isBroken())
            return ImpactedDirection.NO_IMPACT;
        ImpactedDirection out = ImpactedDirection.NO_IMPACT;
        if (brickFace.contains(b.getRight()))
            out = ImpactedDirection.LEFT_IMPACT;
        else if (brickFace.contains(b.getLeft()))
            out = ImpactedDirection.RIGHT_IMPACT;
        else if (brickFace.contains(b.getUp()))
            out = ImpactedDirection.DOWN_IMPACT;
        else if (brickFace.contains(b.getDown()))
            out = ImpactedDirection.UP_IMPACT;
        return out;
    }

    /**
     * Method to repair the brick by reseting the brick strength
     */
    public void repair() {
        setBroken(false);
        setStrength(getFullStrength());
    }

    /**
     * Method when brick is impacted
     */
    public void impact() {
        setStrength(getStrength()-1);
        setBroken((getStrength() == 0));
    }

    public void setBroken(boolean broken) {
        BrickModel.setBroken(broken);
    }

    public final boolean isBroken() {
        return BrickModel.isBroken();
    }

    public int getFullStrength(){
        return BrickModel.getFullStrength();
    }

    public void setStrength(int x){
        BrickModel.setStrength(x);
    }

    public int getStrength() {
        return BrickModel.getStrength();
    }

    public int getDefCrackDepth() {
        return BrickModel.getDefCrackDepth();
    }

    public int getDefSteps() {
        return BrickModel.getDefSteps();
    }

    public Color getBorderColor() {
        return BrickModel.getBorderColor();
    }

    public Color getInnerColor() {
        return BrickModel.getInnerColor();
    }

}







