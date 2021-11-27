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
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {

    private static final int LEVELS_COUNT = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;


    private Random rnd;
    private Rectangle area;

    Brick[] bricks;
    Ball ball;
    Ball extraball;
    Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;
    private boolean wallimpact;


    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        level = 0;


        ballCount = 3;
        ballLost = false;
        wallimpact = false;
        rnd = new Random();

        makeBall(ballPos);
        int speedX, speedY;
        do {
            speedX = rnd.nextInt(5) - 2;//rnd.nextInt(5) , -2
        } while (speedX == 0);
        do {
            speedY = -rnd.nextInt(3); //-rnd.nextInt(3)
        } while (speedY == 0);

        ball.setSpeed(speedX, speedY);

        //make extra ball
        makeExtraBall(new Point(330, 380));
                int speedX1, speedY1;
                do {
                    speedX1 = 2;
                } while (speedX1 == 0);
                do {
                    speedY1 = -3; //-rnd.nextInt(3)
                } while (speedY1 == 0);

                extraball.setSpeed(speedX1, speedY1);


        player = new Player((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;
    }


    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, type);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize);
        }
        return tmp;

    }

    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    /**
     * @param ballPos ball initial position
     * makeBall class to create a ball
     */
    private void makeBall(Point2D ballPos) {
        this.ball = new RubberBall(ballPos);
    }

    /**
     * Make Extra Ball used in Level 5
     */
    private void makeExtraBall(Point2D ballPos) {
        this.extraball = new ExtraBall(ballPos);
    }


    /**
     * Makelevels is to create different level with different brick arrangement
     * @param brickCount total number of brick in the level
     * @param lineCount the total line consist of brick

     */
    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        tmp[4] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);// level with 2 ball
        return tmp;
    }


    public void move() {
        player.move();
        ball.move();

        extraball.move();


    }

    /**
     * Find whether the ball impacted the wall, brick, border and assign direction after impacts
     */
    public void findImpacts() {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
            if (rnd.nextInt() < 0.3) { //probability of it to x2 the speed
                setBallYSpeed((int) (ball.getSpeedY() * 1.5));//increase speed
            }
            else {
                System.out.println(ball.getPosition());
                System.out.println(extraball.getPosition());
            }

        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < area.getY()) {
            ball.reverseY();
        }
    }


    /**
     * Same function with findImpacts
     * Find Impact for the extra ball created
     */
    public void findImpacts1() {
        if (player.impact(extraball)) {
            extraball.reverseY();
        } else if (impactWall1()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
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





    private boolean impactWall() {
        for (Brick b : bricks) {
            switch (b.findImpact(this.ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Crack.UP);//Brick.Crack.UP/down/left right
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up, Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right, Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left, Crack.LEFT);
                }
            }
            return false;
        }
        private boolean impactWall1(){
        for(Brick b : bricks){
            switch(b.findImpact(extraball) ) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    extraball.reverseY();
                    return b.setImpact(extraball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    extraball.reverseY();
                    return b.setImpact(extraball.up,Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    extraball.reverseX();
                    return b.setImpact(extraball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    extraball.reverseX();
                    return b.setImpact(extraball.left,Crack.LEFT);
            }
        }
        return false;
    }

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }
    private boolean impactBorder1(){
        Point2D x = extraball.getPosition();
        return ((x.getX() < area.getX()) ||(x.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }
    public boolean hasLevel(){
        return level < levels.length;
    }

    public int getLevelCount() {

            return level;//level count
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }

    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

}
