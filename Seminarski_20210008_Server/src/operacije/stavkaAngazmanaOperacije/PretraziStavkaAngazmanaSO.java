/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavkaAngazmanaOperacije;

import java.util.List;
import model.StavkaAngazmana;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class PretraziStavkaAngazmanaSO extends ApstraktnaGenerickaOperacija {

    List<StavkaAngazmana> stavke;

    public List<StavkaAngazmana> getStavke() {
        return stavke;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof StavkaAngazmana)){
            throw new Exception("Sistem ne može da nađe stavke angažmana po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        stavke=broker.getAll(param,kljuc);
        
    }
    
}
