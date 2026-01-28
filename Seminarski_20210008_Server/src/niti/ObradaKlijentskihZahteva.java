/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.net.Socket;
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
                ServerskiOdgovor odgovor=new ServerskiOdgovor();
                switch(zahtev.getOperacija()){
                    case LOGIN:
                        System.out.println("usao u login");
                        Dizajner d=(Dizajner)zahtev.getParametar();

                            d=Controller.getInstance().login(d);
                            odgovor.setOdgovor(d);

                        break;

                    default:System.out.println("Greška! Nepostojeća operacija je izabrana.");
                }

                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }
    
    
}
