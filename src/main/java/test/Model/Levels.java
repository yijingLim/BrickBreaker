package test.Model;

import test.Controller.BrickController;

import java.awt.*;

public class Levels {

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int TITANUM = 4;

    /**
     * @param drawArea the size of brick level
     * @param brickCnt Total number of bricks
     * @param lineCnt the number of line
     * @param brickSizeRatio the size of the brick in ratio
     * @param type the type of brick used
     * @return the display of a single type brick on wall=
     */
    public static BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type) {
        /**
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp = new BrickController[brickCnt];

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

    /**
     * @param drawArea the size of brick level
     * @param brickCnt Total number of bricks
     * @param lineCnt the number of line
     * @param brickSizeRatio the size of the brick in ratio
     * @param typeA First type of brick type
     * @param typeB Second type of brick type
     * @return the display of brick on wall with typeA and typeB brick type
     */
    public static BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        /**
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

        BrickController[] tmp = new BrickController[brickCnt];

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
     * @param point Coordinate x and y of brick
     * @param size Size of brick
     * @param type the type of brick selected
     * @return create the respective brick type when the type is choosen
     */
    private static BrickController makeBrick(Point point, Dimension size, int type) {
        BrickFactory brick = new BrickFactory();
        BrickController out;
        switch (type) {
            case CLAY:
                out = brick.getBrickType("CLAY", point, size);
            break;
            case STEEL:
                out = brick.getBrickType("STEEL", point, size);
                break;
            case CEMENT:
                out = brick.getBrickType("CEMENT", point, size);
                break;

            case TITANUM:
                out = brick.getBrickType("TITANUM", point, size);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        }
        return out;
    }


}
