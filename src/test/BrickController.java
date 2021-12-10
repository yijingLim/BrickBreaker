package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract class BrickController{
    Shape brickFace;
    private Brick BrickModel;


    public BrickController(String name, Point pos, Dimension size, Color border, Color inner, int strength) {

        BrickModel = new Brick(name, pos,size,border,inner,strength);
        brickFace = makeBrickFace(pos, size);

    }

    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    public boolean setImpact(Point2D point, int dir) {
        if (isBroken())
            return false;
        impact();
        return isBroken();
    }

    public abstract Shape getBrick();

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

    public void repair() {
        setBroken(false);
        setStrength(getFullStrength());
    }

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







