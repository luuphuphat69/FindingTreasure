/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package input;

import com.captainhook.findingtreasure.Game;
import entity.Player;
import com.captainhook.findingtreasure.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import usage.Constant;

/**
 *
 * @author luuph
 */
public class KeyboardInput implements KeyListener {

    private Panel panel;
    private Player player1;
    private Player player2;

    public KeyboardInput(Panel panel, Player player1, Player player2) {
        this.panel = panel;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player1.setIsMoving(true);
                player1.setLeft(true);
                panel.repaint();
                System.out.println("Key A pressed");
                break;

            case KeyEvent.VK_D:
                player1.setIsMoving(true);
                player1.setRight(true);
                panel.repaint();
                System.out.println("Key D pressed");
                break;

            case KeyEvent.VK_W: {
                try {
                    Game.playSound(Constant.SoundConst.JUMP_SOUND);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(KeyboardInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            player1.setJump(true);
            panel.repaint();
            System.out.println("Key W pressed");
            break;

            case KeyEvent.VK_S:
                player1.setIsMoving(true);
                player1.setDown(true);
                panel.repaint();
                System.out.println("Key S pressed");
                break;

            case KeyEvent.VK_SPACE:
                player1.setIsAttacking(true);
                panel.repaint();
                System.out.println("Key Space pressed");
                break;

            case KeyEvent.VK_LEFT:
                player2.setIsMoving(true);
                player2.setLeft(true);
                panel.repaint();
                System.out.println("Key left pressed");
                break;

            case KeyEvent.VK_RIGHT:
                player2.setIsMoving(true);
                player2.setRight(true);
                panel.repaint();
                System.out.println("Key right pressed");
                break;

            case KeyEvent.VK_UP:
                player2.setJump(true);
                panel.repaint();
                System.out.println("Key W pressed");
                try {
                    Game.playSound(Constant.SoundConst.JUMP_SOUND);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(KeyboardInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case KeyEvent.VK_DOWN:
                player2.setIsMoving(true);
                player2.setDown(true);
                panel.repaint();
                System.out.println("Key down pressed");
                break;

            case KeyEvent.VK_0:
                player2.setIsAttacking(true);
                panel.repaint();
                System.out.println("0 pressed");
                break;
                
            case KeyEvent.VK_ESCAPE:
                panel.setIsPaused();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player1.setIsMoving(false);
                player1.setLeft(false);
                break;

            case KeyEvent.VK_D:
                player1.setIsMoving(false);
                player1.setRight(false);
                break;

            case KeyEvent.VK_W:
                player1.setJump(false);
                break;

            case KeyEvent.VK_S:
                player1.setIsMoving(false);
                player1.setDown(false);

            case KeyEvent.VK_SPACE:
                player1.setIsAttacking(false);

            case KeyEvent.VK_LEFT:
                player2.setIsMoving(false);
                player2.setLeft(false);
                break;

            case KeyEvent.VK_RIGHT:
                player2.setIsMoving(false);
                player2.setRight(false);
                break;
            case KeyEvent.VK_UP:
                player2.setJump(false);
                break;

            case KeyEvent.VK_0:
                player2.setIsAttacking(false);
        }
    }

}
