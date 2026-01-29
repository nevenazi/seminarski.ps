/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author N
 */
public class Primalac {
    
    private Socket socket;

    public Primalac(Socket socket) {
        this.socket = socket;
    }
    
    public Object primi(){
        try {
                if (socket == null || socket.isClosed()) {
                    System.out.println("Socket je zatvoren.");
                    return null;
                }
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                return in.readObject();
            } catch (java.net.SocketException e) {
                System.out.println("Klijent više nije povezan.");
                try {
                        if (!socket.isClosed()) {
                        socket.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (Exception ex) {
                Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
}
