/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author ekni
 */
public class BlackJackUI implements Runnable{
    
    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("BlackJack");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
