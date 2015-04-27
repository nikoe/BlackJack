package nikoe.blackjack.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JButton addBet;
    private JButton reduceBet;

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
        playerMoney.setBounds(73, 80, 50, 50);
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
        this.addBet = new JButton("+");
        this.addBet.setBounds(73, 177, 20, 20);
        this.addBet.setForeground(Color.BLACK);
        this.addBet.setHorizontalAlignment(SwingConstants.CENTER);
        this.addBet.setMargin(new Insets(0, 0, 0, 0));
        this.addBet.setFont(new Font("Verdana", Font.BOLD, 10));
        this.addBet.setVisible(false);
        this.addBet.addActionListener(new addBetButtonListener());
        add(addBet);

        this.reduceBet = new JButton("-");
        this.reduceBet.setBounds(103, 177, 20, 20);
        this.reduceBet.setForeground(Color.BLACK);
        this.reduceBet.setHorizontalAlignment(SwingConstants.CENTER);
        this.reduceBet.setMargin(new Insets(0, 0, 0, 0));
        this.reduceBet.setFont(new Font("Verdana", Font.BOLD, 10));
        this.reduceBet.setVisible(false);
        this.reduceBet.addActionListener(new reduceBetButtonListener());
        add(reduceBet);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.seat.getImage(), 0, 0, this);
        checkGameState();
        resetTexts();
    }

    private void checkGameState() {
        if (manager.getGameState() == GameState.IDLE && seat.hasPlayer()) {
            this.addBet.setVisible(true);
            this.reduceBet.setVisible(true);
        } else {
            this.addBet.setVisible(false);
            this.reduceBet.setVisible(false);
        }
    }

    public void resetTexts() {
        if (seat.hasPlayer()) {
            Human h = (Human) seat.getPlayer();
            playerName.setBounds(47, 50, 100, 50);
            playerMoney.setText(String.valueOf(h.getMoney()));
            playerName.setText(h.getName());
            playerBet.setText("Bet: " + h.getBet());

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

    class addBetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (manager.getGameState() == GameState.IDLE) {
                if (seat.hasPlayer()) {
                    Human h = (Human) seat.getPlayer();
                    if (h.getMoney() > 0) {
                        h.setMoney(h.getMoney() - 1.0);
                        h.setBet(h.getBet() + 1.0);
                        repaint();
                    }
                }
            }
        }

    }

    class reduceBetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (manager.getGameState() == GameState.IDLE) {
                if (seat.hasPlayer()) {
                    Human h = (Human) seat.getPlayer();
                    if (h.getBet() > 0) {
                        h.setMoney(h.getMoney()+1.0);
                        h.setBet(h.getBet() - 1.0);
                        repaint();
                    }
                }
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.seat.getImage().getWidth(null), this.seat.getImage().getHeight(null));
    }
}
