/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.net.Socket;
import komunikacija.KlijentskiZahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.ServerskiOdgovor;

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
            
            KlijentskiZahtev zahtev = (KlijentskiZahtev)primalac.primi();
            ServerskiOdgovor odgovor=new ServerskiOdgovor();
            switch(zahtev.getOperacija()){
                //case
                default:System.out.println("Greška! Nepostojeća operacija je izabrana.");
            }
            
            posiljalac.posalji(odgovor);
            
        }
    }
    
    
}
