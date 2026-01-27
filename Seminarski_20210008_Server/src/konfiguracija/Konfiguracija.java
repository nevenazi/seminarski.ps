/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author N
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija=new Properties();
        try {
            konfiguracija.load(new FileInputStream("C:\\Users\\N\\Documents\\NetBeansProjects\\Seminarski Projektovanje softvera\\Seminarski_20210008_Server\\config\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Konfiguracija getInstance(){
        if (instance==null){
            instance= new Konfiguracija();
        } return instance;
    }

    public String getProperty(String kljuc){
        return konfiguracija.getProperty(kljuc, "n/a");
    }
    
    public void setProperty(String kljuc, String vrednost){
        this.konfiguracija.setProperty(kljuc, vrednost);
    }
    
    public void sacuvajIzmene(){
        try {
            konfiguracija.store(new FileOutputStream("C:\\Users\\N\\Documents\\NetBeansProjects\\Seminarski Projektovanje softvera\\Seminarski_20210008_Server\\config\\config.properties"),null);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
