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


public class PlayerController {


    public Rectangle playerFace;
    public int width;
    public boolean impact;

    private Player PlayerModel;



    public PlayerController(Point ballPoint, int width, int height, Rectangle container) {
        PlayerModel = new Player(ballPoint,width, height,container);
        playerFace = makeRectangle(width, height);

    }

    public Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (PlayerModel.getBallPoint().getX() - (width / 2)), (int) PlayerModel.getBallPoint().getY());
        return new Rectangle(p, new Dimension(width, height));
    }
    public boolean impact(BallController b) {
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown());
    }

    public boolean impactPower(Powerup powerup) {
        return powerup != null && playerFace.contains(powerup.getX(), powerup.getY());
    }

    public void move() {
        double x = PlayerModel.getBallPoint().getX() + PlayerModel.getMoveAmount();
        if (x < PlayerModel.getMin() || x > PlayerModel.getMax())
            return;
        PlayerModel.getBallPoint().setLocation(x, PlayerModel.getBallPoint().getY());
        playerFace.setLocation(PlayerModel.getBallPoint().x - (int) playerFace.getWidth() / 2, PlayerModel.getBallPoint().y);
    }

    public void moveLeft() {
        setMoveAmount(-PlayerModel.getDefMoveAmount());
    }

    public void moveRight() {
        setMoveAmount(PlayerModel.getDefMoveAmount());
    }

    public void expand() {
        setWidthPlayerFace(playerFace.width);
        int width = getWidthPlayerFace() + 15;
        setWidthPlayerFace(width);
    }

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

    public int getWidthPlayerFace() {
        return width;
    }

    public void setWidthPlayerFace(int width) {
        this.width = width;
    }

    public Shape getPlayerFace() {
        return playerFace;
    }

    public void setPlayerFace(Rectangle playerFace) {
        this.playerFace = playerFace;
    }

    public void stop(){
        PlayerModel.stop();
    }
}

