/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kompanijaOperacije;

import java.util.List;
import model.Kompanija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class VratiListuSviKompanijaSO extends ApstraktnaGenerickaOperacija {

    List<Kompanija> kompanije;

    public List<Kompanija> getKompanije() {
        return kompanije;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        //TODO
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        kompanije=broker.getAll(param, null);
    }
    
}
