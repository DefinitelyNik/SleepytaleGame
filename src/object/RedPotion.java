package object;

import entity.Entity;
import main.GamePanel;

public class RedPotion extends Entity {

    GamePanel gp;
    int value = 2; // healing value

    public RedPotion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = consumableType;
        name = "Red potion";
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nRestores " + value/2 + " hp.";
    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\nYou recovered " + value/2 + " hearts!";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
