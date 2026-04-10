/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KompanijaForma;
import forme.model.ModelTabeleKompanija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kompanija;

/**
 *
 * @author N
 */
public class KompanijaFormaController {
    private final KompanijaForma kf;

    public KompanijaFormaController(KompanijaForma kf) {
        this.kf = kf;
        addActionListeners();
    }

    public KompanijaForma getKf() {
        return kf;
    }

    void otvoriFormu() {
        pripremiFormu();    
    }

    private void addActionListeners() {
        kf.addButtonKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriKreirajKompanijaFormu();
            }
        });
        
        kf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=kf.getjTableKompanija().getSelectedRow();
                if (red==-1){
                    JOptionPane.showMessageDialog(kf, "Nije izabrana kompanija za izmenu", "Greška", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKompanija mtk=(ModelTabeleKompanija) kf.getjTableKompanija().getModel();
                    Kompanija k=mtk.getLista().get(red);
                    JOptionPane.showMessageDialog(kf, "Sistem je našao kompaniju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().dodajParametar("kompanija", k);
                    
                    Koordinator.getInstance().otvoriPromeniKompanijaFormu();
                }
            }
        }
        );
        
        kf.addButtonPrikaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=kf.getjTableKompanija().getSelectedRow();
                if (red==-1){
                    JOptionPane.showMessageDialog(kf, "Sistem ne može da nađe kompaniju", "Greška", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKompanija mtk=(ModelTabeleKompanija) kf.getjTableKompanija().getModel();
                    Kompanija k=mtk.getLista().get(red);
                    JOptionPane.showMessageDialog(kf, "Sistem je našao kompaniju.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().dodajParametar("kompanija", k);
                    
                    Koordinator.getInstance().otvoriPrikaziKompanijaFormu();
                }
            }
        }
        );
        
        kf.addButtonObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=kf.getjTableKompanija().getSelectedRow();
                if (red==-1){
                    JOptionPane.showMessageDialog(kf, "Sistem ne može da nađe kompaniju.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleKompanija mtk=(ModelTabeleKompanija) kf.getjTableKompanija().getModel();
                Kompanija k=mtk.getLista().get(red);
                String poruka="Sistem je našao kompaniju:\nnaziv: "+k.getNaziv()+"\nsajt: "+k.getSajt()
                        +"\n\nDa li ste sigurni da želite da izvršite brisanje?\n\n";
                String[] opcije=new String[2];
                opcije[0]="Obriši";
                opcije[1]="Odustani";
                int potvrda=JOptionPane.showOptionDialog(kf, poruka, "Potvrda", 1, JOptionPane.WARNING_MESSAGE,null,opcije,opcije[1]);
                if (potvrda==0){
                        try {
                        Komunikacija.getInstance().obrisiKompanija(k);
                        JOptionPane.showMessageDialog(kf, "Sistem je obrisao kompaniju", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(kf, "Sistem ne može da obriše kompaniju", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        );
        
        kf.addButtonPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv=kf.getjTextFieldNaziv().getText().trim();
                String sajt=kf.getjTextFieldSajt().getText().trim();
                List<Kompanija> kompanije=new ArrayList<>();
                try {
                    kompanije=filtriraj(Komunikacija.getInstance().ucitajKompanije(),naziv,sajt);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(kf, "Sistem ne može da nađe kompanije po zadatim kriterijumima","Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(kf, "Sistem je našao kompanije po zadatim kriterijumima","Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   
                ModelTabeleKompanija mtd=new ModelTabeleKompanija(kompanije);
                kf.getjTableKompanija().setModel(mtd);
            }
        }
        );
        
        kf.addButtonResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        }
        );
        
        kf.addButtonGFActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriGlavnuFormu();
                kf.dispose();
            }
        });
        
    }
    
    public List<Kompanija> filtriraj(List<Kompanija> kompanije,String naziv,String sajt){
        List<Kompanija> filtriranaLista=kompanije.stream()
                .filter(k->(naziv==null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(k->(sajt==null || sajt.isEmpty() || k.getSajt().toLowerCase().contains(sajt.toLowerCase())))
                .collect(Collectors.toList());
        
        return filtriranaLista;
    }

    public void pripremiFormu() {
        kf.getjTextFieldNaziv().setText("");
        kf.getjTextFieldSajt().setText("");
        List<Kompanija> kompanije=new ArrayList<>();
        try {
            kompanije = Komunikacija.getInstance().ucitajKompanije();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(kf, "Sistem ne može da nađe kompanije.","Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ModelTabeleKompanija mtd=new ModelTabeleKompanija(kompanije);
        kf.getjTableKompanija().setModel(mtd);
        kf.setVisible(true);
        Koordinator.getInstance().zatvoriGlavnuFormu();
    }
    
    
}
