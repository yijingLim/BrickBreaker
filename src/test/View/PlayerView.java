package test.View;

import test.Controller.PlayerController;

import java.awt.*;

public class PlayerView {
   // public String Start;

    public PlayerView() {
    }

    public void drawPlayer(PlayerController Player, Graphics2D g2d){

        Shape s = Player.getPlayerFace();
        g2d.setColor(Player.getInnerColor());
        g2d.fill(s);

        g2d.setColor(Player.getBorderColor());
        g2d.draw(s);

    }
}
