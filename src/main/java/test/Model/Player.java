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
package test.Model;

import java.awt.*;


public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = new Color(3, 45, 246);

    private static final int DEF_MOVE_AMOUNT = 5;

    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * Constructor of player
     *  @param ballPoint Coordinate of Ball point x and y
     * @param width width of player
     * @param height height of player
     * @param container size of player
     */
    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    /**
     * When stop function is call, the move amount is set to 0
     */
    public void stop() {
        moveAmount = 0;
    }

    /**
     * @return get the definite move amount of player
     */
    public static int getDefMoveAmount() {
    return DEF_MOVE_AMOUNT;
}

    /**
     * @param moveAmount the amount of move required
     */
    public void setMoveAmount(int moveAmount) {
        this.moveAmount = moveAmount;
    }

    /**
     * @return the move amount
     */
    public int getMoveAmount() {
        return moveAmount;
    }


    /**
     * @return the position of ball point
     */
    public Point getBallPoint() {
        return ballPoint;
    }

    /**
     * @return the minimum move area amount
     */
    public int getMin() {
        return min;
    }

    /**
     * @return maximum move area amount
     */
    public int getMax() {
        return max;
    }

    /**
     * @return border color for the player face
     */
    public static Color getBorderColor() {
        return BORDER_COLOR;
    }

    /**
     * @return inner color filled for player face
     */
    public static Color getInnerColor() {
        return INNER_COLOR;
    }
}



