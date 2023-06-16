package com.captainhook.findingtreasure;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 *
 * @author luuph
 */
public class GameWindow extends JFrame{
    private JFrame jframe;
    
    public GameWindow(Panel panel){
        jframe = new JFrame();
        jframe.add(panel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
               panel.windowFocusLost();
            }
        });
    }
}
