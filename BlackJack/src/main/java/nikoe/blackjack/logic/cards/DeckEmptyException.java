/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.cards;

/**
 *
 * @author Niko
 */
class DeckEmptyException extends RuntimeException {

    public DeckEmptyException(String message) {
        super(message);
    }

}
