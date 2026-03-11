/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Dizajner;
import model.EvidencijaAngazmana;
import model.Kompanija;
import model.MarketingMenadzer;
import model.Sertifikat;
import model.TipVizuala;
import operacije.dizajnerOperacije.KreirajDizajnerSO;
import operacije.dizajnerOperacije.PrijaviDizajnerSO;
import operacije.dizajnerOperacije.ObrisiDizajnerSO;
import operacije.dizajnerOperacije.PromeniDizajnerSO;
import operacije.dizajnerOperacije.VratiListuSviDizajnerSO;
import operacije.evidencijeAngazmanaOperacije.ObrisiEvidencijaAngazmanaSO;
import operacije.evidencijeAngazmanaOperacije.PretraziEvidencijaAngazmanaSO;
import operacije.evidencijeAngazmanaOperacije.VratiListuSviEvidencijaAngazmanaSO;
import operacije.kompanijaOperacije.KreirajKompanijaSO;
import operacije.kompanijaOperacije.ObrisiKompanijaSO;
import operacije.kompanijaOperacije.PromeniKompanijaSO;
import operacije.kompanijaOperacije.VratiListuSviKompanijaSO;
import operacije.marketingMenadzerOperacije.KreirajMarketingMenadzerSO;
import operacije.marketingMenadzerOperacije.ObrisiMarketingMenadzerSO;
import operacije.marketingMenadzerOperacije.PretraziMarketingMenadzerSO;
import operacije.marketingMenadzerOperacije.PromeniMarketingMenadzerSO;
import operacije.marketingMenadzerOperacije.VratiListuSviMarketingMenadzerSO;
import operacije.sertifikatOperacije.UbaciSertifikatSO;
import operacije.sertifikatOperacije.VratiListuSviSertifikatSO;
import operacije.tipVizualaOperacije.VratiListuSviTipVizualaSO;

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
        System.out.println(d.toString()+"controller");
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

    public List<MarketingMenadzer> vratiListuSviMarketingMenadzer() throws Exception {
        VratiListuSviMarketingMenadzerSO o=new VratiListuSviMarketingMenadzerSO();
        o.izvrsi(new MarketingMenadzer(), null);
        return o.getMenadzeri();
    }

    public void obrisiMarketingMenadzer(MarketingMenadzer mm) throws Exception {
        ObrisiMarketingMenadzerSO o= new ObrisiMarketingMenadzerSO();
        o.izvrsi(mm, null);
    }

    public void kreirajMarketingMenadzer(MarketingMenadzer mm) throws Exception {
        KreirajMarketingMenadzerSO o=new KreirajMarketingMenadzerSO();
        o.izvrsi(mm, null);
    }

    public void promeniMarketingMenadzer(MarketingMenadzer mm) throws Exception {
        PromeniMarketingMenadzerSO o=new PromeniMarketingMenadzerSO();
        o.izvrsi(mm, null);
    }

    public List<MarketingMenadzer> pretraziMarketingMenadzer(MarketingMenadzer mm) throws Exception {
        PretraziMarketingMenadzerSO o=new PretraziMarketingMenadzerSO();
        o.izvrsi(mm, mm.uslov());
        return o.getMenadzeri();
    }

    public List<Sertifikat> vratiListuSviSertifikat() throws Exception {
        VratiListuSviSertifikatSO o=new VratiListuSviSertifikatSO();
        o.izvrsi(new Sertifikat(),null);
        return o.getSertifikati();
    }

    public void ubaciSertifikat(Sertifikat s) throws Exception {
        UbaciSertifikatSO o=new UbaciSertifikatSO();
        o.izvrsi(s, null);
    }

    public List<TipVizuala> vratiListuSviTipVizuala() throws Exception {
        VratiListuSviTipVizualaSO o=new VratiListuSviTipVizualaSO();
        o.izvrsi(new TipVizuala(), null);
        return o.getVizuali();
    }

    public List<EvidencijaAngazmana> vratiListuSviEvidencijaAngazmana() throws Exception {
        VratiListuSviEvidencijaAngazmanaSO o =new VratiListuSviEvidencijaAngazmanaSO();
        o.izvrsi(new EvidencijaAngazmana(), null);
        return o.getEvidencije();
    }
    
    public List<EvidencijaAngazmana> pretraziEvidencijaAngazmana(EvidencijaAngazmana ea) throws Exception {
        PretraziEvidencijaAngazmanaSO o =new PretraziEvidencijaAngazmanaSO();
        o.izvrsi(ea, ea.uslov());
        return o.getEvidencije();
    }

    public void obrisiEvidencijaAngazmana(EvidencijaAngazmana evidencija) throws Exception {
        ObrisiEvidencijaAngazmanaSO o= new ObrisiEvidencijaAngazmanaSO();
        o.izvrsi(evidencija, null);
    }
    
    
}
