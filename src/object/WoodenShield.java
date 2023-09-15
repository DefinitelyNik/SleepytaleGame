package object;

import entity.Entity;
import main.GamePanel;

public class WoodenShield extends Entity {

    public WoodenShield(GamePanel gp) {
        super(gp);

        name = "Wooden Shield";
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenceValue = 1;
    }
}
