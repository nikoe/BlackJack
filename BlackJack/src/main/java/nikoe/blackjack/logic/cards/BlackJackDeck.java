package nikoe.blackjack.logic.cards;

import nikoe.blackjack.util.CardImageLoader;

/**
 * Class for BlackJackDeck
 * BlackJackDeck may contain multiple 52 card decks
 * @author Niko
 */
public class BlackJackDeck extends Deck {

    private final int numberOfDecks;

    /**
     *
     * @param numberOfDecks Determine how many single 52 card decks to
     * initialize
     */
    public BlackJackDeck(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        this.initDeck();
    }

    private void initDeck() {
        CardImageLoader loader = new CardImageLoader();
        for (int i = 1; i <= this.numberOfDecks; i++) {
            for (Rank rank : Rank.values()) {
                for (Suit suit : Suit.values()) {
                    Card card = new Card(rank, suit, loader.getCardImage(rank, suit));
                    this.addCard(card);
                }
            }
        }
        this.shuffle();
    }

}
