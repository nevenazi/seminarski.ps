/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.MarketingMenadzerForma;
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
import model.Kompanija;
import model.MarketingMenadzer;

/**
 *
 * @author N
 */
public class MMenadzerFormaController {
    private final MarketingMenadzerForma mmf;

    public MMenadzerFormaController(MarketingMenadzerForma mmf) {
        this.mmf = mmf;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        //df.setVisible(true);
    }

    private void addActionListeners() {
        mmf.addButtonObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=mmf.getjTableMarketingMenadzer().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(mmf, "Sistem ne može da nađe marketing menažera.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleMarketingMenadzer mtmm= (ModelTabeleMarketingMenadzer) mmf.getjTableMarketingMenadzer().getModel();
                MarketingMenadzer mm=mtmm.getLista().get(red);
                JOptionPane.showMessageDialog(mmf, "Sistem je našao marketing menažera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Komunikacija.getInstance().obrisiMarketingMenadzera(mm);
                    JOptionPane.showMessageDialog(mmf, "Sistem je obrisao marketing menažera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mmf, "Sistem ne može da obriše marketing menažera.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mmf.addButtonKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriKreirajMarketingMenadzerFormu();
            }
        });
        
        mmf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=mmf.getjTableMarketingMenadzer().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(mmf, "Sistem ne može da nađe marketing menažera.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleMarketingMenadzer mtmm= (ModelTabeleMarketingMenadzer) mmf.getjTableMarketingMenadzer().getModel();
                MarketingMenadzer mm=mtmm.getLista().get(red);
                JOptionPane.showMessageDialog(mmf, "Sistem je našao marketing menažera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("marketing menadžer", mm);
                Koordinator.getInstance().otvoriPromeniMarketingMenadzerFormu();
            }
        });
        
        mmf.addButtonPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime=mmf.getjTextFieldIme().getText().trim();
                String prezime=mmf.getjTextFieldPrezime().getText().trim();
                Kompanija kompanija=(Kompanija) mmf.getjComboBox1().getSelectedItem();
                
                MarketingMenadzer uslovMM=new MarketingMenadzer();
                if (kompanija!=null){
                    uslovMM.setKompanija(kompanija);
                }
                List<MarketingMenadzer> menadzeri=new ArrayList<>();
                        
                try {
                    menadzeri=filtriraj(Komunikacija.getInstance().pretraziMarketingMenadzer(uslovMM),ime,prezime);
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mmf, "Sistem ne može da nađe marketing menadžere po zadatim kriterijumima","Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(MMenadzerFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ModelTabeleMarketingMenadzer mtmm=new ModelTabeleMarketingMenadzer(menadzeri);
                mmf.getjTableMarketingMenadzer().setModel(mtmm);
                JOptionPane.showMessageDialog(mmf, "Sistem je našao marketing menadžere po zadatim kriterijumima","Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        mmf.addButtonResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
        
        mmf.addButtonGFActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriGlavnuFormu();
                mmf.dispose();
            }
        });
        
        mmf.addButtonPrikaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=mmf.getjTableMarketingMenadzer().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(mmf, "Sistem ne može da nađe marketing menadžera.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleMarketingMenadzer mtmm= (ModelTabeleMarketingMenadzer) mmf.getjTableMarketingMenadzer().getModel();
                MarketingMenadzer mm=mtmm.getLista().get(red);
                JOptionPane.showMessageDialog(mmf, "Sistem je našao marketing menažera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("marketing menadžer", mm);
                Koordinator.getInstance().otvoriPrikaziMarketingMenadzerFormu();
            }
        });
    }
    
    public List<MarketingMenadzer> filtriraj(List<MarketingMenadzer> menadzeri, String ime, String prezime){
    
    List<MarketingMenadzer> filtriranaLista = menadzeri.stream()
    .filter(m -> (ime == null || ime.isEmpty() || m.getIme().toLowerCase().contains(ime.toLowerCase())))
    .filter(m -> (prezime == null || prezime.isEmpty() || m.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
    .collect(Collectors.toList());
    System.out.println(ime+prezime+filtriranaLista.size());
    return filtriranaLista;

        
    }

    public void pripremiFormu() {
        mmf.getjTextFieldIme().setText("");
        mmf.getjTextFieldPrezime().setText("");
        List<Kompanija> kompanije=new ArrayList<>();
        
        try {
            kompanije=Komunikacija.getInstance().ucitajKompanije();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mmf, "Sistem ne može da nađe kompanije.","Greška", JOptionPane.ERROR_MESSAGE);
        }
        mmf.getjComboBox1().removeAllItems();
        mmf.getjComboBox1().addItem(null);
        for (Kompanija kompanija : kompanije) {
            mmf.getjComboBox1().addItem(kompanija);
        }
        List<MarketingMenadzer> menadzeri=new ArrayList<>();
        try {
            menadzeri = Komunikacija.getInstance().ucitajMarketingMenadzere();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mmf, "Sistem ne može da nađe marketing menadžere.","Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(MMenadzerFormaController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        ModelTabeleMarketingMenadzer mtmm=new ModelTabeleMarketingMenadzer(menadzeri);
        mmf.getjTableMarketingMenadzer().setModel(mtmm);
        JOptionPane.showMessageDialog(mmf, "Sistem je našao marketing menadžere","Uspeh", JOptionPane.INFORMATION_MESSAGE);
        Koordinator.getInstance().zatvoriGlavnuFormu();
        mmf.setVisible(true);
    }

    public MarketingMenadzerForma getMmf() {
        return mmf;
    }

    

    
    
    
}
