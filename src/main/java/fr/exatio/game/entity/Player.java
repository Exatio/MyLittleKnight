package fr.exatio.game.entity;

import fr.exatio.game.GamePanel;
import fr.exatio.game.KeyboardHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private EntityState.DIRECTION direction;

    private BufferedImage[] walk_up;
    private BufferedImage[] walk_down;
    private BufferedImage[] walk_left;
    private BufferedImage[] walk_right;

    private KeyboardHandler kHandler;

    private boolean isOnSingleScreen;

    public Player(KeyboardHandler kHandler) {
        super();
        this.kHandler = kHandler;
        this.speed = 4;
        this.hitbox = new Rectangle(3, 9, 11, 13);
    }

    @Override
    public void initializeSprites() {

        walk_up = new BufferedImage[4];
        walk_down = new BufferedImage[4];
        walk_left = new BufferedImage[4];
        walk_right = new BufferedImage[4];

        try {
            for(int i = 1 ; i <= 4 ; i++) {
                walk_up[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("entity/player/walk_up_" + i + ".png")));
            }
            for(int i = 1 ; i <= 4 ; i++) {
                walk_down[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("entity/player/walk_down_" + i + ".png")));
            }
            for(int i = 1 ; i <= 4 ; i++) {
                walk_left[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("entity/player/walk_left_" + i + ".png")));
            }
            for(int i = 1 ; i <= 4 ; i++) {
                walk_right[i-1] = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("entity/player/walk_right_" + i + ".png")));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {

        if(kHandler.upPressed) {
            direction = EntityState.DIRECTION.UP;
             worldY -= speed;
        }

        if(kHandler.downPressed) {
            direction = EntityState.DIRECTION.DOWN;
            worldY += speed;
        }

        if(kHandler.rightPressed) {
            direction = EntityState.DIRECTION.RIGHT;
            worldX += speed;
        }

        if(kHandler.leftPressed) {
            direction = EntityState.DIRECTION.LEFT;
            worldX -= speed;
        }

        if(isOnSingleScreen) {
            x = worldX;
            y = worldY;
        }

        if(direction != lastDirection) {
            sprite_counter = 1;
            image_counter_sprite = 0;
        }

        lastDirection = direction;

        image_counter_sprite++;


        if(sprite_counter == 4 && image_counter_sprite > 5) {
            sprite_counter = 2;
            image_counter_sprite = 0;
        } else if(image_counter_sprite > 10) {
            sprite_counter++;
            image_counter_sprite = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        if(!this.kHandler.leftPressed && !this.kHandler.rightPressed && !this.kHandler.upPressed && !this.kHandler.downPressed) {
            sprite_counter = 1;
        }

        BufferedImage sprite =
                switch(direction) {
                    case UP -> walk_up[sprite_counter-1];
                    case DOWN -> walk_down[sprite_counter-1];
                    case LEFT -> walk_left[sprite_counter-1];
                    case RIGHT -> walk_right[sprite_counter-1];
                };

        g2.drawImage(sprite, x, y, GamePanel.TILE_SIZE, GamePanel.PLAYER_HEIGHT, null);

    }

    public void spawnAt(int row, int col, int direction) {
        this.worldX = (row-1) * GamePanel.TILE_SIZE;
        this.worldY = (col-1) * GamePanel.TILE_SIZE - 20;
        if(!isOnSingleScreen) {
            x = GamePanel.SCREEN_WIDTH/2-GamePanel.TILE_SIZE/2;
            y = GamePanel.SCREEN_HEIGHT/2-GamePanel.PLAYER_HEIGHT/2;
        }
        this.direction = EntityState.DIRECTION.values()[direction];
    }

    public void setOnSingleScreen(boolean onSingleScreen) {
        this.isOnSingleScreen = onSingleScreen;
    }

}
