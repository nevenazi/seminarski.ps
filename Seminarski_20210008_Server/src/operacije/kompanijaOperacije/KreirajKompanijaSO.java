/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kompanijaOperacije;

import javax.swing.JOptionPane;
import model.Kompanija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class KreirajKompanijaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        Kompanija k=(Kompanija) param;
        if(k==null || !(k instanceof Kompanija)){
            throw new Exception("Sistem ne može da zapamti kompaniju.");
        }
        if(k.getNaziv()==null || k.getNaziv().isEmpty() || k.getSajt()==null || k.getSajt().isEmpty()){
            throw new Exception("Greška u unosu podataka o kompaniji.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Kompanija)param);
    }
    
}
