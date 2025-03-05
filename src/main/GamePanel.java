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

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PINK);
    }

    public void startGameThread(){
        gameThread = new Thread();
        gameThread.start();
    }

    @Override
    public void run() {
        //update
        //playeru
    }
}
