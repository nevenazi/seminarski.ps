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
public class UcitajEvidencijaAngazmanaSO extends ApstraktnaGenerickaOperacija {

    EvidencijaAngazmana evidencija;

    public EvidencijaAngazmana getEvidencija() {
        return evidencija;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof EvidencijaAngazmana) || 
                ((EvidencijaAngazmana)param).getIdEvidencijaAngazmana()<=0){
            throw new Exception("Prosleđeni parametar nije evidencija angažmana");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<EvidencijaAngazmana> lista=broker.getAll(param,"WHERE "+((EvidencijaAngazmana)param).vratiPrimatniKljuc());
        if (lista.isEmpty()) throw new Exception("Sistem ne može da nađe evidenciju angažmana");
        evidencija=lista.get(0);
    }
    
}
