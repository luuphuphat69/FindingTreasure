package com.captainhook.findingtreasure;

import entity.Player; 

import java.awt.Graphics;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import level.LevelManager;
import usage.Constant;
import usage.LoadSave;

/**
 *
 * @author luuph
 */
public class Game implements Runnable {
    
    private GameWindow gameWindow;
    private final Panel panel;
    public Thread gameThread;
    private final int FPS_SET = 120;
    

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.2f;
    public final static int TILES_IN_WITDH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WITDH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    public Game() throws URISyntaxException {
        panel = new Panel(this);
        gameWindow = new GameWindow(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        playAudio(Constant.SoundConst.GAME_SOUND);
        startGameLoop();
        
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(Player player, Graphics g) throws URISyntaxException {
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
    
    public void playAudio(String soundPath) throws URISyntaxException {

        URL file = getClass().getClassLoader().getResource(soundPath);
        AudioInputStream audio;
        
        try{
            audio = AudioSystem.getAudioInputStream(file);
            Clip c  = AudioSystem.getClip();
            c.open(audio);
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }
    
    public static void playSound(String soundPath)throws URISyntaxException{
        URL file = Game.class.getClassLoader().getResource(soundPath);
        AudioInputStream audio;
        
        try{
            audio = AudioSystem.getAudioInputStream(file);
            Clip c  = AudioSystem.getClip();
            c.open(audio);
            c.start();
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }
    public void RepaintPanel(){
        panel.repaint();
    }
}
