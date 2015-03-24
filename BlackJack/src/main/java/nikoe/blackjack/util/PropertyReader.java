/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class PropertyReader {

    private Properties props;
    private String filename;

    public PropertyReader(String filename) {
        this.props = new Properties();
        this.filename = filename;
    }

    public String getProperty(String name) {
        try {
            this.props.load(getClass().getResourceAsStream("/nikoe/blackjack/config/"+this.filename));
        } catch (Exception ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return this.props.getProperty(name);

    }
    
    public String getProperty(String name, String def) {
        try {
            this.props.load(getClass().getResourceAsStream("/nikoe/blackjack/config/"+this.filename));
        } catch (Exception ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            return def;
        }
        return this.props.getProperty(name);
    }

}
