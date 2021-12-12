package test.Model;

import test.GameBoard;

import java.awt.geom.RoundRectangle2D;

/**
 * Power Up class is created to add extra functionality to the gaem
 * There is a total of 2 power ups
 */
public class Powerup {
    private int x;
    private int y;
    private int width=20;
    private int height=20;
    private int arcw=10;
    private int arch=10;
    private int speed =2;
    public int typeofPower;
    public RoundRectangle2D Rectangle;
    public boolean remove = false;

    /**
     * @param x the horizontal location for Power Up Block
     * @param y The vertical location for Power Up Block
     * @param typeofPower the option for the type of power up
     */
    public Powerup(int x, int y, int typeofPower){
        this.x =x;
        this.y = y;
        this.Rectangle = new RoundRectangle2D.Double(this.x,this.y, width, height,arcw,arch);
        if(typeofPower == 0){
            System.out.println("multiple ball");
        }
        if(typeofPower == 1){
            System.out.println("Make paddle longer");
        }
        }


    /**
     * The function help to make the power up block drop down gradually at a constant speed
     * if the power up block is out of gameboard height then it is removed from the game
     */
    public void dropdown() {
        if (this.y < GameBoard.DEF_HEIGHT) {
            this.y += this.speed;
            this.Rectangle = new RoundRectangle2D.Double(this.x, this.y, this.width, this.height,arcw,arch);
        } else {
            this.remove = true;
        }
    }

    /**
     * @return the coordinate x of position of power up block
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the coordinate y of position of power up block
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return the width of the size of the power up block
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return false when block is removed
     */
    public boolean remove() {
        return remove;
    }

}

