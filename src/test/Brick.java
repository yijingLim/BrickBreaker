package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 *
 */
public class Brick {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    static Random rnd;

    private String name;
//    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {

        broken = false;
        this.name = name;
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

//    protected abstract Shape makeBrickFace(Point pos, Dimension size);

//    public boolean setImpact(Point2D point, int dir) {
//        if (broken)
//            return false;
//        impact();
//        return broken;
//    }

  //  public abstract Shape getBrick();


    public Color getBorderColor() {
        return border;
    }

    public Color getInnerColor() {
        return inner;
    }

//    public final ImpactedDirection findImpact(BallController b) {
//        if (broken)
//            return ImpactedDirection.NO_IMPACT;
//        ImpactedDirection out = ImpactedDirection.NO_IMPACT;
//        if (brickFace.contains(b.getRight()))
//            out = ImpactedDirection.LEFT_IMPACT;
//        else if (brickFace.contains(b.getLeft()))
//            out = ImpactedDirection.RIGHT_IMPACT;
//        else if (brickFace.contains(b.getUp()))
//            out = ImpactedDirection.DOWN_IMPACT;
//        else if (brickFace.contains(b.getDown()))
//            out = ImpactedDirection.UP_IMPACT;
//        return out;
//    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public final boolean isBroken() {
        return broken;
    }

    public int getFullStrength(){
        return fullStrength;
    }

    public void setStrength(int x){
        strength =x;
    }

    public int getStrength() {
        return strength;
    }

    public static int getUpImpact() {
        return UP_IMPACT;
    }

    public static int getDownImpact() {
        return DOWN_IMPACT;
    }

    public static int getLeftImpact() {
        return LEFT_IMPACT;
    }

    public static int getRightImpact() {
        return RIGHT_IMPACT;
    }

    public int getDefCrackDepth() {
        return DEF_CRACK_DEPTH;
    }

    public int getDefSteps() {
        return DEF_STEPS;
    }
}







