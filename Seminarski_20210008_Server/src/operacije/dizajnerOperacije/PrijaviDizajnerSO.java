/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dizajnerOperacije;

import java.util.List;
import model.Dizajner;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class PrijaviDizajnerSO extends ApstraktnaGenerickaOperacija {

    Dizajner dizajner;

    public Dizajner getDizajner() {
        return dizajner;
    }

    public void setDizajner(Dizajner dizajner) {
        this.dizajner = dizajner;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Dizajner) || ((Dizajner)param).getKorisnickoIme()==null|| ((Dizajner)param).getKorisnickoIme().isEmpty()
                || ((Dizajner)param).getSifra()==null ||((Dizajner)param).getSifra().isEmpty()||((Dizajner)param).getSifra().length()<6){
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Dizajner> dizajneri = broker.getAll(param,null);
        
        
        dizajner=null;
        for(Dizajner d:dizajneri){
            if(d.equals((Dizajner)param)){
                dizajner=d;
                return;
            }
        }
        
        
    }
    
}
