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
        if(param==null || !(param instanceof EvidencijaAngazmana)){
            throw new Exception("Prosleđeni parametar nije evidencija angažmana.");
        }
        EvidencijaAngazmana ea= (EvidencijaAngazmana) param;
        if(ea.getDizajner()==null || !(ea.getDizajner() instanceof Dizajner) || ea.getMarketingMenadzer()==null 
                || !(ea.getMarketingMenadzer() instanceof MarketingMenadzer) || ea.getRok()==null 
                || ea.getStavkeAngazmana()==null || ea.getStavkeAngazmana().isEmpty()|| ea.getUkupanIznos()<0){
            throw new Exception("Greška u unosu podataka evidencije angažmana.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
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
        
    }
    
}
