package com.captainhook.findingtreasure;

import Model.Player;
import input.KeyboardInput;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import level.LevelManager;
import static usage.Constant.PlayerConst.*;
/**
 *
 * @author luuph
 */
public class Panel extends JPanel{

    private Player player = new Player(100, 100, 32 * (int)Game.SCALE, 38 * (int)Game.SCALE, IDLE, false, false);
    private LevelManager levelManager = new LevelManager();
    private Game game;
    
    public Panel(Game game){
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInput(this, player));
    }
    
    void setPanelSize(){
        Dimension dimension = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(dimension);
    }
    
    void windowFocusLost(){
        player.setLeft(false);
        player.setRight(false);
        player.setUp(false);
        player.setDown(false);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        player.importPlayerImage();
        player.loadAnimations();      
        player.updateAnimations();
        player.updateHitbox();
        
        player.setAnimation();
        player.updatePosition();
        
        levelManager.draw(g);
        player.render(g);
        player.drawHitbox(g);
    }
}
