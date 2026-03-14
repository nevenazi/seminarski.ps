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
import java.util.List;
import model.StavkaAngazmana;

/**
 *
 * @author N
 */
public class PromeniEvidencijaAngazmanaSO extends ApstraktnaGenerickaOperacija {

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
            StavkaAngazmana stavka=ea.getStavkeAngazmana().get(0);
            System.out.println("stavka"+stavka.toString());
            List<StavkaAngazmana> bivseStavke=broker.getAll(stavka, stavka.uslov());
            broker.edit(param);
            for (StavkaAngazmana bivsaStavka : bivseStavke) {
                broker.delete(bivsaStavka);
            }
            
            for (StavkaAngazmana novaStavka : ea.getStavkeAngazmana()) {
                broker.add(novaStavka);
            }
        } catch (Exception e) {
            System.err.println("Greška u promeni evidencije sa stavkama: " + e.getMessage());
            throw e;
        }
    }
    
}
