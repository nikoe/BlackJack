/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

/**
 * ENUM FOR GAMESTATE
 * @author Niko
 */
public enum GameState {

    /**
     * IDLE STATE FOR GAME
     */
    IDLE,

    /**
     * STATE FOR PLACING BETS
     */
    PLACEBETS,

    /**
     * STATE FOR ROUND ACTIVE
     */
    ROUNDACTIVE,

    /**
     * STATE FOR DEAL TO DEALER
     */
    DEALTODEALER,

    /**
     * ENDROUND STATE FOR GAME
     */
    ENDROUND
}
