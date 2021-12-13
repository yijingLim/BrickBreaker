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

import test.Controller.BallController;
import test.Controller.BrickController;
import test.Controller.PlayerController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * Wall Class that consists of main interaction of the game
 */
public class Wall {

    private static final int LEVELS_COUNT = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int TITANUM = 4;


    private Random rnd;
    private Rectangle area;

    public BrickController[] bricks;
    public BallController ball;
    public ExtraBall extraball;
    public PlayerController player;


    private BrickController[][] levels;
    private int level;
    public int Score = 0 ;
    private int Strength;
    private int Bonus = 0;

    public Powerup[] powerups;
    int i;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private int type;
    private boolean ballLost;


    /**
     * Constructor of Wall class
     * @param drawArea Area of the wall
     * @param brickCount the number of brick in the wall
     * @param lineCount the number of line in the wall
     * @param brickDimensionRatio
     * @param ballPos Coordinate x and y
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        this.startPoint = new Point(ballPos);
        this.powerups = new Powerup[3];

        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;
        rnd = new Random();

        makeBall(ballPos);
        int speedX, speedY;
        do {
            speedX = 5;
        } while (speedX == 0);
        do {
            speedY = -5;
        } while (speedY == 0);

        ball.setXSpeed(speedX);
        ball.setYSpeed(speedY);

        player = new PlayerController((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;
    }

    /**
     * @param ballPos ball initial position
     * makeBall class to create a ball
     */

    private void makeBall(Point2D ballPos) {
        this.ball = new RubberBall(ballPos);
    }

    /**
     * Makelevels is to create different level with different brick arrangement
     *  @param brickCount total number of brick in the level
     * @param lineCount  the total line consist of brick
     * @return the level of brickcontroller
     */
    private BrickController[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        BrickController[][] tmp = new BrickController[LEVELS_COUNT][];
        tmp[0] = Levels.makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
        tmp[1] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        tmp[4] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, TITANUM, CEMENT);
        return tmp;
    }


    /**
     * Method that initiates the player,ball and extra ball to move when game start
     */
    public void move() {
        player.move();
        ball.move();

        if (extraball != null) {
            extraball.move();
        }
    }

    /**
     * Find whether the ball impacted the wall, brick, border and assign direction after impacts
     * When a certain type of brick is added, the score will be accumulated based on their strength
     */
    public void findImpacts() {

            if (player.impact(ball)) {
                ball.reverseY();
            } else if (impactWall()) {
                /**for efficiency reverse is done into method impactWall
                 * because for every brick program checks for horizontal and vertical impacts
                 */
                brickCount--;
                    switch (Strength) {
                        case 1:
                            Score += 10;
                            break;

                        case 2:
                            Score +=20;
                            break;

                        case 3:
                            Score +=30;
                            break;

                        case 4:
                            Score +=40;
                            break;
                    }

                    System.out.println("My Total Score :" + Score);

                /**
                 * There's 30% probabiliity for the speed to mulitply by 1.2 of its own speed
                 * The speed is limited to y=8
                 */
                if (rnd.nextInt() < 0.3) {
                    if (ball.getSpeedY() < 8) {
                        setBallYSpeed((int) (ball.getSpeedY() * 1.2));
                    } else {
                        setBallYSpeed(ball.getSpeedY());
                    }
                }
                /**
                 * There's 30% probabiliity for the ball to hit a powerup function
                 * There is more than 1 type of powerup function
                 */
                int type = rnd.nextInt(2);
                this.type = type;

                if (rnd.nextInt() < 0.3) {
                    Point2D Locate = ball.getPosition();
                    for (i = 0; i < this.powerups.length; ++i) {
                        if (this.powerups[i] == null) {
                            this.powerups[i] = new Powerup((int) Locate.getX(), (int) Locate.getY(), type);
                        }
                    }
                }


            } else if (impactBorder()) {
                ball.reverseX();
            } else if (ball.getPosition().getY() < area.getY()) {
                ball.reverseY();
            }else if(ball.getPosition().getY() >area.getY()-50+area.getHeight() && extraball == null) {
                ballCount--;
                ballLost = true;
            }
    }


    /**
     * When the powerup block collide with player
     * 2 Option of powerup is provided
     * Powerup type 0 : Extra ball function
     * Powerup type 1 : longer player rectangle
     */
    public void PowerDropDown() {
        for (i = 0; i < this.powerups.length; ++i) {
            if (this.powerups[i] != null) {
                this.powerups[i].dropdown();
                if(this.powerups[i].remove()){
                    this.powerups[i] = null;
                }

                if (player.impactPower(this.powerups[i])) {

                        if(this.type == 0){
                            this.extraball = new ExtraBall(new Point(330, 380));
                            int speedX1, speedY1;
                            do {
                                speedX1 = 2;
                            } while (speedX1 == 0);
                            do {
                                speedY1 = -3;
                            } while (speedY1 == 0);

                            extraball.setXSpeed(speedX1);
                            extraball.setYSpeed(speedY1);
                        }
                        else if(this.type == 1){
                            player.expand();
                        }

                this.powerups[i] = null;
                break;
                }
            }

        }
    }

    /**
     * Same function with findImpacts
     * Find Impact for the extra ball created
     */

    public void findImpacts1(){
            if (extraball != null) {
                    if (player.impact(extraball)) {
                        extraball.reverseY();
                    } else if (impactWall1()) {
                        /*for efficiency reverse is done into method impactWall
                         * because for every brick program checks for horizontal and vertical impacts
                         */
                        brickCount--;
                        switch (Strength) {
                            case 1:
                                Score += 10;
                                break;

                            case 2:
                                Score +=20;
                                break;

                            case 3:
                                Score +=30;
                                break;

                            case 4:
                                Score +=40;
                                break;
                        }


                        System.out.println("My Total Score :" + Score);

                        if (rnd.nextInt() < 0.3) { //probability of it to x2 the speed
                            setBallYSpeed((int) (extraball.getSpeedY() * 1.5));//increase speed
                        }

                    } else if (impactBorder1()) {
                        extraball.reverseX();
                    } else if (extraball.getPosition().getY() < area.getY()) {
                        extraball.reverseY();
                    } else if (ball.getPosition().getY() > area.getY() - 50 + area.getHeight() && extraball.getPosition().getY() > area.getY() - 50 + area.getHeight()) {
                        ballCount--;
                        ballLost = true;
                    }
                }
            }


    /**
     * Method to check if ball impact wall
     * @return true when it impact in all different direction
     */
    public boolean impactWall() {
        for (BrickController b : bricks) {
            switch (b.findImpact(this.ball)) {
                //Vertical Impact
                case UP_IMPACT:
                    ball.reverseY();
                    this.Strength = b.getFullStrength();
                    return b.setImpact(ball.getDown(), Crack.UP);
                case DOWN_IMPACT:
                    ball.reverseY();
                    this.Strength = b.getFullStrength();
                    return b.setImpact(ball.getUp(), Crack.DOWN);

                //Horizontal Impact
                case LEFT_IMPACT:
                    ball.reverseX();
                    this.Strength = b.getFullStrength();
                    return b.setImpact(ball.getRight(), Crack.RIGHT);
                case RIGHT_IMPACT:
                    ball.reverseX();
                    this.Strength = b.getFullStrength();
                    return b.setImpact(ball.getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * Check if extra ball impacted the wall
     * @return true when impacted the bricks in all direction
     */
    public boolean impactWall1() {
            if (this.extraball != null) {
                for (BrickController b : bricks) {
                    switch (b.findImpact(this.extraball)) {

                        //Vertical Impact
                        case UP_IMPACT:
                            this.extraball.reverseY();
                            return b.setImpact(this.extraball.getDown(), Crack.UP);
                        case DOWN_IMPACT:
                            extraball.reverseY();
                            return b.setImpact(this.extraball.getUp(), Crack.DOWN);

                        //Horizontal Impact
                        case LEFT_IMPACT:
                            this.extraball.reverseX();
                            return b.setImpact(this.extraball.getRight(), Crack.RIGHT);
                        case RIGHT_IMPACT:
                            this.extraball.reverseX();
                            return b.setImpact(this.extraball.getLeft(), Crack.LEFT);
                    }
                }
            }
        return false;
    }

    /**
     * Method to check impact border by normal ball
     * @return true when rubber ball impact the border
     */
    private boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Method to check impact border by extra ball
     * @return true when extra rubber ball impact the border
     */
    private boolean impactBorder1() {
            Point2D x = extraball.getPosition();
            return ((x.getX() < area.getX()) || (x.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Method to get the number of brick count
     * @return the number of brick
     */
    public int getBrickCount() {
        return brickCount;
    }

    /** Method to get the number of ball count
     * @return the number of ball
     */
    public int getBallCount() {
        return ballCount;
    }


    /**
     * Method is used to add bonus score to the total score
     * @return bonus score when game is over when ball count is not null
     */
    public int getBallExtraPoint(){
        if(getBallCount()==3){
            Bonus = 50;
        }
        else if(getBallCount()==2){
            Bonus = 40;
        }
        else if(getBallCount()==1){
            Bonus = 30;
        }
        return Bonus;
    }

    /**
     * Method to check if ball is lost
     * @return true when ball is lost
     */
    public boolean isBallLost() {
        return ballLost;
    }

    /**
     * Method to reset the ball speed to its initial ball speed
     */
    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX, speedY;
        do {
            speedX = 5;
        } while (speedX == 0);
        do {
            speedY = -5;
        } while (speedY == 0);
        ball.setXSpeed(speedX);
        ball.setYSpeed(speedY);

        ballLost = false;
    }

    /**
     * Method to reset the brick on the wall and the number of ball per game
     */
    public void wallReset() {
        for (BrickController b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * Method to reset the size of playerface
     * @return the initial player face width
     */
    public int resetPlayer(){
        return player.playerFace.width = 150;
    }

    /**
     * Method to check if ball ended
     * @return true when no ball is left
     */
    public boolean ballEnd() {
        return ballCount == 0;
    }

    /**
     * Method to check if level is done
     * @return true when all brick is impacted
     */
    public boolean isDone() {
        return brickCount == 0;
    }

    /**
     * Method to proceed to next level
     */
    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * Method to check if there is new level
     * @return true is there is more level
     */
    public boolean hasLevel() {
        return level < levels.length;
    }

    /**
     * @return Level of the game
     */
    public int getLevelCount() {
        return level;
    }

    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }

    public void resetBallCount() {
        ballCount = 3;
    }

    /**
     * draw special characteristic block representing powerup
     * @param g2d Graphics2D g2d
     */
    public void drawSpecialCharacteristic(Graphics2D g2d) {
        g2d.setColor(Color.orange);

        int i;
        for (i = 0; i < this.powerups.length; i++) {
            if (this.powerups[i] != null) {
                g2d.drawRoundRect(this.powerups[i].getX(), this.powerups[i].getY(), 20, 20, 5, 5);
            }
        }

    }
}



