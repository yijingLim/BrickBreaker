package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.security.cert.CertificateEncodingException;


public class CementBrick extends BrickController {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;


    /**
     * @param point Coordinate x amd y of brick
     * @param size Size of Cement Brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(getDefCrackDepth(),getDefSteps());
        brickFace = super.brickFace;
    }

    /**
     * @param pos Coordinate x amd y of brick
     * @param size Size of Cement Brick
     * @return the Cement Brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(brickFace,point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * @return the brick face of Cement brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * If the remaining brick strength is not 0, the crack is updated on the cement brick face
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * Repair the brick wall but resetting the crack of Cement brick face
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }

    /**
     * @return the initial strength of cement brick
     */
    public int getFullStrength(){
        return CEMENT_STRENGTH;
    }
}
