package com.captainhook.findingtreasure;

import entity.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import level.LevelManager;
import usage.Constant;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Game implements Runnable {
    
    private GameWindow gameWindow;
    private Panel panel;
    private LevelManager levelManager;
    private Thread gameThread;
    private Thread gameThread1;
    private final int FPS_SET = 120;
    

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.2f;
    public final static int TILES_IN_WITDH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WITDH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
//    
//    private int xOffset;
//    private int leftBorder = (int) 0.2 * GAME_WIDTH;
//    private int rightBorder = (int) 0.2 * GAME_WIDTH;
//    private int tilesWide = LevelManager.getLevelData()[0].length;
//    private int maxTilesOffset = tilesWide - TILES_IN_WITDH;
//    private int maxOffset = maxTilesOffset * Game.TILES_SIZE;
    
    public Game() {
        panel = new Panel(this);
        gameWindow = new GameWindow(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        startGameLoop();   
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread1 = new Thread(this);
        gameThread.start();
        gameThread1.start();
    }

    public void render(Player player, Graphics g) {
        player.update(g);
        player.render(g);
    }

    public void update(Player player, Graphics g) {
        player.update(g);
        player.render(g);

    }

    @Override
    public void run() {

        // Duration each frame last in nanoseconds
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                panel.repaint();
                lastFrame = now;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

}
