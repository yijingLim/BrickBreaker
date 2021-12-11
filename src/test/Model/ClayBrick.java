package test.Model;

import test.Controller.BrickController;

import java.awt.*;
import java.awt.Point;


/**
 * Created by filippo on 04/09/16.
 *
 */
public class ClayBrick extends BrickController {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(224, 10, 10).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * @param point Coordinate x and y of clay brick
     * @param size size of clay brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * @param pos Coordinate x and y of clay brick
     * @param size size of clay brick
     * @return the structure of clay brick
     */
    @Override
    public Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * @return the brick face of clay
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

    /**
     * @return the initial strength of clay brick
     */
    public int getFullStrength(){
        return CLAY_STRENGTH;
    }




}
