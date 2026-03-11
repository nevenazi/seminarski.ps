/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijeAngazmanaOperacije;

import model.EvidencijaAngazmana;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class ObrisiEvidencijaAngazmanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        EvidencijaAngazmana ea=(EvidencijaAngazmana) param;
        if(ea==null || !(ea instanceof EvidencijaAngazmana)){
            throw new Exception("Sistem ne može da obriše evidenciju angažmana.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((EvidencijaAngazmana)param);
    }
    
}
