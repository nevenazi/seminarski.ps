/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.ServerskiOdgovor;
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
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac=new Posiljalac(socket);
        primalac=new Primalac(socket);
        kraj=false;
    }

    
    
    @Override
    public void run() {
        while(!kraj){
            try {
                System.out.println("run obrada klijentskih zahteva");
                KlijentskiZahtev zahtev = (KlijentskiZahtev)primalac.primi();
                if (zahtev==null){
                    continue;
                }
                ServerskiOdgovor odgovor=new ServerskiOdgovor();
                Dizajner d;
                Kompanija k;
                MarketingMenadzer mm;
                EvidencijaAngazmana evidencija;
                switch(zahtev.getOperacija()){
                    case PRIJAVI_DIZAJNER:
                        d=(Dizajner)zahtev.getParametar();

                            d=Controller.getInstance().prijaviDizajner(d);
                            odgovor.setOdgovor(d);

                        break;
                    case VRATI_LISTU_SVI_DIZAJNER:
                        List <Dizajner> dizajneri=Controller.getInstance().vratiListuSviDizajner();
                        odgovor.setOdgovor(dizajneri);
                        break;
                    case OBRISI_DIZAJNER:
                        try{
                        d=(Dizajner) zahtev.getParametar();
                        Controller.getInstance().obrisiDizajner(d);
                        odgovor.setOdgovor(null);
                        }catch (Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case KREIRAJ_DIZAJNER:
                        try {
                            d = (Dizajner) zahtev.getParametar();
                            Controller.getInstance().kreirajDizajner(d);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }
                        break;
                    case PROMENI_DIZAJNER:
                        try {
                            d = (Dizajner) zahtev.getParametar();
                            Controller.getInstance().promeniDizajner(d);
                            System.out.println(d.toString()+"okz");
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            odgovor.setOdgovor(exception);
                        }break;
                    case VRATI_LISTU_SVI_KOMPANIJA:
                        List<Kompanija> kompanije=Controller.getInstance().vratiListuSviKompanije();
                        odgovor.setOdgovor(kompanije);
                        break;
                    case KREIRAJ_KOMPANIJA:
                        try {
                            k = (Kompanija) zahtev.getParametar();
                            Controller.getInstance().kreirajKompanija(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }break;
                    case PROMENI_KOMPANIJA:
                        try {
                            k = (Kompanija) zahtev.getParametar();
                            Controller.getInstance().promeniKompanija(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case OBRISI_KOMPANIJA:
                        try {
                            k = (Kompanija) zahtev.getParametar();
                            Controller.getInstance().obrisiKompanija(k);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case VRATI_LISTU_SVI_MARKETING_MENADZER:
                        List<MarketingMenadzer> menadzeri=Controller.getInstance().vratiListuSviMarketingMenadzer();
                        odgovor.setOdgovor(menadzeri);
                        break;
                    case OBRISI_MARKETING_MENADZER:
                        try {
                            mm = (MarketingMenadzer) zahtev.getParametar();
                            Controller.getInstance().obrisiMarketingMenadzer(mm);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case KREIRAJ_MARKETING_MENADZER:
                        try {
                            mm = (MarketingMenadzer) zahtev.getParametar();
                            Controller.getInstance().kreirajMarketingMenadzer(mm);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }break;
                    case PROMENI_MARKETING_MENADZER:
                        try {
                            mm = (MarketingMenadzer) zahtev.getParametar();
                            Controller.getInstance().promeniMarketingMenadzer(mm);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case PRETRAZI_MARKETING_MENADZER:
                        mm=(MarketingMenadzer) zahtev.getParametar();
                        List<MarketingMenadzer> filtriraniMenadzeri=Controller.getInstance().pretraziMarketingMenadzer(mm);
                        odgovor.setOdgovor(filtriraniMenadzeri);
                        break;
                    case VRATI_LISTU_SVI_SERTIFIKAT:
                        List<Sertifikat> sertifikati=Controller.getInstance().vratiListuSviSertifikat();
                        odgovor.setOdgovor(sertifikati);
                        break;
                    case UBACI_SERTIFIKAT:
                        try {
                            Sertifikat s = (Sertifikat) zahtev.getParametar();
                            Controller.getInstance().ubaciSertifikat(s);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }break;
                    case VRATI_LISTU_SVI_TIP_VIZUALA:
                        List<TipVizuala> vizuali=Controller.getInstance().vratiListuSviTipVizuala();
                        odgovor.setOdgovor(vizuali);
                        break;
                    case VRATI_LISTU_SVI_EVIDENCIJA_ANGAZMANA:
                        List<EvidencijaAngazmana> evidencije=Controller.getInstance().vratiListuSviEvidencijaAngazmana();
                        odgovor.setOdgovor(evidencije);
                        break;
                    case PRETRAZI_EVIDENCIJA_ANGAZMANA:
                        evidencija=(EvidencijaAngazmana) zahtev.getParametar();
                        List<EvidencijaAngazmana> filtriraneEvidencije=Controller.getInstance().pretraziEvidencijaAngazmana(evidencija);
                        odgovor.setOdgovor(filtriraneEvidencije);
                        break;
                    case KREIRAJ_EVIDENCIJA_ANGAZMANA:
                        try {
                            evidencija = (EvidencijaAngazmana) zahtev.getParametar();
                            Controller.getInstance().kreirajEvidencijaAngazmana(evidencija);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }break;
                    case PROMENI_EVIDENCIJA_ANGAZMANA:
                        try {
                            evidencija = (EvidencijaAngazmana) zahtev.getParametar();
                            Controller.getInstance().promeniEvidencijaAngazmana(evidencija);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }break;
                    

                    default:System.out.println("Greška! Nepostojeća operacija je izabrana.");
                }

                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }

    public void prekini() {
        kraj=true;
        if (socket!=null && !socket.isClosed()) try {
            socket.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
