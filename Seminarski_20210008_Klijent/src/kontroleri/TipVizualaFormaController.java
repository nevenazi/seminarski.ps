/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;


import forme.TipVizualaForma;
import forme.model.ModelTabeleTipVizuala;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.TipVizuala;

/**
 *
 * @author N
 */
public class TipVizualaFormaController {
    private final TipVizualaForma tvf;

    public TipVizualaFormaController(TipVizualaForma tvf) {
        this.tvf = tvf;
        addActionListeners();
    }

    public TipVizualaForma getTvf() {
        return tvf;
    }

    void otvoriFormu() {
        pripremiFormu();    
    }

    private void addActionListeners() {
               
       
        
        tvf.addButtonGFActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriGlavnuFormu();
                tvf.dispose();
            }
        });
        
    }
    

    public void pripremiFormu() {
        
        List<TipVizuala> tipovivizuala=new ArrayList<>();
        try {
            tipovivizuala = Komunikacija.getInstance().ucitajVizuale();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tvf, "Sistem ne može da nađe tipove vizuala.","Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(tvf, "Sistem je našao tipove vizuala.","Uspeh", JOptionPane.INFORMATION_MESSAGE);
        ModelTabeleTipVizuala mttv=new ModelTabeleTipVizuala(tipovivizuala);
        tvf.getjTableTipVizuala().setModel(mttv);
        tvf.setVisible(true);
        Koordinator.getInstance().zatvoriGlavnuFormu();
    }
    
    
}
