/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author N
 */
public class PokreniServer extends Thread {
    boolean kraj=false;
    ServerSocket serverSoket;
    List<ObradaKlijentskihZahteva> klijenti;

    public PokreniServer() {
        klijenti=new ArrayList<>();
    }
    
    
    
    public void zaustaviServer(){
        kraj=true;
        try {
            for (ObradaKlijentskihZahteva obradaKlijentskihZahteva : klijenti) {
                obradaKlijentskihZahteva.prekini();
            }
            serverSoket.close();
            interrupt();
            System.out.println("cao");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("caocao");
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
                serverSoket = new ServerSocket(9000);
                System.out.println(serverSoket);
                while (!kraj) {
                    System.out.println("krenuo je run u PokreniServer");
                    Socket s = serverSoket.accept();
                    System.out.println("Klijent je povezan");
                    ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                    klijenti.add(okz);
                    okz.start();
                }
        }catch (IOException iOException) {
                    if (!kraj){
                        Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, iOException);
                    }
       
        }  
    }
    
    
}
