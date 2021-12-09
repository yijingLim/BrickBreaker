package test;

import java.awt.*;

public class PlayerView {
   // public String Start;

    public PlayerView() {

    }

    void drawPlayer(PlayerController p, Graphics2D g2d){

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

    }
}
