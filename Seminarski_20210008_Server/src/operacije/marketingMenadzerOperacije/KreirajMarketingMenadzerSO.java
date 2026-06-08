/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.marketingMenadzerOperacije;

import model.Kompanija;
import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class KreirajMarketingMenadzerSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof MarketingMenadzer)){
            throw new Exception("Sistem ne može da zapamti marketing menadžera.");
        }
        
        MarketingMenadzer mm= (MarketingMenadzer) param;
        if(mm.getIme()==null || mm.getIme().isEmpty() || mm.getPrezime()==null || mm.getPrezime().isEmpty()
                || mm.getEmail()==null || mm.getEmail().isEmpty() || !(mm.getEmail().matches("^[a-z0-9._]+@[a-z0-9.]+\\.[a-z]{2,}$"))
                || mm.getTelefon()==null || mm.getTelefon().isEmpty() || !(mm.getTelefon().matches("^\\+3816\\d{7,8}$"))
                || mm.getKompanija()==null || !(mm.getKompanija() instanceof Kompanija)){
            throw new Exception("Greška u unosu podataka o marketing menadžeru.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((MarketingMenadzer)param);
    }
    
}
