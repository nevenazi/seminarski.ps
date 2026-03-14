/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajDizajnerForma;
import forme.VrstaForme;
import static forme.VrstaForme.PROMENI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Dizajner;

/**
 *
 * @author N
 */
public class KreirajDizajnerFormaController {
    
    public final KreirajDizajnerForma kdf;
    private Dizajner dizajnerForme;

    public KreirajDizajnerFormaController(KreirajDizajnerForma kdf) {
        this.kdf = kdf;
    }
    public void otvoriFormu(VrstaForme vrstaForme){
        kdf.setVisible(true);
       
        switch (vrstaForme){
            case KREIRAJ:
                kdf.setTitle("Kreiraj dizajnera");
                kdf.getjButtonPromeni().setVisible(false);
                kdf.getjButtonSacuvaj().setVisible(true);
                kdf.getjButtonSacuvaj().setEnabled(true);
                dizajnerForme=new Dizajner();
                JOptionPane.showMessageDialog(kdf, "Sistem je kreirao dizajnera", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                break;
            case PROMENI:
                kdf.setTitle("Promeni dizajnera");
                kdf.getjButtonSacuvaj().setVisible(false);
                kdf.getjButtonPromeni().setVisible(true);
                kdf.getjButtonPromeni().setEnabled(true);
                dizajnerForme=(Dizajner)Koordinator.getInstance().vratiParametar("dizajner");
                popuniFormu(dizajnerForme);
                break;
            case PRIKAZI:
                kdf.setTitle("Prikaži dizajnera");
                kdf.getjButtonSacuvaj().setVisible(false);
                kdf.getjButtonPromeni().setVisible(false);
                dizajnerForme=(Dizajner)Koordinator.getInstance().vratiParametar("dizajner");
                popuniFormu(dizajnerForme);
                onemoguciPoljaForme();
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
                    String ispis="Sistem je zapamtio dizajnera:\n"+"ime: "+d.getIme()+"\nprezime: "+d.getPrezime()+"\nkorisničko ime: "+d.getKorisnickoIme();
                    JOptionPane.showMessageDialog(kdf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
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
                
                    Dizajner d=pokupiDizajnera();
                    d.setIdDizajner(dizajnerForme.getIdDizajner());
                    
                try {    
                    Komunikacija.getInstance().promeniDizajner(d);
                    String ispis="Sistem je zapamtio dizajnera:\n"+"ime: "+d.getIme()+"\nprezime: "+d.getPrezime()+"\nkorisničko ime: "+d.getKorisnickoIme();
                    JOptionPane.showMessageDialog(kdf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getDizajnerFormaController().pripremiFormu();
                    kdf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kdf, "Sistem ne može da zapamti dizajnera.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        kdf.addButtonZatvoriActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kdf.dispose();
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
        if (Koordinator.getInstance().getUlogovaniKorisnik().equals(dizajner)){
            kdf.getjPasswordField().setVisible(true);
            kdf.getjLabelSifra().setVisible(true);
        }else {
            kdf.getjPasswordField().setVisible(false);
            kdf.getjLabelSifra().setVisible(false);
        }
    }

    private void onemoguciPoljaForme() {
        kdf.getjTextFieldIme().setEditable(false);
        kdf.getjTextFieldPrezime().setEditable(false);
        kdf.getjTextFieldKorisnickoIme().setEditable(false);
        kdf.getjPasswordField().setVisible(false);
        kdf.getjLabelSifra().setVisible(false);
    }


}
