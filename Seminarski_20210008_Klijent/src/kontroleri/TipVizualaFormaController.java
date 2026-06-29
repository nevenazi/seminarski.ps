/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;


import forme.TipVizualaForma;
import forme.model.ModelTabeleTipVizuala;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            tipovivizuala = Komunikacija.getInstance().vratiSveTipVizuala();
        } catch (Exception ex) {
            Logger.getLogger(TipVizualaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelTabeleTipVizuala mttv=new ModelTabeleTipVizuala(tipovivizuala);
        tvf.getjTableTipVizuala().setModel(mttv);
        tvf.setVisible(true);
        Koordinator.getInstance().zatvoriGlavnuFormu();
    }
    
    
}
