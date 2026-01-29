/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DizajnerForma;
import forme.model.ModelTabeleDizajner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        df.setVisible(true);
    }

    private void addActionListeners() {
        df.addButtonObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=df.getjTableDizajner().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(df, "Nije odabran dizajner za brisanje. Pokušajte ponovo.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ModelTabeleDizajner mtd= (ModelTabeleDizajner) df.getjTableDizajner().getModel();
                Dizajner d=mtd.getLista().get(red);
                try {
                    Komunikacija.getInstance().obrisiDizajnera(d);
                    JOptionPane.showMessageDialog(df, "Sistem je obrisao dizajnera.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(df, "Sistem ne može da obriše dizajnera.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        df.addButtonKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriKreirajDizajnerFormu();
            }
        });
    }

    public void pripremiFormu() {
        List<Dizajner> dizajneri=Komunikacija.getInstance().ucitajDizajnere();
        ModelTabeleDizajner mtd=new ModelTabeleDizajner(dizajneri);
        df.getjTableDizajner().setModel(mtd);
    }

    public DizajnerForma getDf() {
        return df;
    }

    
    
    
}
