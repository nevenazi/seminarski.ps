/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import model.Dizajner;

/**
 *
 * @author N
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Dizajner dizajner;

    public Dizajner getDizajner() {
        return dizajner;
    }

    public void setDizajner(Dizajner dizajner) {
        this.dizajner = dizajner;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Dizajner> dizajneri = broker.getAll(param,null);
        System.out.println("Lista dizajnera u loginOperacija izvrsiOperaciju() je: "+dizajneri);
        
        dizajner=null;
        for(Dizajner d:dizajneri){
            if(d.equals((Dizajner)param)){
                dizajner=d;
                return;
            }
        }
        
        
    }
    
}
