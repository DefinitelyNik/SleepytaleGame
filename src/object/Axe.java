package object;

import entity.Entity;
import main.GamePanel;

public class Axe extends Entity {

    public Axe(GamePanel gp) {
        super(gp);

        type = axeType;
        name = "Axe";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 40;
        attackArea.height = 40;
        description = "[" + name + "]\nAn old axe. Maybe u can cut some\ntrees with it.";
    }
}
