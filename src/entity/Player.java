package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Fireball;
import object.Key;
import object.NormalSword;
import object.WoodenShield;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX, screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImages();
        setItems();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // Player status
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10; // ammo for testing other projectiles
        strength = 1; // the more strength player has, the more damage he deals;
        dexterity = 1; // the more dexterity player has, the less damage he receives;
        exp = 0;
        nextLevelExp = 5;
        money = 0;
        currentWeapon = new NormalSword(gp);
        currentShield = new WoodenShield(gp);
        projectile = new Fireball(gp);
        attack = getAttack(); // getting total attack value with strength and weapon attack values
        defence = getDefence(); // getting total defence value with dexterity and shield defence values
    }

    public void setItems() {

        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence() {
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getPlayerImage() {

        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImages() {

        if(currentWeapon.type == swordType) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
        }
        if(currentWeapon.type == axeType) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
        }

    }

    public void update() {

        if(attacking) {
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.ePressed) {
            if(keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Check NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check Monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Check Interactive tiles collision
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

            // Check event
            gp.eHandler.checkEvent();

            // If collision is false, player can move
            if(!collisionOn && !keyH.ePressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if(keyH.ePressed && !attackCanceled) {
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.ePressed = false;

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(gp.keyH.shotKeyPressed && !projectile.alive
                && shotAvailableCounter == 30 && projectile.haveResource(this)) {

            //Set default coordinates, direction and user
            projectile.set(worldX, worldY, direction, true, this);

            //Subtract the cost of using the projectile
            projectile.subtractResource(this);

            //Add it to the list
            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(10);
        }

        // This needs to be outside of key if statement
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        //Checking hp overflow
        if(life > maxLife) {
            life = maxLife;
        }

        //Checking mp overflow
        if(mana > maxMana) {
            mana = maxMana;
        }
    }

    public void attacking() {

        spriteCounter++;

        if(spriteCounter <= 5) {
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            // Restoring original data after checking collision
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {

        if(i != 999) {

            //Pickup only items
            if(gp.obj[i].type == pickupOnlyType) {
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }
            //Inventory items
            else {
                String text;

                if(inventory.size() != maxInventorySize) {

                    inventory.add(gp.obj[i]);
                    gp.playSE(1);
                    text = "Got a " + gp.obj[i].name + "!";
                    gp.obj[i] = null;
                }
                else {
                    text = "You cannot carry any more!";
                }
                gp.ui.addMessage(text);
            }
        }
    }

    public void interactNPC(int i) {

        if(keyH.ePressed) {
            if(i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {

        if(i != 999) {

            if(!invincible && !gp.monster[i].dying) {
                gp.playSE(6);

                int damage = gp.monster[i].attack - defence;
                if(damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {

        if(i != 999) {
            if(!gp.monster[i].invincible) {

                gp.playSE(5);

                int damage = attack - gp.monster[i].defence;
                if(damage < 0) {
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp + "!");
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int i) {

        if(i != 999 && gp.iTile[i].destructible && gp.iTile[i].isCorrectItem(this) && !gp.iTile[i].invincible) {

            //gp.iTile[i].playSE(); there are no sound rn
            gp.iTile[i].life--;
            gp.iTile[i].invincible = true;

            //Generate particle
            generateParticle(gp.iTile[i], gp.iTile[i]);

            if(gp.iTile[i].life <= 0) {
                gp.iTile[i] = gp.iTile[i].getDestroyedForm();
            }
        }
    }

    public void checkLevelUp() {

        if(exp >= nextLevelExp) {
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\nYou feel much stronger!";
        }
    }

    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == swordType || selectedItem.type == axeType) {

                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImages();
            }
            if(selectedItem.type == shieldType) {
                currentShield = selectedItem;
                defence = getDefence();
            }
            if(selectedItem.type == consumableType) {

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                if(attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) image = attackUp1;
                    if (spriteNum == 2) image = attackUp2;
                }
            }
            case "down" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                if(attacking) {
                    if (spriteNum == 1) image = attackDown1;
                    if (spriteNum == 2) image = attackDown2;
                }
            }
            case "left" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                if(attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) image = attackLeft1;
                    if (spriteNum == 2) image = attackLeft2;
                }
            }
            case "right" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                }
                if(attacking) {
                    if (spriteNum == 1) image = attackRight1;
                    if (spriteNum == 2) image = attackRight2;
                }
            }
        }

        // Set alpha to Graphics2D if player receives damage
        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
