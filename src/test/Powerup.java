package test;

import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class Powerup {
    private int x;
    private int y;
    private int width=20;
    private int height=20;
    private int arcw=10;
    private int arch=10;
    private int speed =2;
    public RoundRectangle2D Rectangle;
    public boolean remove = false;

    /**
     * @param x the horizontal location for Power Up Block
     * @param y The vertical location for Power Up Block
     */
    public Powerup(int x, int y){
        this.x =x;
        this.y = y;
        this.Rectangle = new RoundRectangle2D.Double(this.x,this.y, width, height,arcw,arch);

    }

    public void dropdown() {
        if (this.y < GameBoard.DEF_HEIGHT) {
            this.y += this.speed;
            this.Rectangle = new RoundRectangle2D.Double(this.x, this.y, this.width, this.height,arcw,arch);
        } else {
            this.remove = true;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean remove() {
        return remove;
    }
}
