/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DizajnerForma;
import forme.model.ModelTabeleDizajner;
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

/**
 *
 * @author N
 */
public class DizajnerFormaController {
    private final DizajnerForma df;

    public DizajnerFormaController(DizajnerForma df) {
        this.df = df;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        //df.setVisible(true);
    }

    private void addActionListeners() {
        df.addButtonObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=df.getjTableDizajner().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(df, "Sistem ne može da nađe dizajnera.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleDizajner mtd= (ModelTabeleDizajner) df.getjTableDizajner().getModel();
                Dizajner d=mtd.getLista().get(red);
                String poruka="Sistem je našao dizajnera:\n"+"ime: "+d.getIme()+"\nprezime: "+d.getPrezime()+"\nkorisničko ime: "+d.getKorisnickoIme()
                        +"\n\nDa li ste sigurni da želite da izvršite brisanje?\n\n";
                String[] opcije=new String[2];
                opcije[0]="Obriši";
                opcije[1]="Odustani";
                int potvrda=JOptionPane.showOptionDialog(df, poruka, "Potvrda", 1, JOptionPane.WARNING_MESSAGE,null,opcije,opcije[1]);
                if (potvrda==0){
                    try {
                        Komunikacija.getInstance().obrisiDizajnera(d);
                        JOptionPane.showMessageDialog(df, "Sistem je obrisao dizajnera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(df, "Sistem ne može da obriše dizajnera.", "Greška", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        df.addButtonKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriKreirajDizajnerFormu();
            }
        });
        
        df.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=df.getjTableDizajner().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(df, "Sistem ne može da nađe dizajnera.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleDizajner mtd= (ModelTabeleDizajner) df.getjTableDizajner().getModel();
                Dizajner d=mtd.getLista().get(red);
                JOptionPane.showMessageDialog(df, "Sistem je našao dizajnera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                
                Koordinator.getInstance().dodajParametar("dizajner", d);
                Koordinator.getInstance().otvoriPromeniDizajnerFormu();
            }
        });
        
        df.addButtonPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime=df.getjTextFieldIme().getText().trim();
                String prezime=df.getjTextFieldImePrezime().getText().trim();
                String korisnickoIme=df.getjTextFieldKorisnickoIme().getText().trim();
                
                List<Dizajner> dizajneri=new ArrayList<>();
                        
                try {
                    dizajneri=filtriraj(Komunikacija.getInstance().ucitajDizajnere(),ime,prezime,korisnickoIme);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(df, "Sistem ne može da nađe dizajnere po zadatim kriterijumima","Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(DizajnerFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ModelTabeleDizajner mtd=new ModelTabeleDizajner(dizajneri);
                df.getjTableDizajner().setModel(mtd);
                JOptionPane.showMessageDialog(df, "Sistem je našao dizajnere po zadatim kriterijumima","Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        df.addButtonResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
        
        df.addButtonGFActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriGlavnuFormu();
                df.dispose();
            }
        });
        
        df.addButtonPrikaziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=df.getjTableDizajner().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(df, "Sistem ne može da nađe dizajnera.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleDizajner mtd= (ModelTabeleDizajner) df.getjTableDizajner().getModel();
                Dizajner d=mtd.getLista().get(red);
                JOptionPane.showMessageDialog(df, "Sistem je našao dizajnera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                Koordinator.getInstance().dodajParametar("dizajner", d);
                Koordinator.getInstance().otvoriPrikaziDizajnerFormu();
            }
        });
    }
    
    public List<Dizajner> filtriraj(List<Dizajner> dizajneri, String ime, String prezime, String korisnickoIme){
        
    List<Dizajner> filtriranaLista = dizajneri.stream()
    .filter(d -> (ime == null || ime.isEmpty() || d.getIme().toLowerCase().contains(ime.toLowerCase())))
    .filter(d -> (prezime == null || prezime.isEmpty() || d.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
    .filter(d -> (korisnickoIme == null || korisnickoIme.isEmpty() || d.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())))
    .collect(Collectors.toList());
    
    return filtriranaLista;

        
    }

    public void pripremiFormu() {
        df.getjTextFieldIme().setText("");
        df.getjTextFieldImePrezime().setText("");
        df.getjTextFieldKorisnickoIme().setText("");
        List<Dizajner> dizajneri=new ArrayList<>();
        try {
            dizajneri = Komunikacija.getInstance().ucitajDizajnere();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(df, "Sistem ne može da nađe dizajnere.","Greška", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DizajnerFormaController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        ModelTabeleDizajner mtd=new ModelTabeleDizajner(dizajneri);
        df.getjTableDizajner().setModel(mtd);
        Koordinator.getInstance().zatvoriGlavnuFormu();
        df.setVisible(true);
    }

    public DizajnerForma getDf() {
        return df;
    }

    
    
    
}
