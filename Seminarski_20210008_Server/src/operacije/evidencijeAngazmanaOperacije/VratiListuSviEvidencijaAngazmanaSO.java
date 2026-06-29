/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijeAngazmanaOperacije;

import java.util.List;
import model.EvidencijaAngazmana;
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
            throw new Exception("Prosleđeni parametar nije evidencija angažmana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
            evidencije=broker.getAll(param," ORDER BY evidencijaAngazmana.idEvidencijaAngazmana ASC , stavkaangazmana.rb ASC");
        
    }
    
}
