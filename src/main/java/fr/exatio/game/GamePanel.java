package fr.exatio.game;

import fr.exatio.game.entity.Player;
import fr.exatio.game.tiles.Tile;
import fr.exatio.game.tiles.TileManager;
import fr.exatio.game.tiles.TileType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int SCALE = 3;

    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int PLAYER_HEIGHT = 22 * SCALE;
    public static final int SCREEN_COLS = 16;
    public static final int SCREEN_ROWS = 12;

    public static final int SCREEN_WIDTH = SCREEN_COLS * TILE_SIZE;
    public static final int SCREEN_HEIGHT = SCREEN_ROWS * TILE_SIZE;

    private final double FPS = 60;
    private final double DRAW_INTERVAL = 1000000000 / FPS;

    private Thread gameThread;
    private KeyboardHandler kHandler = new KeyboardHandler();
    private Player player;
    private TileManager tileManager;
    private int currentFPS;
    public static boolean showFPS = false;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        addKeyListener(kHandler);
        setFocusable(true);
        player = new Player(kHandler);
        tileManager = new TileManager(this);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        int timer = 0;
        int drawCount = 0;


        while(gameThread.isAlive()) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime);
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta > DRAW_INTERVAL) {
                update();
                repaint();
                delta -= DRAW_INTERVAL;
                drawCount++;
            }

            if(timer >= 1000000000) {
                currentFPS = drawCount;
                timer = 0;
                drawCount = 0;
            }


        }

    }

    private void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.drawAllTiles(g2);
        player.draw(g2);

        if(showFPS) {
            g2.setColor(Color.red);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawString(currentFPS + " FPS", SCREEN_WIDTH - 70, 20);
        }


        g2.dispose();
    }

    public Player getPlayer() {
        return player;
    }
}
