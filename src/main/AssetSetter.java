package main;

import entity.OldMan;
import monster.GreenSlime;
import object.Axe;
import object.BlueShield;
import object.Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        int i = 0;
        gp.obj[i] = new Key(gp);
        gp.obj[i].worldX = gp.tileSize*25;
        gp.obj[i].worldY = gp.tileSize*23;
        i++;

        gp.obj[i] = new Key(gp);
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
}
