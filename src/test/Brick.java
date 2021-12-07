package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Brick {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    public enum ImpactedDirection{
        UP_IMPACT, DOWN_IMPACT, LEFT_IMPACT, RIGHT_IMPACT, NO_IMPACT
    }


    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {

        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos, size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    public boolean setImpact(Point2D point, int dir) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    public abstract Shape getBrick();


    public Color getBorderColor() {
        return border;
    }

    public Color getInnerColor() {
        return inner;
    }



    public final ImpactedDirection findImpact(Ball b) {
        if (broken)
            return ImpactedDirection.NO_IMPACT;
        ImpactedDirection out = ImpactedDirection.NO_IMPACT;
        if (brickFace.contains(b.right))
            out = ImpactedDirection.LEFT_IMPACT;
        else if (brickFace.contains(b.left))
            out = ImpactedDirection.RIGHT_IMPACT;
        else if (brickFace.contains(b.up))
            out = ImpactedDirection.DOWN_IMPACT;
        else if (brickFace.contains(b.down))
            out = ImpactedDirection.UP_IMPACT;
        return out;
    }

    public final boolean isBroken() {
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact() {
        strength--;
        broken = (strength == 0);
    }
    public int getFullStrength(){
        return fullStrength;
    }

    public void setStength(int x){
        strength =x;
    }

    public int getStrength() {
        return strength;
    }

}







