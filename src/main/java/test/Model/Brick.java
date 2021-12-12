package test.Model;

import java.awt.*;
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

    public static Random rnd;

    private String name;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Constructor of brick class
     * @param name the name of the brick
     * @param pos the coordinate x and y of brick
     * @param size size of the brick
     * @param border the border color of the brick
     * @param inner the inner color of the brick
     * @param strength Strength of bricj
     */
    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {

        broken = false;
        this.name = name;
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    public Color getBorderColor() {
        return border;
    }

    public Color getInnerColor() {
        return inner;
    }

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

    public int getDefCrackDepth() {
        return DEF_CRACK_DEPTH;
    }

    public int getDefSteps() {
        return DEF_STEPS;
    }
}







