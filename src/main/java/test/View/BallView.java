package test.View;

import test.Controller.BallController;

import java.awt.*;

public class BallView {

    public BallView() {
    }

    /**
     * Draw the ball in the game
     * @param ball The content of ball such as inner and border colors, radius
     * @param g2d
     */
    public void drawBall(BallController ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }
}
