/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

/**
 *
 * @author Niko
 */
public class Seat {
    
    private Player player = null;
    private final int seatNumber;
    
    public Seat(int number) {
        this.seatNumber = number;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void release() {
        this.player = null;
    }
    
    public int getSeatNumber() {
        return this.seatNumber;
    }
    
}
