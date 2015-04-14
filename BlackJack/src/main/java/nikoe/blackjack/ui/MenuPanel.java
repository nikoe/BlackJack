/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.ui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import nikoe.blackjack.logic.BlackJackGameManager;
import nikoe.blackjack.logic.GameState;

/**
 *
 * @author Niko
 */
public class MenuPanel extends JPanel {

    private BlackJackGameManager manager;
    private JButton startRound;
    private JButton hitButton;
    private JButton standButton;
    
    public MenuPanel(BlackJackGameManager manager) {
        setLayout(new GridLayout(1, 4));
        this.manager = manager;
        addButtons();
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public void repaint() {
        setButtonVisibility();
        super.repaint();
    }

    private void setButtonVisibility() {
        if (this.manager != null) {
            if (this.manager.getGameState() == GameState.IDLE) {
                this.hitButton.setVisible(false);
                this.standButton.setVisible(false);
                this.startRound.setVisible(true);
            }
            if (this.manager.getGameState() == GameState.PLACEBETS) {
                this.hitButton.setVisible(false);
                this.standButton.setVisible(false);
                this.startRound.setVisible(false);
            }
            if (this.manager.getGameState() == GameState.ROUNDACTIVE) {
                this.hitButton.setVisible(true);
                this.standButton.setVisible(true);
                this.startRound.setVisible(false);
            }
            if (this.manager.getGameState() == GameState.DEALTODEALER) {
                this.hitButton.setVisible(false);
                this.standButton.setVisible(false);
                this.startRound.setVisible(false);
            }
            if (this.manager.getGameState() == GameState.ENDROUND) {
                this.hitButton.setVisible(false);
                this.standButton.setVisible(false);
                this.startRound.setVisible(false);
            }
        }
    }

    private void addButtons() {
        startRound = new JButton("New Round");
        startRound.addActionListener(new StartRoundButtonListener());
        add(startRound);
        hitButton = new JButton("Hit");
        hitButton.setVisible(false);
        hitButton.addActionListener(new HitButtonListener());
        add(hitButton);
        standButton = new JButton("Stand");
        standButton.setVisible(false);
        standButton.addActionListener(new StandButtonListener());
        add(standButton);
    }

    class StartRoundButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (manager.seatsHasPlayers() && manager.getGameState() == GameState.IDLE) {
                manager.startNewRound();
            }
        }

    }

    class HitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (manager.getGameState() == GameState.ROUNDACTIVE) {
                manager.hitCard();
            }
        }

    }

    class StandButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (manager.getGameState() == GameState.ROUNDACTIVE) {
                manager.activeHandStand();
            }
        }

    }
}
