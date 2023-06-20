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
import object.ObjectManager;
import usage.Constant;
/**
 *
 * @author luuph
 */
public class Panel extends JPanel{

    private Player player1 = initPlayer(1);
    private Player player2 = initPlayer(2);
    
    private Chest chest = new Chest(0, 0, Constant.ObjectConst.CHEST, 0, ObjectManager.getLevelData());
    private Coin coin = new Coin(0, 0, Constant.ObjectConst.COIN, 0, ObjectManager.getLevelData());
    
    private LevelManager levelManager = new LevelManager();
    private ObjectManager objectManager = new ObjectManager(chest, coin);
    
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
        coin.update();
        chest.update();
        objectManager.draw(g, chest, coin);
        chest.drawHitBox(g, Chest.xDrawOffset, Chest.yDrawOffset);
        //coin.drawHitBox(g, Coin.xDrawOffset, Coin.yDrawOffset);
        game.update(player1, g);
        game.update(player2, g);        
    }
}