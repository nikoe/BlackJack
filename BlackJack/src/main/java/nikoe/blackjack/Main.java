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

/**
 * Main Class for this application
 * @author Niko
 */
public class Main {

    /**
     * Main method for this application
     * @param args
     */
    public static void main(String[] args) {
        BlackJackGameManager manager = new BlackJackGameManager();
        BlackJackUI ui = new BlackJackUI(manager);
        SwingUtilities.invokeLater(ui);
    }

}
