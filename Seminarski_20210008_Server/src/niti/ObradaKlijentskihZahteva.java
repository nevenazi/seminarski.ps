/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.ServerskiOdgovor;
import model.Dizajner;

/**
 *
 * @author N
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac=new Posiljalac(socket);
        primalac=new Primalac(socket);
    }

    
    
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("run obrada klijentskih zahteva");
                KlijentskiZahtev zahtev = (KlijentskiZahtev)primalac.primi();
                if (zahtev==null){
                    continue;
                }
                ServerskiOdgovor odgovor=new ServerskiOdgovor();
                Dizajner d;
                switch(zahtev.getOperacija()){
                    case LOGIN:
                        d=(Dizajner)zahtev.getParametar();

                            d=Controller.getInstance().login(d);
                            odgovor.setOdgovor(d);

                        break;
                    case UCITAJDIZAJNERE:
                        List <Dizajner> dizajneri=Controller.getInstance().ucitajDizajnere();
                        odgovor.setOdgovor(dizajneri);
                        break;
                    case OBRISIDIZAJNER:
                        try{
                        d=(Dizajner) zahtev.getParametar();
                        Controller.getInstance().obrisiDizajner(d);
                        odgovor.setOdgovor(null);
                        }catch (Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case KREIRAJDIZAJNER:
                        try {
                            d = (Dizajner) zahtev.getParametar();
                            Controller.getInstance().kreirajDizajner(d);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }
                        break;
                    case PROMENIDIZAJNER:
                        try {
                            d = (Dizajner) zahtev.getParametar();
                            Controller.getInstance().promeniDizajner(d);
                            odgovor.setOdgovor(null);
                        } catch (Exception exception) {
                            odgovor.setOdgovor(exception);
                        }

                    default:System.out.println("Greška! Nepostojeća operacija je izabrana.");
                }

                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }
    
    
}
