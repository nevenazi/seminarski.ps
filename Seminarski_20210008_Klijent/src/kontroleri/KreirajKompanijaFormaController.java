/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajKompanijaForma;
import forme.VrstaForme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kompanija;

/**
 *
 * @author N
 */
public class KreirajKompanijaFormaController {
    
    private final KreirajKompanijaForma kkf;

    public KreirajKompanijaFormaController() {
        this.kkf = new KreirajKompanijaForma();
        
    }

    void otvoriFormu(VrstaForme vrstaForme) {
        kkf.setVisible(true);
        
        switch (vrstaForme){
            case KREIRAJ:
                kkf.getjButtonKreiraj().setVisible(true);
                kkf.getjButtonKreiraj().setEnabled(true);
                kkf.getjButtonPromeni().setVisible(false);
                kkf.setTitle("Kreiraj kompaniju");
                break;
            case PROMENI:
                popuniFormu((Kompanija) Koordinator.getInstance().vratiParametar("kompanija"));
                kkf.getjButtonKreiraj().setVisible(false);
                kkf.getjButtonPromeni().setVisible(true);
                kkf.getjButtonPromeni().setEnabled(true);
                kkf.setTitle("Promeni kompaniju");
                break;
            case PRIKAZI:
                popuniFormu((Kompanija) Koordinator.getInstance().vratiParametar("kompanija"));
                kkf.getjButtonKreiraj().setVisible(false);
                kkf.getjButtonPromeni().setVisible(false);
                kkf.getjTextFieldNaziv().setEditable(false);
                kkf.getjTextFieldSajt().setEditable(false);
                kkf.setTitle("Prikaži kompaniju");
                break;
            default: 
                JOptionPane.showMessageDialog(kkf, "Nije izabrana vrsta forme", "Greška", JOptionPane.ERROR_MESSAGE);
                kkf.dispose();
        }
        
        addActionListeners();
    }

    private void popuniFormu(Kompanija k) {
        kkf.getjTextFieldNaziv().setText(k.getNaziv());
        kkf.getjTextFieldSajt().setText(k.getSajt());
    }

    private void addActionListeners() {
        kkf.addButtonKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv=kkf.getjTextFieldNaziv().getText().trim();
                String sajt=kkf.getjTextFieldSajt().getText().trim();
                
                Kompanija k=new Kompanija();
                k.setNaziv(naziv);
                k.setSajt(sajt);
                
                try {
                    Komunikacija.getInstance().kreirajKompanija(k);
                    String ispis="Sistem je zapamtio kompaniju:\nnaziv: "+k.getNaziv()+"\nsajt: "+k.getSajt();
                    JOptionPane.showMessageDialog(kkf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getKompanijaFormaController().pripremiFormu();
                    kkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kkf, "Sistem ne može da zapamti kompaniju.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        kkf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=((Kompanija)Koordinator.getInstance().vratiParametar("kompanija")).getIdKompanija();
                String naziv=kkf.getjTextFieldNaziv().getText().trim();
                String sajt=kkf.getjTextFieldSajt().getText().trim();
                
                Kompanija k=new Kompanija(id, naziv, sajt);
                
                try {
                    Komunikacija.getInstance().promeniKompanija(k);
                    String ispis="Sistem je zapamtio kompaniju:\nnaziv: "+k.getNaziv()+"\nsajt: "+k.getSajt();
                    JOptionPane.showMessageDialog(kkf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getKompanijaFormaController().pripremiFormu();
                    kkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kkf, "Sistem ne može da zapamti kompaniju.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        kkf.addButtonZatvoriActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kkf.dispose();
            }
        });
        
    }
    
    
}
