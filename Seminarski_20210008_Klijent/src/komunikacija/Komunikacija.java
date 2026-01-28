/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dizajner;

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
        
    }

    public Dizajner login(String username, String password) {
        
        
        Dizajner dizajner=new Dizajner();
        dizajner.setKorisnickoIme(username);
        dizajner.setSifra(password);
        System.out.println(dizajner);
        KlijentskiZahtev zahtev=new KlijentskiZahtev(Operacija.LOGIN,dizajner);
        
        posiljalac.posalji(zahtev);
        System.out.println(zahtev);
        ServerskiOdgovor odg=(ServerskiOdgovor) primalac.primi();
        //dizajner=(Dizajner) odg.getOdgovor();
        //return dizajner;
        return null;
    }
    
}
