/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.SertifikatForma;
import forme.model.ModelTabeleSertifikat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;
import model.Sertifikat;

/**
 *
 * @author N
 */
public class SertifikatFormaController {
    private final SertifikatForma sf;

    public SertifikatFormaController(SertifikatForma sf) {
        this.sf = sf;
        addActionListeners();
    }

    public SertifikatForma getSf() {
        return sf;
    }

    void otvoriFormu() {
        pripremiFormu();    
    }

    private void addActionListeners() {
        sf.addButtonUbaciActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriUbaciSertifikatFormu();
            }
        });
        
       
        
        sf.addButtonGFActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriGlavnuFormu();
                sf.dispose();
            }
        });
        
    }
    

    public void pripremiFormu() {
        
        List<Sertifikat> sertifikati=new ArrayList<>();
        try {
            sertifikati = Komunikacija.getInstance().vratiSveSertifikat();
        } catch (Exception ex) {
            Logger.getLogger(SertifikatFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelTabeleSertifikat mts=new ModelTabeleSertifikat(sertifikati);
        sf.getjTableSertifikat().setModel(mts);
        sf.setVisible(true);
        Koordinator.getInstance().zatvoriGlavnuFormu();
    }
    
    
}
