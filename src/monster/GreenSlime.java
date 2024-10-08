package monster;

import entity.Entity;
import main.GamePanel;
import object.BronzeCoin;
import object.Heart;
import object.ManaCrystal;
import object.Rock;

import java.util.Random;

public class GreenSlime extends Entity {

    GamePanel gp;

    public GreenSlime(GamePanel gp) {

        super(gp);

        this.gp = gp;

        type = monsterType;
        name = "Green Slime";
        speed = 1;
        maxLife = 6;
        life = maxLife;
        attack = 5;
        defence = 0;
        exp = 2;
        projectile = new Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick a number from 1 to 100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100)+1;
        if(i > 99 && !projectile.alive && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {

        int i = new Random().nextInt(100) + 1;

        if(i < 50) {
            dropItem(new BronzeCoin(gp));
        }
        if(i >= 50 && i < 75) {
            dropItem(new Heart(gp));
        }
        if(i >= 75 && i < 100) {
            dropItem(new ManaCrystal(gp));
        }
    }
}
