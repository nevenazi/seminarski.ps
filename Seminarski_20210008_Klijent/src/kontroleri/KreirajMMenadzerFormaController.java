/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajMarketingMenadzerForma;
import forme.VrstaForme;
import static forme.VrstaForme.PROMENI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kompanija;
import model.MarketingMenadzer;

/**
 *
 * @author N
 */
public class KreirajMMenadzerFormaController {
    
    public final KreirajMarketingMenadzerForma kmmf;
    private MarketingMenadzer mmenadzerForme;

    public KreirajMMenadzerFormaController(KreirajMarketingMenadzerForma kmmf) {
        this.kmmf = kmmf;
    }
    public void otvoriFormu(VrstaForme vrstaForme){
        kmmf.setVisible(true);
        popuniComboBoxKompanija();
        switch (vrstaForme){
            case KREIRAJ:
                kmmf.setTitle("Kreiraj marketing menadžera");
                kmmf.getjButtonPromeni().setVisible(false);
                kmmf.getjButtonSacuvaj().setVisible(true);
                kmmf.getjButtonSacuvaj().setEnabled(true);
                mmenadzerForme=new MarketingMenadzer();
                break;
            case PROMENI:
                kmmf.setTitle("Promeni marketing menadžera");
                kmmf.getjButtonSacuvaj().setVisible(false);
                kmmf.getjButtonPromeni().setVisible(true);
                kmmf.getjButtonPromeni().setEnabled(true);
                mmenadzerForme=(MarketingMenadzer)Koordinator.getInstance().vratiParametar("marketing menadžer");
                popuniFormu(mmenadzerForme);
                break;
            case PRIKAZI:
                kmmf.setTitle("Prikaži marketing menadžera");
                kmmf.getjButtonSacuvaj().setVisible(false);
                kmmf.getjButtonPromeni().setVisible(false);
                mmenadzerForme=(MarketingMenadzer)Koordinator.getInstance().vratiParametar("marketing menadžer");
                popuniFormu(mmenadzerForme);
                onemoguciPoljaForme();
                break;
            default:
                JOptionPane.showMessageDialog(kmmf, "Nije izabrana vrsta forme", "Greška", JOptionPane.ERROR_MESSAGE);
                kmmf.dispose();
                
        }
        
        addActionListeners();
    }
    public void addActionListeners(){
        kmmf.addButtonSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MarketingMenadzer mm=pokupiMarketingMenadzera();
                
                try {
                    Komunikacija.getInstance().kreirajMarketingMenadzer(mm);
                    String ispis="Sistem je kreirao marketing menadžera:\n"+"ime: "+mm.getIme()+"\nprezime: "+mm.getPrezime()+"\nemail: "+mm.getEmail()+"\ntelefon: "+mm.getTelefon()+"\nkompanija: "+mm.getKompanija().getNaziv();
                    JOptionPane.showMessageDialog(kmmf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getMmenadzerFormaController().pripremiFormu();
                    kmmf.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(KreirajMMenadzerFormaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(kmmf, "Sistem ne može da zapamti marketing menadžera.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        kmmf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    MarketingMenadzer mm=pokupiMarketingMenadzera();
                    mm.setIdMarketingMenadzer(mmenadzerForme.getIdMarketingMenadzer());
                try {    
                    Komunikacija.getInstance().promeniMarketingMenadzer(mm);
                    String ispis="Sistem je zapamtio marketing menadžera:\n"+"ime: "+mm.getIme()+"\nprezime: "+mm.getPrezime()+"\nemail: "+mm.getEmail()+"\ntelefon: "+mm.getTelefon()+"\nkompanija: "+mm.getKompanija().getNaziv();
                    JOptionPane.showMessageDialog(kmmf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getMmenadzerFormaController().pripremiFormu();
                    kmmf.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(KreirajMMenadzerFormaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(kmmf, "Sistem ne može da zapamti marketing menadžera.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        
        kmmf.addButtonZatvoriActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kmmf.dispose();
            }
        });
        
    }
    
    public MarketingMenadzer pokupiMarketingMenadzera(){
        
        String ime=kmmf.getjTextFieldIme().getText().trim();
        String prezime=kmmf.getjTextFieldPrezime().getText().trim();
        String email=kmmf.getjTextFieldEmail().getText().trim();
        String telefon=kmmf.getjTextFieldTelefon().getText().trim();
        Kompanija kompanija=(Kompanija) kmmf.getjComboBoxKompanija().getSelectedItem();
        
        MarketingMenadzer m=new MarketingMenadzer(-1, ime, prezime, telefon, email, kompanija);
        
        return m;
                
    }

    private void popuniFormu(model.MarketingMenadzer menadzer) {
        kmmf.getjTextFieldIme().setText(menadzer.getIme());
        kmmf.getjTextFieldPrezime().setText(menadzer.getPrezime());
        kmmf.getjTextFieldEmail().setText(menadzer.getEmail());
        kmmf.getjTextFieldTelefon().setText(menadzer.getTelefon());
        kmmf.getjComboBoxKompanija().setSelectedItem(menadzer.getKompanija());
        
    }

    private void onemoguciPoljaForme() {
        kmmf.getjTextFieldIme().setEditable(false);
        kmmf.getjTextFieldPrezime().setEditable(false);
        kmmf.getjTextFieldEmail().setEditable(false);
        kmmf.getjTextFieldTelefon().setEditable(false);
        kmmf.getjComboBoxKompanija().setEnabled(false);
        
    }

    private void popuniComboBoxKompanija() {
        List<Kompanija> kompanije=new ArrayList<>();
        try {
            kompanije=Komunikacija.getInstance().vratiSveKompanija();
        } catch (Exception ex) {
            Logger.getLogger(KreirajMMenadzerFormaController.class.getName())
                    .log(Level.SEVERE,"Neispunjeni preduslovi. Sistem ne može da nađe kompanije.", ex);
            Koordinator.getInstance().otvoriMarketingMenadzerFormu();
            kmmf.dispose();
        }
        kmmf.getjComboBoxKompanija().removeAllItems();
        for (Kompanija kompanija : kompanije) {
            kmmf.getjComboBoxKompanija().addItem(kompanija);
        }
    }


}
