/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import nikoe.blackjack.logic.BlackJackGameManager;
import nikoe.blackjack.ui.GamePanel;

/**
 *
 * @author ekni
 */
public class BlackJackUI implements Runnable{
    
    private JFrame frame;
    private BlackJackGameManager manager;
    
    public BlackJackUI(BlackJackGameManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void run() {
        frame = new JFrame("BlackJack");
        frame.setPreferredSize(new Dimension(1024, 768));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createGamePanel(frame.getContentPane());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createGamePanel(Container container) {
        GamePanel gamePanel = new GamePanel(manager);
        manager.setUi(gamePanel);
        container.add(gamePanel);
    } 
}
