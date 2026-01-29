/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajDizajnerForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Dizajner;

/**
 *
 * @author N
 */
public class KreirajDizajnerFormaController {
    
    public final KreirajDizajnerForma kdf;

    public KreirajDizajnerFormaController(KreirajDizajnerForma kdf) {
        this.kdf = kdf;
    }
    public void otvoriFormu(){
        kdf.setVisible(true);
        addActionListeners();
    }
    public void addActionListeners(){
        kdf.addButtonSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ćććććććććććććććććććć");
                String ime=kdf.getjTextFieldIme().getText().trim();
                String prezime=kdf.getjTextFieldPrezime().getText().trim();
                String korisnickoIme=kdf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra=String.valueOf(kdf.getjPasswordField().getPassword()).trim();
                Dizajner d=new Dizajner();
                
                d.setIme(ime);
                d.setPrezime(prezime);
                d.setKorisnickoIme(korisnickoIme);
                d.setSifra(sifra);
                try {
                    Komunikacija.getInstance().kreirajDizajner(d);
                    JOptionPane.showMessageDialog(kdf, "Sistem je zapamtio dizajnera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getDizajnerFormaController().pripremiFormu();
                    kdf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kdf, "Sistem ne može da zapamti dizajnera.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
