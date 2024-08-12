package object;

import entity.Entity;
import main.GamePanel;

public class BronzeCoin extends Entity {

    GamePanel gp;

    public BronzeCoin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = pickupOnlyType;
        name = "Bronze coin";
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {

        gp.playSE(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.money += value;
    }
}
