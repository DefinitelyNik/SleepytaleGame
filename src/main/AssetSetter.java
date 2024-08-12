package main;

import entity.OldMan;
import monster.GreenSlime;
import object.*;
import tile_interactive.DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        int i = 0;
        gp.obj[i] = new BronzeCoin(gp);
        gp.obj[i].worldX = gp.tileSize*25;
        gp.obj[i].worldY = gp.tileSize*23;
        i++;

        gp.obj[i] = new BronzeCoin(gp);
        gp.obj[i].worldX = gp.tileSize*21;
        gp.obj[i].worldY = gp.tileSize*19;
        i++;

        gp.obj[i] = new Key(gp);
        gp.obj[i].worldX = gp.tileSize*26;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;

        gp.obj[i] = new Axe(gp);
        gp.obj[i].worldX = gp.tileSize*33;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;

        gp.obj[i] = new BlueShield(gp);
        gp.obj[i].worldX = gp.tileSize*35;
        gp.obj[i].worldY = gp.tileSize*21;
        i++;

        gp.obj[i] = new RedPotion(gp);
        gp.obj[i].worldX = gp.tileSize*22;
        gp.obj[i].worldY = gp.tileSize*27;
        i++;

        gp.obj[i] = new Heart(gp);
        gp.obj[i].worldX = gp.tileSize*22;
        gp.obj[i].worldY = gp.tileSize*29;
        i++;

        gp.obj[i] = new ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize*22;
        gp.obj[i].worldY = gp.tileSize*31;
        i++;
    }

    public void setNPC() {

        gp.npc[0] = new OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }

    public void setMonster() {

        int i = 0;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*36;
        i++;

        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*37;
        i++;

        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*24;
        gp.monster[i].worldY = gp.tileSize*37;
        i++;

        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*34;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;

        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*38;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
    }

    public void setInteractiveTile() {
        int i = 0;
        gp.iTile[i] = new DryTree(gp, 27, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 28, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 29, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 30, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 31, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 32, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 33, 12);
        i++;

        gp.iTile[i] = new DryTree(gp, 20, 20);
        i++;
        gp.iTile[i] = new DryTree(gp, 20, 21);
        i++;
        gp.iTile[i] = new DryTree(gp, 20, 22);
        i++;
        gp.iTile[i] = new DryTree(gp, 22, 24);
        i++;
        gp.iTile[i] = new DryTree(gp, 23, 24);
        i++;
        gp.iTile[i] = new DryTree(gp, 24, 24);
        i++;
    }
}
