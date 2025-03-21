package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int counter2 = 0;

    // constructor
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;  // the center of the screen
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        // making the "body" of the player smaller for better collision handling
        solidArea = new Rectangle(8, 40, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public  void setDefaultValues(){
        worldX = gp.tileSize * 23; // players position in the world map
        worldY = gp.tileSize * 21; // where the player starts the game   gp.tileSize * coordonata( linis/coloana din matrice)
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png"));

        }catch(IOException e){
            System.err.println("ERROR: Image not found");
            e.printStackTrace();
        }
    }

    public void update(){

        // updating the player coordinates based on input

        if(keyH.upPressed){
            direction = "up";
        }
        else if(keyH.downPressed){
            direction = "down";
        }
        else if(keyH.leftPressed){
            direction = "left";
        }
        else if(keyH.rightPressed){
            direction = "right";
        }

        collisionOn = false;
        gp.cc.checkTile(this);

        if(!collisionOn){
            switch(direction){
                case "up":
                    worldY -= speed; //the players walk with 4 pixels per second
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        //spriteCounter handling????
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.black);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
