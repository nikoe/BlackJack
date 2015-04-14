/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import nikoe.blackjack.logic.players.Player;

/**
 *
 * @author Niko
 */
public class Seat {

    private Player player = null;
    private final int seatNumber;
    private Image img;

    public Seat(int number) {
        this.seatNumber = number;
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("images/seat.png"));
        } catch (IOException ex) {
            Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public int getSeatNumber() {
        return this.seatNumber;
    }
    
    public Player getPlayer() {
        return this.player;
    }

    public void release() {
        this.player = null;
    }

    public boolean hasPlayer() {
        if (this.player != null) {
            return true;
        } else {
            return false;
        }
    }

    public Image getImage() {
        return this.img;
    }

}
