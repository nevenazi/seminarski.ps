/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Dizajner;
import operacije.dizajnerOperacije.KreirajDizajnerSO;
import operacije.dizajnerOperacije.PrijaviDizajnerSO;
import operacije.dizajnerOperacije.ObrisiDizajnerSO;
import operacije.dizajnerOperacije.UcitajDizajnereSO;

/**
 *
 * @author N
 */
public class Controller {
    private static Controller instance;

    private Controller() {
    }
    
    public static Controller getInstance(){
        if (instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public Dizajner login(Dizajner d) throws Exception {
        PrijaviDizajnerSO o =new PrijaviDizajnerSO();
        o.izvrsi(d,null);
        System.out.println("klasa controller:"+o.getDizajner());
        return o.getDizajner();
    }

    public List<Dizajner> ucitajDizajnere() throws Exception {
        UcitajDizajnereSO o=new UcitajDizajnereSO();
        o.izvrsi(new Dizajner(), null);
        return o.getDizajneri();
        
    }

    public void obrisiDizajner(model.Dizajner parametar) throws Exception {
        ObrisiDizajnerSO o=new ObrisiDizajnerSO();
        o.izvrsi(parametar, null);
        
    }

    public void kreirajDizajner(Dizajner d) throws Exception {
        KreirajDizajnerSO o=new KreirajDizajnerSO();
        o.izvrsi(d, null);
    }
    
    
}
