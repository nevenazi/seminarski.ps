/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.marketingMenadzerOperacije;

import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class ObrisiMarketingMenadzerSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        MarketingMenadzer mm=(MarketingMenadzer) param;
        if(mm==null || !(mm instanceof MarketingMenadzer)){
            throw new Exception("Sistem ne može da obriše marketing menadžera");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((MarketingMenadzer)param);
    }
    
}
