/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    
    
    
    public void zaustaviServer(){
        kraj=true;
        try {
            serverSoket.close();
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
            
            while (!kraj) {
                System.out.println("krenuo je run u PokreniServer");
                Socket s = serverSoket.accept();
                System.out.println("Klijent je povezan");
                //ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                //okz.start();
            }
        } catch (IOException iOException) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, iOException);
        }
        
        
    }
    
    
}
