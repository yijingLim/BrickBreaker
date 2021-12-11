package test.Model;

import test.Controller.BallController;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class ExtraBall extends BallController {


    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(244, 88, 255);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();


    /**
     * ExtraBall class is a new ball used in game
     */
    public ExtraBall(Point2D center){
        super(center,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }




    @Override
    public Shape makeBall(Point2D center, int radius) {

        double x = center.getX() - (radius / 2);
        double y = center.getY() - (radius / 2);

        return new Ellipse2D.Double(x,y,radius,radius);
    }
}
