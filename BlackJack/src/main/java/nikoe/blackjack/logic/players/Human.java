/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.logic.players;

/**
 * CLASS FOR HUMAN EXTENDS PLAYER
 * @author Niko
 */
public class Human extends Player {
    
    private double money;
    
    /**
     *
     * @param name
     */
    public Human(String name) {
        super(name);
        initializeMoney();
    }
    
    
    private void initializeMoney() {
        //Check somewhere if this player already has some money
        
        this.money = 500.0;
    }
    
    /**
     *
     * @return
     */
    public double getMoney() {
        return this.money;
    }
    
    /**
     *
     * @param money
     */
    public void setMoney(double money) {
        this.money = money;
    }
    
}
