/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dizajnerOperacije;

import model.Dizajner;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class KreirajDizajnerSO extends ApstraktnaGenerickaOperacija {
        
    @Override
    protected void preduslovi(Object param) throws Exception {
        Dizajner d=(Dizajner)param;
        if(param==null || !(param instanceof Dizajner)){
            throw new Exception("Sistem ne može da zapamti dizajnera.");
        }
        
        if (d.getIme()==null || d.getIme().isEmpty() || d.getPrezime()==null || d.getPrezime().isEmpty() || d.getKorisnickoIme()==null || d.getKorisnickoIme().isEmpty()
                || d.getSifra()==null || d.getSifra().isEmpty()|| d.getSifra().length()<6){
            throw new Exception("Greška u unosu podataka dizajnera.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Dizajner)param);
    }
    
}
