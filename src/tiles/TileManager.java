package tiles;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tiles; // the surface

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tiles = new Tile[10]; // types of tyles like wall, door, etc
    }
}
