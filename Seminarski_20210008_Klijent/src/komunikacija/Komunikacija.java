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
import model.StavkaAngazmana;
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

    public Dizajner login(String username, String password){
        
        
        Dizajner dizajner=new Dizajner();
        dizajner.setKorisnickoIme(username);
        dizajner.setSifra(password);
        System.out.println(dizajner);
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PRIJAVI_DIZAJNER,dizajner);
        
        try {
            posiljalac.posalji(zahtev);
        } catch (IOException ex) {
            System.out.println("Zahtev za prijavu ne može da se pošalje");
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(zahtev);
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        dizajner=(Dizajner) odg.getOdgovor();
        return dizajner;
        
    }

    public List<Dizajner> ucitajDizajnere() throws IOException {
        List <Dizajner> dizajneri=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_DIZAJNER, null);
        posiljalac.posalji(zahtev);
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        dizajneri=(List<Dizajner>) odg.getOdgovor();
        return dizajneri;     
    }

    public void obrisiDizajnera(Dizajner d) throws Exception {
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.OBRISI_DIZAJNER, d);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor)primalac.primi();
        if (odg.getOdgovor()!=null){
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška u brisanju dizajnera");
        }
    }

    public void kreirajDizajner(Dizajner d) throws Exception {
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.KREIRAJ_DIZAJNER, d);
        posiljalac.posalji(zahtev);//TODO
        ServerskiOdgovor odg=(ServerskiOdgovor)primalac.primi();
        if (odg.getOdgovor()!=null){
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška u kreiranju dizajnera");
        }
    }

    public void promeniDizajner(Dizajner d) throws Exception {
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PROMENI_DIZAJNER, d);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor)primalac.primi();
        if (odg.getOdgovor()!=null){
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("Greška u izmeni dizajnera");
        }
    }

    public List<Kompanija> ucitajKompanije() throws IOException {
        List <Kompanija> kompanije=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_KOMPANIJA, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        kompanije=(List<Kompanija>) odg.getOdgovor();
        return kompanije; 
    }

    public void kreirajKompanija(Kompanija k) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.KREIRAJ_KOMPANIJA, k);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u kreiranju kompanije");
        }
    }

    public void promeniKompanija(Kompanija k) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.PROMENI_KOMPANIJA, k);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if(so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u promeni kompanije");
        }
    }

    public void obrisiKompanija(Kompanija k) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.OBRISI_KOMPANIJA, k);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u brisanju kompanije");
        }
    }

    public List<MarketingMenadzer> ucitajMarketingMenadzere() throws IOException {
        List <MarketingMenadzer> menadzeri=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_MARKETING_MENADZER, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        menadzeri=(List<MarketingMenadzer>) odg.getOdgovor();
        return menadzeri; 
    }

    public void obrisiMarketingMenadzera(MarketingMenadzer mm)throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.OBRISI_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u brisanju marketing menazera");
        }
    }

    public void kreirajMarketingMenadzer(MarketingMenadzer mm) throws Exception{
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.KREIRAJ_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u kreiranju marketing menadžera");
        }
    }

    public void promeniMarketingMenadzer(MarketingMenadzer mm) throws Exception{
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.PROMENI_MARKETING_MENADZER, mm);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if(so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u promeni marketing menadžera");
        }
    }

    public List<MarketingMenadzer> pretraziMarketingMenadzer(MarketingMenadzer uslovMM) throws IOException {
        List <MarketingMenadzer> menadzeri=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PRETRAZI_MARKETING_MENADZER, uslovMM);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        menadzeri=(List<MarketingMenadzer>) odg.getOdgovor();
        return menadzeri; 
    }

    public List<Sertifikat> ucitajSertifikate() throws IOException {
        List <Sertifikat> sertifikati=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_SERTIFIKAT, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        sertifikati=(List<Sertifikat>) odg.getOdgovor();
        return sertifikati;
    }

    public void ubaciSertifikat(Sertifikat s) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.UBACI_SERTIFIKAT, s);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u ubacivanju sertifikata");
        }
    }

    public List<TipVizuala> ucitajVizuale() throws IOException {
        List <TipVizuala> vizuali=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_TIP_VIZUALA, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        vizuali=(List<TipVizuala>) odg.getOdgovor();
        return vizuali;
    }

    public List<EvidencijaAngazmana> ucitajEvidencijeAngazmana() throws IOException {
        List <EvidencijaAngazmana> evidencije=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.VRATI_LISTU_SVI_EVIDENCIJA_ANGAZMANA, null);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        evidencije=(List<EvidencijaAngazmana>) odg.getOdgovor();
        return evidencije;
    }

    public List<EvidencijaAngazmana> pretraziEvidencijaAngazmana(EvidencijaAngazmana uslovEA) throws IOException {
        List <EvidencijaAngazmana> evidencije=new ArrayList<>();
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.PRETRAZI_EVIDENCIJA_ANGAZMANA, uslovEA);
        posiljalac.posalji(zahtev);
        
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        evidencije=(List<EvidencijaAngazmana>) odg.getOdgovor();
        return evidencije;
    }


    public void kreirajEvidencijuAngazmana(EvidencijaAngazmana ea) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.KREIRAJ_EVIDENCIJA_ANGAZMANA, ea);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u kreiranju evidencije angažmana.");
        }
    }

    public void promeniEvidencijaAngazmana(EvidencijaAngazmana ea) throws Exception {
        KlijentskiZahtev kz=new KlijentskiZahtev(Operacija.PROMENI_EVIDENCIJA_ANGAZMANA, ea);
        posiljalac.posalji(kz);
        
        ServerskiOdgovor so=(ServerskiOdgovor) primalac.primi();
        if (so.getOdgovor()!=null){
            ((Exception)so.getOdgovor()).printStackTrace();
            throw new Exception("Greška u promeni evidencije angažmana.");
        }
    }

    
}
