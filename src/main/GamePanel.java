package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; // the size of our player or basically the size of a tile (16 x 16)
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //actual tile size that we see on our screen

    final int tileCol = 16; // how many tiles on a column
    final int tileRow = 12; // how many tiles on a row

    // how many pixels will be our screen size
    final int screenWidth = tileSize * tileCol;
    final int screenHeight = tileSize * tileRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //FPS
    int FPS = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.addKeyListener(keyH); // recognize the input
        this.setFocusable(true); // the game focuses on the input key
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//
//        double drawInterval = (double) 1000000000 /FPS; // 0.016 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null){
//
//            //update (such as character position)
//            update();
//
//            //draw with the updated information
//            repaint();
//
//            //fps handling
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000; // to assure that remainingTime is in milliseconds
//
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public void run(){

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        //long timer = 0;
        //int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; // how much time has passed / drawInterval
           // timer += currentTime - lastTime;
            lastTime = currentTime; // updating the last time

            if(delta >= 1){
                update();
                repaint();
                --delta;
                // drawCount++;
            }

            // verifying the FPS
//            if(timer >= 1000000000){
//                System.out.println("FPS : " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }
    public void update(){

        // updating the player coordinates based on input

        if(keyH.upPressed){
            playerY -= playerSpeed; //the players walk with 4 pixels per second
        }
        else if(keyH.downPressed){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX += playerSpeed;
        }
    }

    //standard name to draw character in Java
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.black);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); //to save memory

    }

}
