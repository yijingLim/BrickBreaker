/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;


public class TitanumBrick extends BrickController {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(239, 123, 7);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int TITANUM_STRENGTH = 3;
    private static final double TITANUM_PROBABILITY = 0.5;

    private Random rnd;
    private Shape brickFace;
    private Crack crack;

    /**
     * @param point Coordinate x and  y of brick
     * @param size size of the brick
     */
    public TitanumBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,TITANUM_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
        crack = new Crack(getDefCrackDepth(),getDefSteps());
    }


    /**
     * @param pos Coordinate x and  y of brick make
     * @param size size of the brick
     * @return a new titanum brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * when the wall impact the brick, if its not broken the impact
     * the brick will have crack until brick strength is used up
     * @return true when ready to impact
     */
    public  boolean setImpact(Point2D point , int dir){
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
     * With 0.5 or less of probability, the brick strength will -1 when impact
     */
    public void impact(){
        if(rnd.nextDouble() < TITANUM_PROBABILITY){
            super.impact();
        }
    }

    /**
     * If brick is not broke, strength is not 0, draw new crack on brick face
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * Repair brick by reseting the brickface to be crack free
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }

    /**
     * @return initial strength of the brick
     */
    public int getFullStrength(){
        return super.getFullStrength();
    }

}