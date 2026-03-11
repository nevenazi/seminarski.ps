/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.marketingMenadzerOperacije;

import operacije.kompanijaOperacije.*;
import javax.swing.JOptionPane;
import model.Kompanija;
import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class KreirajMarketingMenadzerSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        MarketingMenadzer mm= (MarketingMenadzer) param;
        if(mm==null || !(mm instanceof MarketingMenadzer)){
            throw new Exception("Sistem ne može da zapamti marketing menadžera.");
        }
        if(mm.getIme()==null || mm.getIme().isEmpty() || mm.getPrezime()==null || mm.getPrezime().isEmpty()|| mm.getEmail()==null || mm.getEmail().isEmpty()|| mm.getTelefon()==null || mm.getTelefon().isEmpty()|| mm.getKompanija()==null && (mm.getKompanija() instanceof Kompanija)){
            throw new Exception("Greška u unosu podataka o marketing menadžeru.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((MarketingMenadzer)param);
    }
    
}
