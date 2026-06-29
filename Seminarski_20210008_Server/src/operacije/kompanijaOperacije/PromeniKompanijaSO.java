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
public class PromeniKompanijaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Kompanija) || ((Kompanija) param).getIdKompanija()<=0){
            throw new Exception("Prosleđeni parametar nije kompanija.");
        }
        
        Kompanija k=(Kompanija) param;
        if(k.getNaziv()==null || k.getNaziv().isEmpty()
                ||(k.getSajt()!=null && !(k.getSajt().matches("^(https?:\\/\\/)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")))){
            throw new Exception("Greška u unosu podataka o kompaniji.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Kompanija)param);
    }
    
}
