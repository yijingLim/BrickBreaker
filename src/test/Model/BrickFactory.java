package test.Model;

import test.Controller.BrickController;

import java.awt.*;

public class BrickFactory {

    public BrickController getBrickType(String brickType, Point point, Dimension size){
        if (brickType == null)
            return null;
        if(brickType.equalsIgnoreCase("CLAY"))
            return new ClayBrick(point, size);
        else if(brickType.equalsIgnoreCase("CEMENT"))
            return new CementBrick(point, size);
        else if(brickType.equalsIgnoreCase("STEEL"))
            return new SteelBrick(point, size);
        else if(brickType.equalsIgnoreCase("TITANUM"))
            return new TitanumBrick(point, size);

        return null;
    }

}
