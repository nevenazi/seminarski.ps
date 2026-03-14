/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijeAngazmanaOperacije;

import operacije.marketingMenadzerOperacije.*;
import operacije.kompanijaOperacije.*;
import java.util.List;
import javax.swing.JOptionPane;
import model.EvidencijaAngazmana;
import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class VratiListuSviEvidencijaAngazmanaSO extends ApstraktnaGenerickaOperacija {

    List<EvidencijaAngazmana> evidencije;

    public List<EvidencijaAngazmana> getEvidencije() {
        return evidencije;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof EvidencijaAngazmana)){
            throw new Exception("Sistem ne može da nađe evidencije angažmana po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        evidencije=broker.getAll(param,null);
        
    }
    
}
