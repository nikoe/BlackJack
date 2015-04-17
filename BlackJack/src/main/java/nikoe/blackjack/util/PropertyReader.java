/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikoe.blackjack.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CLASS FOR READING GAME SETTINGS
 * @author Niko
 */
public class PropertyReader {

    private Properties props;
    private String filename;

    /**
     *
     * @param filename
     */
    public PropertyReader(String filename) {
        this.props = new Properties();
        this.filename = filename;
    }

    /**
     * RETURNS PROPERTY, IF PROPERTY NOT FOUND THEN RETURN DEFAULT
     * @param name
     * @param def
     * @return
     */
    public String getProperty(String name, String def) {
        try {
            File jarPath=new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
            String propertiesPath=jarPath.getParentFile().getAbsolutePath();
            propertiesPath = propertiesPath.replace("%20", " ");
            File f = new File(propertiesPath+"/config/"+this.filename);
            if(f.exists()) {
                this.props.load(new FileInputStream(propertiesPath+"/config/"+this.filename));
            }else {
                this.props.load(getClass().getClassLoader().getResourceAsStream("config/"+this.filename));
            }
        } catch (Exception ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
            return def;
        }
        
        String value = this.props.getProperty(name);
        if(value == null) {
            return def;
        }
        if(value.length() == 0) {
            return def;
        }
        
        return value;
    }

}
