package nikoe.blackjack.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;

/**
 * PANEL FOR HAND IN A TABLE
 * @author Niko
 */
public class HandPanel extends JPanel {

    private Seat seat;

    /**
     *
     * @param seat
     */
    public HandPanel(Seat seat) {
        setLayout(null);
        this.seat = seat;

    }



    @Override
    public void paintComponent(Graphics g) {
        if (this.seat.hasPlayer()) {
            int x = 0, y = 0;
            for (Hand h : this.seat.getPlayer().getHands()) {
                for (Card c : h.getCards()) {
                    g.drawImage(c.getImage(), x, y, this);
                    x += 25;
                }
                x = 0;
                y -= 25;
            }
        }
    }
    
    private String getHandValueString(Hand h) {
        String value = "";
        HandValueHolder hvh = h.getHandValue();
        if (hvh.getPossibleHandValues().size() == 1) {
            value = "" + hvh.getPossibleHandValues().get(0);
        } else if (hvh.getPossibleHandValues().size() == 2) {
            value = "" + hvh.getPossibleHandValues().get(0) + "/" + hvh.getPossibleHandValues().get(1);
        }

        return value;
    }
}
