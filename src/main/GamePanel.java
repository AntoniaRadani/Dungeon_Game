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

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
        this.addKeyListener(keyH); // recognize the input
        this.setFocusable(true); // the game focuses on the input key
    }

    public void startGameThread(){
        gameThread = new Thread();
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){

            //update (such as character position)
            update();

            //draw with the updated informatipn
            repaint();
        }
    }

    public void update(){

    }

    //standard name to draw character in Java
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.black);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose(); //to save memory

    }

}
