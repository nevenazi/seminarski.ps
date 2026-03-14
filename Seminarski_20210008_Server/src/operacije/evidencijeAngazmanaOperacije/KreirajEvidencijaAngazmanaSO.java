/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijeAngazmanaOperacije;


import model.Dizajner;
import model.EvidencijaAngazmana;
import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.StavkaAngazmana;

/**
 *
 * @author N
 */
public class KreirajEvidencijaAngazmanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        EvidencijaAngazmana ea= (EvidencijaAngazmana) param;
        if(ea==null || !(ea instanceof EvidencijaAngazmana)){
            throw new Exception("Sistem ne može da zapamti evidenciju angažmana.");
        }
        if(ea.getDizajner()==null || !(ea.getDizajner() instanceof Dizajner) || ea.getMarketingMenadzer()==null 
                || !(ea.getMarketingMenadzer() instanceof MarketingMenadzer) || ea.getRok()==null 
                || ea.getStavkeAngazmana()==null || ea.getStavkeAngazmana().isEmpty()|| ea.getUkupanIznos()<0){
            throw new Exception("Greška u unosu podataka o evidenciji angažmana.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        try {
            EvidencijaAngazmana ea = (EvidencijaAngazmana) param;
            PreparedStatement ps = broker.add(ea);
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            ea.setIdEvidencijaAngazmana(id);

            for (StavkaAngazmana stavka : ea.getStavkeAngazmana()) {
                stavka.setEvidencijaAngazmana(ea);
                broker.add(stavka);
            }
        } catch (Exception e) {
            System.err.println("Greška u dodavanju evidencije sa stavkama: " + e.getMessage());
            throw e;
        }
    }
    
}
