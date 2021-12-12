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
package test.Controller;

import test.Model.Player;
import test.Model.Powerup;

import java.awt.*;


/**
 * Player Controller link with Player (Model) and PlayerView
 */
public class PlayerController {


    public Rectangle playerFace;
    public int width;
    private int height;
    public boolean impact;

    private Player PlayerModel;

    /**
     * @param ballPoint Coordinate x and y of ball
     * @param width the width of the player bar
     * @param height the height of the player bar
     * @param container the size of the player bar
     */
    public PlayerController(Point ballPoint, int width, int height, Rectangle container) {
        PlayerModel = new Player(ballPoint,width, height,container);
        playerFace = makeRectangle(width, height);
        this.height = height;
    }


    /**
     * @param width width of player face
     * @param height height of the playerface
     * @return new playerface of the player bar
     */
    public Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (PlayerModel.getBallPoint().getX() - (width / 2)), (int) PlayerModel.getBallPoint().getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    /**
     * check impact when player bar interesect with ball position
     * @param b ball that impact with player
     * @return true when ball hit the playerface
     */
    public boolean impact(BallController b) {
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown());
    }

    /**
     * @param powerup the powerup block
     * @return true if powerup is presence and intersect with the playerface
     */
    public boolean impactPower(Powerup powerup) {
        return powerup != null && playerFace.contains(powerup.getX(), powerup.getY());
    }

    /**
     * Move the player bar horizontally within the game frame
     */
    public void move() {
        double x = PlayerModel.getBallPoint().getX() + PlayerModel.getMoveAmount();
        if (x < PlayerModel.getMin() || x > PlayerModel.getMax())
            return;
        PlayerModel.getBallPoint().setLocation(x, PlayerModel.getBallPoint().getY());
        playerFace.setLocation(PlayerModel.getBallPoint().x - (int) playerFace.getWidth() / 2, PlayerModel.getBallPoint().y);
    }

    /**
     * Player move to the left and move amount is negative to the left
     */
    public void moveLeft() {
        setMoveAmount(-PlayerModel.getDefMoveAmount());
    }

    /**
     * Player move to the right and move amount is positive to the right
     */
    public void moveRight() {
        setMoveAmount(PlayerModel.getDefMoveAmount());
    }

    /**
     * Expand the width of playerface by adding a value of 15
     */
    public void expand() {
        setWidthPlayerFace(playerFace.width);
        int width = getWidthPlayerFace() + 15;
        setWidthPlayerFace(width);
        playerFace = makeRectangle(width, height);
    }

    /**
     * @param p New point assigned for the player to move towards
     * The player move to the specific location assigned
     */
    public void moveTo(Point p) {
        PlayerModel.getBallPoint().setLocation(p);
        playerFace.setLocation(PlayerModel.getBallPoint().x - (int) playerFace.getWidth() / 2, PlayerModel.getBallPoint().y);
    }
    public void setMoveAmount(int moveAmount) {
        PlayerModel.setMoveAmount(moveAmount);
    }

    public int getMoveAmount() {
        return PlayerModel.getMoveAmount();
    }

    /**
     * @return the width of the player face
     */
    public int getWidthPlayerFace() {
        return width;
    }

    /**
     * @param width the set the width of player face
     */
    public void setWidthPlayerFace(int width) {
        this.width = width;
    }

    public Shape getPlayerFace() {
        return playerFace;
    }

    public void stop(){
        PlayerModel.stop();
    }

    public Color getBorderColor() {
        return PlayerModel.getBorderColor();
    }

    public Color getInnerColor() {
        return PlayerModel.getInnerColor();
    }
}

