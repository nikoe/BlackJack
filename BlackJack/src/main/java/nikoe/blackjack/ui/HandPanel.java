package nikoe.blackjack.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;

/**
 * PANEL FOR HAND IN A TABLE
 *
 * @author Niko
 */
public class HandPanel extends JPanel {

    private Seat seat;
    private List<JLabel> list = new ArrayList<>();

    /**
     *
     * @param seat
     */
    public HandPanel(Seat seat) {
        setLayout(null);
        //setBorder(BorderFactory.createLineBorder(Color.black));
        this.seat = seat;

    }

    @Override
    public void paintComponent(Graphics g) {
        if (this.seat.hasPlayer()) {
            int x = 0;
            int y = this.getHeight() - 97;
            removeOldValues();
            for (Hand h : this.seat.getPlayer().getHands()) {
                for (Card c : h.getCards()) {
                    g.drawImage(c.getImage(), x, y, this);
                    x += 25;
                }
                addValue(h, x, y + 15);
                x = 0;
                y -= 25;
            }
        }
    }
        
    @Override
    public void repaint() {
        if(this.list != null) {
            removeOldValues();
        }
    }

    private void removeOldValues() {
        for (JLabel l : this.list) {
            this.remove(l);
            this.revalidate();
        }
    }

    private void addValue(Hand h, int x, int y) {
        JLabel t = new JLabel();
        t.setText(getHandValueString(h));
        t.setFont(new Font("Verdana", Font.BOLD, 14));
        t.setBounds(x + 50, y, 50, 50);
        t.setForeground(Color.WHITE);
        this.list.add(t);
        add(t);
    }

    private String getHandValueString(Hand h) {
        String value = "";
        /*
         */
        if (h.getIsReady()) {
            value = "" + h.getFinalHandValue();
            if (h.getFinalHandValue() > 21) {
                value = "BUST";
            }

            if (h.isBlackJack()) {
                value = "BJ";
            }

        } else {
            HandValueHolder hvh = h.getHandValue();
            if (hvh.getPossibleHandValues().size() == 1) {
                value = "" + hvh.getPossibleHandValues().get(0);
            } else if (hvh.getPossibleHandValues().size() == 2) {
                value = "" + hvh.getPossibleHandValues().get(0) + "/" + hvh.getPossibleHandValues().get(1);
            }

            if (h.getFinalHandValue() > 21) {
                value = "BUST";
            }

            if (h.isBlackJack()) {
                value = "BJ";
            }
        }
        return value;
    }

}
