/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Dizajner;
import model.Kompanija;
import operacije.dizajnerOperacije.KreirajDizajnerSO;
import operacije.dizajnerOperacije.PrijaviDizajnerSO;
import operacije.dizajnerOperacije.ObrisiDizajnerSO;
import operacije.dizajnerOperacije.PromeniDizajnerSO;
import operacije.dizajnerOperacije.VratiListuSviDizajnerSO;
import operacije.kompanijaOperacije.KreirajKompanijaSO;
import operacije.kompanijaOperacije.ObrisiKompanijaSO;
import operacije.kompanijaOperacije.PromeniKompanijaSO;
import operacije.kompanijaOperacije.VratiListuSviKompanijaSO;

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

    public Dizajner prijaviDizajner(Dizajner d) throws Exception {
        PrijaviDizajnerSO o =new PrijaviDizajnerSO();
        o.izvrsi(d,null);
        return o.getDizajner();
    }

    public List<Dizajner> vratiListuSviDizajner() throws Exception {
        VratiListuSviDizajnerSO o=new VratiListuSviDizajnerSO();
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

    public void promeniDizajner(Dizajner d) throws Exception {
        PromeniDizajnerSO o=new PromeniDizajnerSO();
        o.izvrsi(d, null);
    }

    public List<Kompanija> vratiListuSviKompanije() throws Exception {
        VratiListuSviKompanijaSO o=new VratiListuSviKompanijaSO();
        o.izvrsi(new Kompanija(), null);
        return o.getKompanije();
    }

    public void kreirajKompanija(Kompanija k) throws Exception {
        KreirajKompanijaSO o=new KreirajKompanijaSO();
        o.izvrsi(k, null);
    }

    public void promeniKompanija(Kompanija k) throws Exception {
        PromeniKompanijaSO o=new PromeniKompanijaSO();
        o.izvrsi(k, null);
    }

    public void obrisiKompanija(Kompanija k) throws Exception {
        ObrisiKompanijaSO o=new ObrisiKompanijaSO();
        o.izvrsi(k, null);
    }
    
    
}
