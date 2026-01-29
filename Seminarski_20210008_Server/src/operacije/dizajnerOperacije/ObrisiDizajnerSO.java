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
public class ObrisiDizajnerSO extends ApstraktnaGenerickaOperacija {

    //private boolean uspesno;
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Dizajner)){
            throw new Exception("Sistem ne može da obriše dizajnera.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        broker.delete((Dizajner)param);
    }
    
}
