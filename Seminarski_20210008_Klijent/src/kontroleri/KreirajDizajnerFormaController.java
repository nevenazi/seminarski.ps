/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajDizajnerForma;
import forme.VrstaForme;
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
    public void otvoriFormu(VrstaForme vrstaForme){
        kdf.setVisible(true);
       
        switch (vrstaForme){
            case KREIRAJ:
                kdf.setTitle("Kreiraj dizajnera");
                kdf.getjButtonPromeni().setVisible(false);
                kdf.getjButtonKreiraj().setVisible(true);
                kdf.getjButtonKreiraj().setEnabled(true);

                break;
            case PROMENI:
                kdf.setTitle("Promeni dizajnera");
                kdf.getjButtonKreiraj().setVisible(false);
                kdf.getjButtonPromeni().setVisible(true);
                kdf.getjButtonPromeni().setEnabled(true);
                popuniFormu((Dizajner)Koordinator.getInstance().vratiParametar("dizajner"));
                break;
            default:
                JOptionPane.showMessageDialog(kdf, "Nije izabrana vrsta forme", "Greška", JOptionPane.ERROR_MESSAGE);
                kdf.dispose();
                
        }
        
        addActionListeners();
    }
    public void addActionListeners(){
        kdf.addButtonSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Dizajner d=pokupiDizajnera();
                
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
        
        kdf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    int id=((Dizajner)Koordinator.getInstance().vratiParametar("dizajner")).getIdDizajner();
                    Dizajner d=pokupiDizajnera();
                    d.setIdDizajner(id);
                try {    
                    Komunikacija.getInstance().promeniDizajner(d);
                    JOptionPane.showMessageDialog(kdf, "Sistem je zapamtio dizajnera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getDizajnerFormaController().pripremiFormu();
                    kdf.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(KreirajDizajnerFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    
    public Dizajner pokupiDizajnera(){
        
        String ime=kdf.getjTextFieldIme().getText().trim();
        String prezime=kdf.getjTextFieldPrezime().getText().trim();
        String korisnickoIme=kdf.getjTextFieldKorisnickoIme().getText().trim();
        String sifra=String.valueOf(kdf.getjPasswordField().getPassword()).trim();
        Dizajner d=new Dizajner();
                
        d.setIme(ime);
        d.setPrezime(prezime);
        d.setKorisnickoIme(korisnickoIme);
        d.setSifra(sifra);
        
        return d;
                
    }

    private void popuniFormu(Dizajner dizajner) {
        kdf.getjTextFieldIme().setText(dizajner.getIme());
        kdf.getjTextFieldPrezime().setText(dizajner.getPrezime());
        kdf.getjTextFieldKorisnickoIme().setText(dizajner.getKorisnickoIme());
        kdf.getjPasswordField().setText(dizajner.getSifra());
    }


}
