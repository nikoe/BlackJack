/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.ui;

import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;

/**
 *
 * @author Niko
 */
public class HandPanel extends JPanel{
    
    private Seat seat;
    private JLabel handValue;
    
    public HandPanel(Seat seat) {
        this.seat = seat;
        handValue = new JLabel();
        handValue.setBounds(50, 50, 10, 10);
        add(handValue);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(this.seat.hasPlayer()) {
            Hand h = this.seat.getPlayer().getHands().get(0);
            int x = 0, y = 0;
            for(Card c : h.getCards()) {
                g.drawImage(c.getImage(), x, y, this);
                x += 25;
            }
        }
    }
    
    /*
    @Override
    public void repaint() {
        super.repaint();
        if(this.seat != null) {
            if(this.seat.hasPlayer()) {
                Hand h = this.seat.getPlayer().getHands().get(0);
                handValue.setText(""+h.getFinalHandValue());
            }
        }
    }
    */
    
}
