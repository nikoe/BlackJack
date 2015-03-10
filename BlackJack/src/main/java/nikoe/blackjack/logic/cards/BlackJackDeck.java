package nikoe.blackjack.logic.cards;

/**
 *
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
        this.shuffle();
    }

    private void initDeck() {
        for (int i = 1; i <= this.numberOfDecks; i++) {
            for (Rank rank : Rank.values()) {
                for (Suit suit : Suit.values()) {
                    Card card = new Card(rank, suit);
                    this.availableCards.add(card);
                }
            }
        }
    }

}
