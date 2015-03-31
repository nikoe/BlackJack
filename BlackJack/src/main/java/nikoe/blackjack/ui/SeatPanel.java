/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import nikoe.blackjack.logic.BlackJackGameManager;
import nikoe.blackjack.logic.GameState;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.players.Human;

/**
 *
 * @author Niko
 */
public class SeatPanel extends JComponent {

    private Seat seat;
    private JLabel playerName;
    private JLabel playerMoney;
    private BlackJackGameManager manager;
    
    public SeatPanel(Seat seat, BlackJackGameManager manager) {
        this.seat = seat;
        this.manager = manager;
        addLabels();
        addMouseListener(listener);
    }
    
    private void addLabels() {
        playerName = new JLabel("",SwingConstants.CENTER);
        playerName.setText("SIT");
        playerName.setFont(new Font("Verdana", Font.BOLD, 14));
        playerName.setBounds(47, 60, 100, 50);
        playerName.setForeground(Color.WHITE);
        add(playerName);
        
        playerMoney = new JLabel("", SwingConstants.CENTER);
        playerMoney.setFont(new Font("Verdana", Font.BOLD, 14));
        playerMoney.setBounds(73, 90, 50, 50);
        playerMoney.setForeground(Color.WHITE);
        add(playerMoney);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.seat.getImage(), 0, 0, this);
        resetTexts();
    }
    
    public void resetTexts() {
        if(seat.hasPlayer()) {
            Human h = (Human)seat.getPlayer();
            playerMoney.setText(String.valueOf(h.getMoney()));
            playerName.setText(h.getName());

        }else {
           playerName.setText("SIT DOWN"); 
           playerMoney.setText("");
        }
    }
    
    private MouseListener listener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(manager.getGameState() == GameState.IDLE) {
                if(e.getButton() == 1) {
                    if(!seat.hasPlayer()) {
                        String name = JOptionPane.showInputDialog("Type name");
                        if(name != null) {
                            if(name.length() > 0) {
                                Human h = new Human(name);
                                seat.setPlayer(h);
                                repaint();
                            }
                        }
                    }
                }else if(e.getButton() == 3) {
                    if(seat.hasPlayer()) {
                        seat.release();
                        repaint();
                    }
                }
            }
        }
    };
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.seat.getImage().getWidth(null), this.seat.getImage().getHeight(null));
    }
}
