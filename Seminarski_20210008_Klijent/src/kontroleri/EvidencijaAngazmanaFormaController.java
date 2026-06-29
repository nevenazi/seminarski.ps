/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.EvidencijaAngazmanaForma;
import forme.model.ModelTabeleEvidencijaAngazmana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Dizajner;
import model.EvidencijaAngazmana;
import model.Kompanija;
import model.MarketingMenadzer;

/**
 *
 * @author N
 */
public class EvidencijaAngazmanaFormaController {
    private final EvidencijaAngazmanaForma eaf;

    public EvidencijaAngazmanaFormaController(EvidencijaAngazmanaForma eaf) {
        this.eaf = eaf;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
    }

    private void addActionListeners() {
        
        eaf.addButtonKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriKreirajEvidencijaAngazmanaFormu();
            }
        });
        
        eaf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=eaf.getjTableEvidencijaAngazmana().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidenciju angažmana.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleEvidencijaAngazmana mtea= (ModelTabeleEvidencijaAngazmana) eaf.getjTableEvidencijaAngazmana().getModel();
                EvidencijaAngazmana ea=mtea.getLista().get(red);
                //ucitavanje evidencije iz baze koja je izabrana
                EvidencijaAngazmana ucitanaEvidencija;
                try {
                    ucitanaEvidencija=Komunikacija.getInstance().ucitajEvidencijaAngazmana(ea);
                } catch (Exception ex) {
                    Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(eaf, "Sistem je našao evidenciju angažmana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("evidencija angažmana", ucitanaEvidencija);
                Koordinator.getInstance().otvoriPromeniEvidencijaAngazmanaFormu();
            }
        });
        
        eaf.addButtonPrikaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=eaf.getjTableEvidencijaAngazmana().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleEvidencijaAngazmana mtea= (ModelTabeleEvidencijaAngazmana) eaf.getjTableEvidencijaAngazmana().getModel();
                EvidencijaAngazmana ea=mtea.getLista().get(red);
                //ucitavanje evidencije iz baze koja je izabrana
                EvidencijaAngazmana ucitanaEvidencija;
                try {
                    ucitanaEvidencija=Komunikacija.getInstance().ucitajEvidencijaAngazmana(ea);
                } catch (Exception ex) {
                    Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(eaf, "Sistem je našao evidenciju angažmana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("evidencija angažmana", ucitanaEvidencija);
                Koordinator.getInstance().otvoriPrikaziEvidencijaAngazmanaFormu();
            }
        });
        
        eaf.addButtonPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dizajner dizajner=(Dizajner) eaf.getjComboBoxDizajner().getSelectedItem();
                MarketingMenadzer menadzer=(MarketingMenadzer) eaf.getjComboBoxMarketingMenadzer().getSelectedItem();
                Kompanija kompanija=(Kompanija) eaf.getjComboBoxKompanija().getSelectedItem();
                
                //pravimo evidenciju koja se koristi za poredjenje odnosno za uslov kod pretrazivanja
                EvidencijaAngazmana uslovEA=new EvidencijaAngazmana();
                if (dizajner!=null){
                    uslovEA.setDizajner(dizajner);
                }
                if (menadzer!=null){
                    uslovEA.setMarketingMenadzer(menadzer);
                }else uslovEA.setMarketingMenadzer(new MarketingMenadzer());
                if (kompanija!=null){
                    uslovEA.getMarketingMenadzer().setKompanija(kompanija);
                }
                
                List<EvidencijaAngazmana> evidencije=new ArrayList<>();
                try {
                    evidencije=Komunikacija.getInstance().pretraziEvidencijaAngazmana(uslovEA);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidencije angažmana po zadatim kriterijumima","Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ModelTabeleEvidencijaAngazmana mtea=new ModelTabeleEvidencijaAngazmana(evidencije);
                eaf.getjTableEvidencijaAngazmana().setModel(mtea);
                JOptionPane.showMessageDialog(eaf, "Sistem je našao evidencije angažmana po zadatim kriterijumima","Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        eaf.addButtonResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
        
        eaf.addButtonGlavnaFormaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriGlavnuFormu();
                eaf.dispose();
            }
        });
        
    }
    
    

    public void pripremiFormu() {
        
        //kompanije
        List<Kompanija> kompanije=new ArrayList<>();
        try {
            kompanije=Komunikacija.getInstance().vratiSveKompanija();
        } catch (Exception ex) {
            Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eaf.getjComboBoxKompanija().removeAllItems();
        eaf.getjComboBoxKompanija().addItem(null);
        for (Kompanija kompanija : kompanije) {
            eaf.getjComboBoxKompanija().addItem(kompanija);
        }
        
        //menadzeri
        List<MarketingMenadzer> menadzeri=new ArrayList<>();
        try {
            menadzeri = Komunikacija.getInstance().vratiSveMarketingMenadzer();
        } catch (Exception ex) {
            Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eaf.getjComboBoxMarketingMenadzer().removeAllItems();
        eaf.getjComboBoxMarketingMenadzer().addItem(null);
        for (MarketingMenadzer menadzer: menadzeri) {
            eaf.getjComboBoxMarketingMenadzer().addItem(menadzer);
        }
        
        //dizajneri
        List<Dizajner> dizajneri=new ArrayList<>();
        try {
            dizajneri = Komunikacija.getInstance().vratiSveDizajner();
        } catch (Exception ex) {
            Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eaf.getjComboBoxDizajner().removeAllItems();
        eaf.getjComboBoxDizajner().addItem(null);
        for (Dizajner dizajner: dizajneri) {
            eaf.getjComboBoxDizajner().addItem(dizajner);
        }
        
        //evidencije tabela
        List<EvidencijaAngazmana> evidencije=new ArrayList<>();
        try {
            evidencije = Komunikacija.getInstance().vratiSveEvidencijaAngazmana();
        } catch (Exception ex) {
            Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelTabeleEvidencijaAngazmana mtea=new ModelTabeleEvidencijaAngazmana(evidencije);
        eaf.getjTableEvidencijaAngazmana().setModel(mtea);
        
        //otvaranje forme
        Koordinator.getInstance().zatvoriGlavnuFormu();
        eaf.setVisible(true);
    }

    public EvidencijaAngazmanaForma getEaf() {
        return eaf;
    }

    

    

    
    
    
}
