/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack;

/**
 *
 * @author Niko
 */
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import nikoe.blackjack.logic.BlackJackGameManager;
import nikoe.blackjack.logic.HandValueHolder;
import nikoe.blackjack.logic.players.Human;
import nikoe.blackjack.logic.Seat;
import nikoe.blackjack.logic.cards.BlackJackDeck;
import nikoe.blackjack.logic.cards.Card;
import nikoe.blackjack.logic.cards.Hand;
import nikoe.blackjack.logic.cards.Rank;
import nikoe.blackjack.logic.cards.Suit;
import nikoe.blackjack.logic.players.Dealer;
import nikoe.blackjack.logic.players.Player;
import nikoe.blackjack.ui.BlackJackUI;
import nikoe.blackjack.util.PropertyReader;

public class Main {

    private static BlackJackGameManager game;
    public static void main(String[] args) {
        BlackJackUI ui = new BlackJackUI();
        SwingUtilities.invokeLater(ui);
    }

}
