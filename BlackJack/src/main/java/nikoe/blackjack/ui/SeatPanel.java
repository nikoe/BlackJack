package nikoe.blackjack.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
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
 * PANEL FOR SEAT
 *
 * @author Niko
 */
public class SeatPanel extends JComponent {

    private Seat seat;
    private JLabel playerName;
    private JLabel playerMoney;
    private JLabel playerBet;
    private BlackJackGameManager manager;
    private JButton betTen;
    private JButton betFifty;
    private JButton betHundred;

    public SeatPanel(Seat seat, BlackJackGameManager manager) {
        this.seat = seat;
        this.manager = manager;
        addLabels();
        addButtons();
        addMouseListener(listener);
    }

    private void addLabels() {
        playerName = new JLabel("", SwingConstants.CENTER);
        playerName.setFont(new Font("Verdana", Font.BOLD, 14));
        playerName.setBounds(47, 50, 100, 50);
        playerName.setForeground(Color.WHITE);
        add(playerName);

        playerMoney = new JLabel("", SwingConstants.CENTER);
        playerMoney.setFont(new Font("Verdana", Font.BOLD, 14));
        playerMoney.setBounds(50, 80, 100, 50);
        playerMoney.setForeground(Color.WHITE);
        add(playerMoney);

        playerBet = new JLabel("", SwingConstants.CENTER);
        playerBet.setFont(new Font("Verdana", Font.BOLD, 14));
        playerBet.setBounds(47, 105, 100, 50);
        playerBet.setForeground(Color.WHITE);
        playerBet.setText("");
        add(playerBet);
    }

    private void addButtons() {
        this.betTen = new JButton("10");
        this.betTen.setBounds(55, 180, 30, 20);
        this.betTen.setForeground(Color.BLACK);
        this.betTen.setHorizontalAlignment(SwingConstants.CENTER);
        this.betTen.setMargin(new Insets(0, 0, 0, 0));
        this.betTen.setFont(new Font("Verdana", Font.BOLD, 10));
        this.betTen.setVisible(false);
        this.betTen.addMouseListener(betListener);
        add(betTen);

        this.betFifty = new JButton("50");
        this.betFifty.setBounds(90, 180, 30, 20);
        this.betFifty.setForeground(Color.BLACK);
        this.betFifty.setHorizontalAlignment(SwingConstants.CENTER);
        this.betFifty.setMargin(new Insets(0, 0, 0, 0));
        this.betFifty.setFont(new Font("Verdana", Font.BOLD, 10));
        this.betFifty.setVisible(false);
        this.betFifty.addMouseListener(betListener);
        add(betFifty);
        
        this.betHundred = new JButton("100");
        this.betHundred.setBounds(125, 180, 35, 20);
        this.betHundred.setForeground(Color.BLACK);
        this.betHundred.setHorizontalAlignment(SwingConstants.CENTER);
        this.betHundred.setMargin(new Insets(0, 0, 0, 0));
        this.betHundred.setFont(new Font("Verdana", Font.BOLD, 10));
        this.betHundred.setVisible(false);
        this.betHundred.addMouseListener(betListener);
        add(betHundred);

        

    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            Image img;
            if (this.manager.getSeatPlaying() == this.seat.getSeatNumber()) {
                img = ImageIO.read(getClass().getClassLoader().getResource("images/seat_active.png"));
            } else {
                img = ImageIO.read(getClass().getClassLoader().getResource("images/seat.png"));
            }
            g.drawImage(img, 0, 0, this);
            checkGameState();
            resetTexts();
        } catch (IOException ex) {
            Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkGameState() {
        if (manager.getGameState() == GameState.PLACEBETS && seat.hasPlayer()) {
            this.betTen.setVisible(true);
            this.betFifty.setVisible(true);
            this.betHundred.setVisible(true);
        } else {
            this.betTen.setVisible(false);
            this.betFifty.setVisible(false);
            this.betHundred.setVisible(false);
        }
    }
    
    /**
     * Resets all texts in SeatPanel
     */
    public void resetTexts() {
        if (seat.hasPlayer()) {
            Human h = (Human) seat.getPlayer();
            playerName.setBounds(47, 50, 100, 50);
            playerMoney.setText(String.valueOf(h.getMoney()));
            playerName.setText(h.getName());
            playerBet.setText("Bet: " + String.valueOf(h.getBet()));

        } else {
            playerName.setBounds(47, 70, 100, 50);
            playerName.setText("SIT DOWN");
            playerMoney.setText("");
            playerBet.setText("");
        }
    }

    private MouseListener listener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (manager.getGameState() == GameState.IDLE) {
                if (e.getButton() == 1) {
                    if (!seat.hasPlayer()) {
                        String name = JOptionPane.showInputDialog("Type name");
                        if (name != null) {
                            if (name.length() > 0) {
                                Human h = new Human(name);
                                seat.setPlayer(h);
                                repaint();
                            }
                        }
                    }
                } else if (e.getButton() == 3) {
                    if (seat.hasPlayer()) {
                        seat.release();
                        repaint();
                    }
                }
            }
        }
    };

    private MouseListener betListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (manager.getGameState() == GameState.PLACEBETS) {
                if (seat.hasPlayer()) {
                    if (e.getButton() == 1) {
                        JButton b = (JButton) e.getSource();
                        Double bet = Double.parseDouble(b.getText());
                        Human h = (Human) seat.getPlayer();
                        if (h.getMoney() >= bet) {
                            h.setMoney(h.getMoney() - bet);
                            h.setBet(h.getBet() + bet);
                            repaint();
                        }else {
                            JOptionPane.showMessageDialog(null, "Not enough money!");
                        }
                    } else if (e.getButton() == 3) {
                        JButton b = (JButton) e.getSource();
                        Double bet = Double.parseDouble(b.getText());
                        Human h = (Human) seat.getPlayer();
                        if (h.getBet() >= bet) {
                            h.setMoney(h.getMoney() + bet);
                            h.setBet(h.getBet() - bet);
                            repaint();
                        }
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
