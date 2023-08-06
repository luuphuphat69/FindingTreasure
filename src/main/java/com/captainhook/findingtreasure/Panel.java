package com.captainhook.findingtreasure;

import entity.Chest;
import entity.Coin;
import entity.Player;
import input.KeyboardInput;
import input.MouseInput;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import level.LevelManager;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Logger;
import level.Level;
import object.ObjectManager;
import static usage.Constant.PlayerConst.IDLE;
import usage.HelpMethods;
/**
 *
 * @author luuph
 */
public class Panel extends JPanel{
    
    public ArrayList<Coin> listCoin;
    private Chest chest;
    
    private static Player player1;
    private static Player player2;
    
    private LevelManager levelManager;
    private ObjectManager objectManager;
    private Game game;
    
    public Panel(Game game){
        this.game = game;
        initClasses();
        setPanelSize();
        addKeyListener(new KeyboardInput(this, player1, player2));
        addMouseListener(new MouseInput(levelManager));
    }
    
    void setPanelSize(){
        Dimension dimension = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(dimension);
    }
    
    void windowFocusLost(){
        player1.setDirection(); // reset hướng đi của nhân vật khi view của cửa
        player2.setDirection(); // sổ bị mất
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(!levelManager.isPaused){
            update(g);
        }else{
            levelManager.draw(g);
            levelManager.menu.draw(g);
            levelManager.drawAllButton(g);
        }
    }
    
    public void initClasses(){
        levelManager = new LevelManager();
        
        player1 = new Player(100, 400, 25 * (int)Game.SCALE, 38 * (int)Game.SCALE, IDLE, false, false, 1);
        player2 = new Player(200, 400, 25 * (int)Game.SCALE, 38 * (int)Game.SCALE, IDLE, false, false, 2);
        
        player1.setImgLevelData(levelManager.getCurrentLevel().getImg());
        player2.setImgLevelData(levelManager.getCurrentLevel().getImg());
        
        listCoin = HelpMethods.GetCoins(levelManager.getCurrentLevel().getImg());
        chest = HelpMethods.GetChest(levelManager.getCurrentLevel().getImg());
        
        player1.getLevelManager(levelManager);
        player1.getChest(chest);
        player2.getLevelManager(levelManager);
        player2.getChest(chest);
        
        objectManager = new ObjectManager(chest, listCoin, player1, player2);
        levelManager.getObjectManager(objectManager);
        levelManager.getPlayer(player1, player2);
        
        player1.getLevelManager(levelManager);
        player2.getLevelManager(levelManager);
    }
    
    public void loadLevel(){
        levelManager.loadNextLevel();
        
        // Set lại rương tránh lấy lại rương ở level trước đó
        player1.getChest(objectManager.getChest());
        player2.getChest(objectManager.getChest());
    }
    
    public static void setPlayerSpawn(){
        player1.setxAxis(100);
        player1.setyAxis(400);
        
        player2.setxAxis(100);
        player2.setyAxis(400);
    }
    public void update(Graphics g){
        levelManager.draw(g);            // Vẽ map
        try {
            objectManager.draw(g);           // Vẽ rương và vàng
        } catch (URISyntaxException ex) {
            Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        objectManager.drawPoint(g);      // Vẽ điểm
        try {
            game.update(player1, g);   // Vẽ người chơi 1
        } catch (URISyntaxException ex) {
            Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            game.update(player2, g);   // Vẽ người chơi 2
        } catch (URISyntaxException ex) {
            Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        loadLevel();
    }
    public void setIsPaused(){
        levelManager.isPaused = !levelManager.isPaused;
    }
}