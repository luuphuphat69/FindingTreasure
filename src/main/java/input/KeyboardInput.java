/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package input;

import Model.Player;
import com.captainhook.findingtreasure.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import usage.Constant;

/**
 *
 * @author luuph
 */
public class KeyboardInput implements KeyListener{
    
    private Panel panel;
    private Player player;
    
    public KeyboardInput(Panel panel, Player player){
        this.panel = panel;
        this.player = player;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setIsMoving(true);
                player.setLeft(true);
                panel.repaint();
                System.out.println("Key A pressed");
                break;
            case KeyEvent.VK_D:
                player.setIsMoving(true);
                player.setRight(true);
                panel.repaint();
                System.out.println("Key D pressed");
                break;
            case KeyEvent.VK_W:
                player.setIsMoving(true);
                player.setUp(true);
                panel.repaint();
                System.out.println("Key W pressed");
                break;
            case KeyEvent.VK_S:
                player.setIsMoving(true);
                player.setDown(true);
                panel.repaint();
                System.out.println("Key S pressed");
                break;
            case KeyEvent.VK_SPACE:
                player.setIsAttacking(true);
                panel.repaint();
                System.out.println("Key Space pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setIsMoving(false);
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setIsMoving(false);
                player.setRight(false);
                break;
            case KeyEvent.VK_W:
                player.setIsMoving(false);
                player.setUp(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
            case KeyEvent.VK_SPACE:
                player.setIsAttacking(false);
        }
    }
    
}
