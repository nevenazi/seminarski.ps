/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.marketingMenadzerOperacije;

import java.util.List;
import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class UcitajMarketingMenadzerSO extends ApstraktnaGenerickaOperacija {

    MarketingMenadzer mMenadzer;

    public MarketingMenadzer getmMenadzer() {
        return mMenadzer;
    }

    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof MarketingMenadzer)){
            throw new Exception("Prosleđeni parametar nije marketing menadžer.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<MarketingMenadzer> lista=broker.getAll(param, "WHERE "+((MarketingMenadzer)param).vratiPrimatniKljuc());
        if (lista.isEmpty())
            throw new Exception("Sistem ne može da nađe marketing menadžera.");
        mMenadzer=lista.get(0);
        
    }
    
}
