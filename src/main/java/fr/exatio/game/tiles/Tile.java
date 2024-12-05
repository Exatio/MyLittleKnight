package fr.exatio.game.tiles;

import fr.exatio.game.GamePanel;
import fr.exatio.game.util.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile implements Drawable {

    private int row, col, positionX, positionY;
    private TileType type;
    private BufferedImage sprite;


    public Tile(TileType type, int row, int col) {
        this.col = col;
        this.row = row;
        this.positionX = col * GamePanel.TILE_SIZE;
        this.positionY = row * GamePanel.TILE_SIZE;
        this.type = type;
        initializeSprites();
    }

    @Override
    public void initializeSprites() {
        try {
            this.sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tile/" + type.name + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(this.sprite, positionX, positionY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

}


