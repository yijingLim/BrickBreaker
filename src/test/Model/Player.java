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



    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    public void stop() {
        moveAmount = 0;
    }

    public static int getDefMoveAmount() {
    return DEF_MOVE_AMOUNT;
}

    public void setMoveAmount(int moveAmount) {
        this.moveAmount = moveAmount;
    }

    public int getMoveAmount() {
        return moveAmount;
    }


    public Point getBallPoint() {
        return ballPoint;
    }

    public void setBallPoint(Point ballPoint) {
        this.ballPoint = ballPoint;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public static Color getBorderColor() {
        return BORDER_COLOR;
    }

    public static Color getInnerColor() {
        return INNER_COLOR;
    }
}



