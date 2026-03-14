/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.EvidencijaAngazmanaForma;
import forme.model.ModelTabeleEvidencijaAngazmana;
import forme.model.ModelTabeleMarketingMenadzer;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
                JOptionPane.showMessageDialog(eaf, "Sistem je našao evidenciju angažmana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("evidencija angažmana", ea);
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
                JOptionPane.showMessageDialog(eaf, "Sistem je našao evidenciju angažmana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("evidencija angažmana", ea);
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

    /*public void prikaziEvidenciju(MouseEvent evt) {
        JTable table=(JTable) evt.getSource();
        Point point=evt.getPoint();
        int red=table.rowAtPoint(point);
        EvidencijaAngazmana ea=((ModelTabeleEvidencijaAngazmana)eaf.getjTableEvidencijaAngazmana().getModel()).getLista().get(red);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd.MM.yyyy");
        String ispis="Sistem je našao evidenciju angažmana:\n"+"dizajner: "+ea.getDizajner().toString()
                            +"\nmarketing menadžer: "+ea.getMarketingMenadzer().toString()+"\nrok: "+simpleDateFormat.format(ea.getRok())
                            +"\nukupan iznos: "+ea.getUkupanIznos()+"\nzavršen: "+ea.isZavrsen();
                    JOptionPane.showMessageDialog(eaf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
    }*/

    

    
    
    
}
