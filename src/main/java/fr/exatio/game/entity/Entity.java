package fr.exatio.game.entity;

import fr.exatio.game.util.Drawable;

import java.awt.*;

public abstract class Entity implements Drawable {

    public int x, y, worldX, worldY;
    protected int speed;

    protected boolean collideable = false;
    protected Rectangle hitbox;

    protected int sprite_counter = 1;
    protected int image_counter_sprite = 0;
    protected EntityState.DIRECTION lastDirection;

    protected Entity() {
        initializeSprites();
    }

    public abstract void update();


}
