/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import nikoe.blackjack.logic.BlackJackGameManager;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.BlackJackDeck;
import nikoe.blackjack.logic.cards.Card;

/**
 *
 * @author Niko
 */
public class GamePanel extends JPanel {

    private Image background;
    private BlackJackGameManager manager;
    private List<SeatPanel> seatPanels;
    private MenuPanel menu;
    private List<HandPanel> handPanels;

    public GamePanel(final BlackJackGameManager manager) {
        setLayout(null);
        background = loadBackgroundImage();
        this.manager = manager;
        this.seatPanels = new ArrayList<>();
        this.handPanels = new ArrayList<>();
        addSeatPanels();
        addMenuPanel();
        addHandPanels();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }

    private void addSeatPanels() {
        int seatCount = this.manager.getSeats().size();
        if (seatCount == 1) {
            addSeatPanel(this.manager.getSeat(1), 420, 515);
        } else if (seatCount == 2) {
            addSeatPanel(this.manager.getSeat(1), 680, 475);
            addSeatPanel(this.manager.getSeat(2), 160, 480);
        } else if (seatCount == 3) {
            addSeatPanel(this.manager.getSeat(1), 800, 430);
            addSeatPanel(this.manager.getSeat(2), 420, 515);
            addSeatPanel(this.manager.getSeat(3), 10, 430);
        } else if (seatCount == 4) {
            addSeatPanel(this.manager.getSeat(1), 800, 430);
            addSeatPanel(this.manager.getSeat(2), 560, 500);
            addSeatPanel(this.manager.getSeat(2), 270, 500);
            addSeatPanel(this.manager.getSeat(3), 10, 430);
        }
    }

    private Image loadBackgroundImage() {
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("images/table.png"));
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 0);

        try {
            mt.waitForID(0);
        } catch (InterruptedException x) {
            x.printStackTrace();
        }

        return image;
    }

    private void addSeatPanel(Seat seat, int x, int y) {
        SeatPanel panel = new SeatPanel(seat);
        panel.setBounds(x, y, (int) panel.getPreferredSize().getWidth() - 25, (int) panel.getPreferredSize().getHeight() - 25);
        add(panel);
        this.seatPanels.add(panel);
    }

    private void addMenuPanel() {
        MenuPanel menu = new MenuPanel(this.manager);
        menu.setBounds(0, 685, 1024, 55);
        add(menu);
        this.menu = menu;
    }
    
    private void addHandPanels() {
        HandPanel h = new HandPanel(this.manager.getSeat(1));
        h.setBounds(300, 300, 340, 100);
        add(h);
        this.handPanels.add(h);
        
    }
    
    public void repaintAll() {
        repaint();
        menu.repaint();
        for(SeatPanel p : this.seatPanels) {
            p.repaint();
        }
        for(HandPanel h : this.handPanels) {
            h.repaint();
        }
    }

}
