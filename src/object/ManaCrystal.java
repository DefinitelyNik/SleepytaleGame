package object;

import entity.Entity;
import main.GamePanel;

public class ManaCrystal extends Entity {

    GamePanel gp;

    public ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = pickupOnlyType;
        name = "Mama Crystal";
        value = 1;
        down1 = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {

        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
