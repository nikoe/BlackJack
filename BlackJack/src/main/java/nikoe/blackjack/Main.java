package nikoe.blackjack;

/**
 *
 * @author Niko
 */

import javax.swing.SwingUtilities;
import nikoe.blackjack.logic.BlackJackGameManager;
import nikoe.blackjack.ui.BlackJackUI;

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
