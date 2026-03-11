/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.EvidencijaAngazmanaForma;
import forme.model.ModelTabeleEvidencijaAngazmana;
import forme.model.ModelTabeleMarketingMenadzer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
        eaf.addButtonObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=eaf.getjTableEvidencijaAngazmana().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidenciju angažmana.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleEvidencijaAngazmana mtea= (ModelTabeleEvidencijaAngazmana) eaf.getjTableEvidencijaAngazmana().getModel();
                EvidencijaAngazmana ea=mtea.getLista().get(red);
                JOptionPane.showMessageDialog(eaf, "Sistem je našao evidenciju angažmana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Komunikacija.getInstance().obrisiEvidencijaAngazmana(ea);
                    JOptionPane.showMessageDialog(eaf, "Sistem je obrisao evidenciju angažmana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da obriše evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
//        eaf.addButtonKreirajActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Koordinator.getInstance().otvoriKreirajMarketingMenadzerFormu();
//            }
//        });
        
//        eaf.addButtonPromeniActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int red=eaf.getjTableMarketingMenadzer().getSelectedRow();
//                if (red==-1) {
//                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe marketing menažera.", "Greška", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//                ModelTabeleMarketingMenadzer mtmm= (ModelTabeleMarketingMenadzer) eaf.getjTableMarketingMenadzer().getModel();
//                MarketingMenadzer mm=mtmm.getLista().get(red);
//                JOptionPane.showMessageDialog(eaf, "Sistem je našao marketing menažera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
//                
//                Koordinator.getInstance().dodajParametar("marketing menadžer", mm);
//                Koordinator.getInstance().otvoriPromeniMarketingMenadzerFormu();
//            }
//        });
        
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
        
//        eaf.addButtonPrikaziActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int red=eaf.getjTableMarketingMenadzer().getSelectedRow();
//                if (red==-1) {
//                    JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe marketing menadžera.", "Greška", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//                ModelTabeleMarketingMenadzer mtmm= (ModelTabeleMarketingMenadzer) eaf.getjTableMarketingMenadzer().getModel();
//                MarketingMenadzer mm=mtmm.getLista().get(red);
//                JOptionPane.showMessageDialog(eaf, "Sistem je našao marketing menažera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
//                
//                Koordinator.getInstance().dodajParametar("marketing menadžer", mm);
//                Koordinator.getInstance().otvoriPrikaziMarketingMenadzerFormu();
//            }
//        });
    }
    
    

    public void pripremiFormu() {
        
        //kompanije
        List<Kompanija> kompanije=new ArrayList<>();
        try {
            kompanije=Komunikacija.getInstance().ucitajKompanije();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe kompanije.","Greška", JOptionPane.ERROR_MESSAGE);
        }
        eaf.getjComboBoxKompanija().removeAllItems();
        eaf.getjComboBoxKompanija().addItem(null);
        for (Kompanija kompanija : kompanije) {
            eaf.getjComboBoxKompanija().addItem(kompanija);
        }
        
        //menadzeri
        List<MarketingMenadzer> menadzeri=new ArrayList<>();
        try {
            menadzeri = Komunikacija.getInstance().ucitajMarketingMenadzere();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe marketing menadžere.","Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        eaf.getjComboBoxMarketingMenadzer().removeAllItems();
        eaf.getjComboBoxMarketingMenadzer().addItem(null);
        for (MarketingMenadzer menadzer: menadzeri) {
            eaf.getjComboBoxMarketingMenadzer().addItem(menadzer);
        }
        
        //dizajneri
        List<Dizajner> dizajneri=new ArrayList<>();
        try {
            dizajneri = Komunikacija.getInstance().ucitajDizajnere();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe dizajnere.","Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(EvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        eaf.getjComboBoxDizajner().removeAllItems();
        eaf.getjComboBoxDizajner().addItem(null);
        for (Dizajner dizajner: dizajneri) {
            eaf.getjComboBoxDizajner().addItem(dizajner);
        }
        
        //evidencije tabela
        List<EvidencijaAngazmana> evidencije=new ArrayList<>();
        try {
            evidencije = Komunikacija.getInstance().ucitajEvidencijeAngazmana();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(eaf, "Sistem ne može da nađe evidencije angažmana.","Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(MMenadzerFormaController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        ModelTabeleEvidencijaAngazmana mtea=new ModelTabeleEvidencijaAngazmana(evidencije);
        eaf.getjTableEvidencijaAngazmana().setModel(mtea);
        JOptionPane.showMessageDialog(eaf, "Sistem je našao evidencije angažmana.","Uspeh", JOptionPane.INFORMATION_MESSAGE);
        
        //otvaranje forme
        Koordinator.getInstance().zatvoriGlavnuFormu();
        eaf.setVisible(true);
    }

    public EvidencijaAngazmanaForma getEaf() {
        return eaf;
    }

    

    
    
    
}
