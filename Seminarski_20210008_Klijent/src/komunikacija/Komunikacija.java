/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dizajner;
import model.EvidencijaAngazmana;
import model.Kompanija;
import model.MarketingMenadzer;
import model.Sertifikat;
import model.TipVizuala;

/**
 *
 * @author N
 */
public class Komunikacija {
    
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }
    
    public static Komunikacija getInstance(){
        if (instance==null){
            instance=new Komunikacija();
        }
        return instance;
        
    }
    
    public void konekcija(){
        try {
            
            socket=new Socket("localhost",9000);
            
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        posiljalac=new Posiljalac(socket);
        primalac= new Primalac(socket);
        System.out.println(socket);
    }

    public Dizajner prijaviDizajner(String username, String password)throws Exception{
        
        
        Dizajner dizajner=new Dizajner();
        dizajner.setKorisnickoIme(username);
        dizajner.setSifra(password);
        System.out.println(dizajner);
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PRIJAVI_DIZAJNER,dizajner);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        dizajner=(Dizajner) odg.getOdgovor();
        return dizajner;
        
    }

    public List<Dizajner> vratiSveDizajner() throws Exception {
        List <Dizajner> dizajneri;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_DIZAJNER, null);
        posiljalac.posalji(zahtev);
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        dizajneri=(List<Dizajner>) odg.getOdgovor();
        return dizajneri;     
    }

    public void obrisiDizajner(Dizajner d) throws Exception {
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.OBRISI_DIZAJNER, d);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor)primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
    }

    public void kreirajDizajner(Dizajner d) throws Exception {
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.KREIRAJ_DIZAJNER, d);
        posiljalac.posalji(zahtev);//TODO
        ServerskiOdgovor odg=(ServerskiOdgovor)primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
    }

    public void promeniDizajner(Dizajner d) throws Exception {
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PROMENI_DIZAJNER, d);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor)primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
    }

    public List<Kompanija> vratiSveKompanija() throws Exception {
        List <Kompanija> kompanije;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_KOMPANIJA, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        kompanije=(List<Kompanija>) odg.getOdgovor();
        return kompanije; 
    }

    public void kreirajKompanija(Kompanija k) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.KREIRAJ_KOMPANIJA, k);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public void promeniKompanija(Kompanija k) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.PROMENI_KOMPANIJA, k);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public void obrisiKompanija(Kompanija k) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.OBRISI_KOMPANIJA, k);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public List<MarketingMenadzer> vratiSveMarketingMenadzer() throws Exception {
        List <MarketingMenadzer> menadzeri;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_MARKETING_MENADZER, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        menadzeri=(List<MarketingMenadzer>) odg.getOdgovor();
        return menadzeri; 
    }

    public void obrisiMarketingMenadzer(MarketingMenadzer mm)throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.OBRISI_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public void kreirajMarketingMenadzer(MarketingMenadzer mm) throws Exception{
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.KREIRAJ_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public void promeniMarketingMenadzer(MarketingMenadzer mm) throws Exception{
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.PROMENI_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public List<MarketingMenadzer> pretraziMarketingMenadzer(MarketingMenadzer uslovMM) throws Exception {
        List <MarketingMenadzer> menadzeri;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PRETRAZI_MARKETING_MENADZER, uslovMM);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        menadzeri=(List<MarketingMenadzer>) odg.getOdgovor();
        return menadzeri; 
    }

    public List<Sertifikat> vratiSveSertifikat() throws Exception {
        List <Sertifikat> sertifikati;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_SERTIFIKAT, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        sertifikati=(List<Sertifikat>) odg.getOdgovor();
        return sertifikati;
    }

    public void ubaciSertifikat(Sertifikat s) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public List<TipVizuala> vratiSveTipVizuala() throws Exception {
        List <TipVizuala> vizuali;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_TIP_VIZUALA, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        vizuali=(List<TipVizuala>) odg.getOdgovor();
        return vizuali;
    }

    public List<EvidencijaAngazmana> vratiSveEvidencijaAngazmana() throws Exception {
        List <EvidencijaAngazmana> evidencije;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_EVIDENCIJA_ANGAZMANA, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        evidencije=(List<EvidencijaAngazmana>) odg.getOdgovor();
        return evidencije;
    }

    public List<EvidencijaAngazmana> pretraziEvidencijaAngazmana(EvidencijaAngazmana uslovEA) throws Exception {
        List <EvidencijaAngazmana> evidencije;
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PRETRAZI_EVIDENCIJA_ANGAZMANA, uslovEA);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        evidencije=(List<EvidencijaAngazmana>) odg.getOdgovor();
        return evidencije;
    }


    public void kreirajEvidencijaAngazmana(EvidencijaAngazmana ea) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.KREIRAJ_EVIDENCIJA_ANGAZMANA, ea);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public void promeniEvidencijaAngazmana(EvidencijaAngazmana ea) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.PROMENI_EVIDENCIJA_ANGAZMANA, ea);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getException()!=null){
            throw so.getException();
        }
    }

    public EvidencijaAngazmana ucitajEvidencijaAngazmana(EvidencijaAngazmana ea) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.UCITAJ_EVIDENCIJA_ANGAZMANA, ea);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        return (EvidencijaAngazmana)odg.getOdgovor();
    }
    
    public MarketingMenadzer ucitajMarketingMenadzer(MarketingMenadzer mm) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.UCITAJ_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        if (odg.getException()!=null){
            throw odg.getException();
        }
        return (MarketingMenadzer)odg.getOdgovor();
    }

    
}
