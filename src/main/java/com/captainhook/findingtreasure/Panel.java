package com.captainhook.findingtreasure;

import entity.Chest;
import entity.Coin;
import entity.Player;
import input.KeyboardInput;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import level.LevelManager;
import static entity.Player.initPlayer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.ObjectManager;
import usage.Constant;
import usage.LoadSave;
/**
 *
 * @author luuph
 */
public class Panel extends JPanel{

    private Player player1 = initPlayer(1);
    private Player player2 = initPlayer(2);
    
    BufferedImage img = LoadSave.GetSpriteAtlas("level/" + LoadSave.LEVEL_ONE_DATA);
    public ArrayList<Coin> listCoin = ObjectManager.GetCoins(img);
    private Chest chest = new Chest(0, 0, Constant.ObjectConst.CHEST, 0, ObjectManager.getLevelData());
    
    private LevelManager levelManager = new LevelManager();
    private ObjectManager objectManager = new ObjectManager(chest, listCoin, player1, player2);
    
    
    private Game game;
    
    public Panel(Game game){
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInput(this, player1, player2));
    }
    
    void setPanelSize(){
        Dimension dimension = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(dimension);
    }
    
    void windowFocusLost(){
        player1.setDirection();
        player2.setDirection();
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        levelManager.draw(g);
        chest.update();
        objectManager.draw(g);
        objectManager.drawPoint(g);
        chest.drawHitBox(g, Chest.xDrawOffset, Chest.yDrawOffset);
        game.update(player1, g);
        game.update(player2, g);        
    }
}