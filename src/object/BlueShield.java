package object;

import entity.Entity;
import main.GamePanel;

public class BlueShield extends Entity {

    public BlueShield(GamePanel gp) {
        super(gp);

        type = shieldType;
        name = "Blue shield";
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenceValue = 2;
        description = "[" + name + "]\nJust a shield but blue!!!.";
    }
}
