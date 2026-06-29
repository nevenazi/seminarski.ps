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
public class VratiListuSviMarketingMenadzerSO extends ApstraktnaGenerickaOperacija {

    List<MarketingMenadzer> menadzeri;

    public List<MarketingMenadzer> getMenadzeri() {
        return menadzeri;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof MarketingMenadzer)){
            throw new Exception("Prosleđeni parametar nije marketing menadžer.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
            menadzeri=broker.getAll(param,null);
        
    }
    
}
