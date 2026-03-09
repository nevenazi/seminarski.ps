/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kompanijaOperacije;

import model.Kompanija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class ObrisiKompanijaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        Kompanija k=(Kompanija) param;
        if(k==null || !(k instanceof Kompanija)){
            throw new Exception("Sistem ne može da obriše kompaniju");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Kompanija)param);
    }
    
}
