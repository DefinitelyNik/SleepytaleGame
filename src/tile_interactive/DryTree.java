package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class DryTree extends InteractiveTile{

    GamePanel gp;

    public DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;

        down1 = setup("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if(entity.currentWeapon.type == axeType) {
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(7);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {
        return new Color(65,50,30);
    }

    public int getParticleSize() {
        return 6;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
}
