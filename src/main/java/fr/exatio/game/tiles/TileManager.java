package fr.exatio.game.tiles;

import fr.exatio.game.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TileManager {

    GamePanel gp;
    int currentScreen;
    Map currentMap;
    List<Tile> tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        changeScreen(1);
    }

    public void drawAllTiles(Graphics2D g2) {

        for(Tile tile : tiles) {
            int tileXInScreen = tile.getCol() * GamePanel.TILE_SIZE - gp.getPlayer().worldX + gp.getPlayer().x;
            int tileYInScreen = tile.getRow() * GamePanel.TILE_SIZE - gp.getPlayer().worldY + gp.getPlayer().y;
            tile.setPosition(tileXInScreen, tileYInScreen);

            if(currentMap.isSingleScreen | (tileXInScreen > -GamePanel.TILE_SIZE && tileXInScreen - gp.getPlayer().x < gp.getPlayer().x + GamePanel.TILE_SIZE && tileYInScreen > -GamePanel.TILE_SIZE && tileYInScreen - gp.getPlayer().y < gp.getPlayer().y + GamePanel.PLAYER_HEIGHT)) {
                tile.draw(g2);
            }

        }
    }


    public void changeScreen(int screen) {
        currentMap = new Map(screen);
        changeScreen(screen, currentMap.defaultSpawnRow, currentMap.defaultSpawnCol, currentMap.defaultDirection, currentMap.isSingleScreen);
    }

    public void changeScreen(int screen, int rowSpawn, int colSpawn, int direction, boolean onSingleScreen) {
        gp.getPlayer().spawnAt(rowSpawn, colSpawn, direction);
        gp.getPlayer().setOnSingleScreen(onSingleScreen);
        tiles = new ArrayList<>();
        currentScreen = screen;
        for(int i = 0 ; i < GamePanel.SCREEN_ROWS ; i++) {
            for(int j = 0 ; j < GamePanel.SCREEN_COLS ; j++) {
                TileType type = TileType.values()[currentMap.map[i][j]];
                tiles.add(new Tile(type, i, j));
            }
        }
    }
}
