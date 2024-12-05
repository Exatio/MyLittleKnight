package fr.exatio.game.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public interface Drawable {

    void draw(Graphics2D g2);
    void initializeSprites();

}
