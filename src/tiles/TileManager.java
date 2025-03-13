package tiles;


import main.GamePanel;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp; // test
    public Tile[] tiles; // the surface
    public int [][]mapTileNumber;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tiles = new Tile[15];// types of tyles like wall, door, etc
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/level1_map.txt");
    }

    public void getTileImage() {

        try {

            tiles[0] = new Tile(); // e boring. nu cred ca o sa o folosim
            tiles[0].image = ImageIO.read( getClass().getResourceAsStream("/tile/tile2.png") ); // added new asset

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read( getClass().getResourceAsStream("/tile/tile3.png") );

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read( getClass().getResourceAsStream("/tile/tile4.png") );

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read( getClass().getResourceAsStream("/tile/tile5.png") );

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read( getClass().getResourceAsStream("/tile/tile6.png") );

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read( getClass().getResourceAsStream("/tile/tile7.png") );

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read( getClass().getResourceAsStream("/tile/middle_wall1.png") );
            tiles[6].collision = true;

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read( getClass().getResourceAsStream("/tile/middle_wall2.png") );
            tiles[7].collision = true;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read( getClass().getResourceAsStream("/tile/end_wall_right.png") );
            tiles[8].collision = true;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read( getClass().getResourceAsStream("/tile/end_wall_left.png") );
            tiles[9].collision = true;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read( getClass().getResourceAsStream("/tile/end_wall_both_sides.png") );
            tiles[10].collision = true;

         //  tiles[12] = new Tile();
            //  tiles[12].image = ImageIO.read( getClass().getResourceAsStream("/tile/test.png") );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) { // we read the matrix to stock the data of the tiles

        try {

            InputStream is = getClass().getResourceAsStream(filePath); // used is to import the file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // to read the content of the file

            int col = 0;
            int row = 0;

            while ( col < gp.maxWorldCol && row < gp.maxWorldRow ) {

                String line = br.readLine(); // we read the line ( row )
                while (col < gp.maxWorldCol ) {
                    String [] numbers = line.split(" "); // array of the row numbers

                    int num = Integer.parseInt(numbers[col]); // the array we are gonna use

                    mapTileNumber[col][row] = num;
                    ++col;
                }

                if ( col == gp.maxWorldCol ) {
                    col = 0;
                    ++row;
                }
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics2D g2) {

        /*
        // first row
        g2.drawImage(tiles[0].image, 0, 0, gp.tileSize, gp.tileSize, null); // adding tiles x is multiple 48 and y same
        g2.drawImage(tiles[1].image, 48, 0, gp.tileSize, gp.tileSize, null); // one tile is 48x48
        g2.drawImage(tiles[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[5].image, 144, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 192, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[1].image, 240, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[4].image, 288, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[0].image, 336, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[5].image, 384, 0, gp.tileSize, gp.tileSize, null);

        // second row
        g2.drawImage(tiles[4].image, 0, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 48, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[1].image, 96, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[3].image, 192, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[5].image, 240, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 288, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[3].image, 336, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[1].image, 384, 48, gp.tileSize, gp.tileSize, null);

        // third row
        g2.drawImage(tiles[0].image, 0, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[5].image, 48, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[3].image, 96, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[4].image, 144, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[1].image, 192, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 240, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[3].image, 288, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[0].image, 336, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[5].image, 384, 96, gp.tileSize, gp.tileSize, null);

        // fourth row
        g2.drawImage(tiles[0].image, 0, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[4].image, 48, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 96, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[5].image, 144, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[1].image, 192, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[3].image, 240, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[0].image, 288, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[4].image, 336, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 384, 144, gp.tileSize, gp.tileSize, null);

        // test to see if they overlap
        //  g2.drawImage(tiles[12].image, 48, 0, gp.tileSize, gp.tileSize, null);
        // they do

        munca de chinez.. nu facem asa
         */

        int worldCol = 0;
        int worldRow = 0;


        while ( worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow ) {

            int tileNum = mapTileNumber[worldCol][worldRow]; // the index

            int worldX = worldCol * gp.tileSize;  // pos on the map
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX; // the small part of the map around the moving player
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // where on the screen we put it

            g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            ++worldCol;

            if ( worldCol == gp.maxWorldCol ) {
                worldCol= 0;
                ++worldRow;

            }
        }

    }

}
