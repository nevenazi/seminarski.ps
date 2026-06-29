/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.UbaciSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Sertifikat;

/**
 *
 * @author N
 */
public class UbaciSertifikatFormaController {
    
    private final UbaciSertifikatForma usf;
    

    public UbaciSertifikatFormaController() {
        this.usf = new UbaciSertifikatForma();
        
    }

    void otvoriFormu() {
        usf.setVisible(true);
        usf.getjButtonSacuvaj().setVisible(true);
        usf.getjButtonSacuvaj().setEnabled(true);
               
        addActionListeners();
    }

    

    private void addActionListeners() {
        usf.addButtonSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv=usf.getjTextFieldNaziv().getText().trim();
                String institucija=usf.getjTextFieldInstitucija().getText().trim();
                
                Sertifikat s=new Sertifikat();
                s.setNaziv(naziv);
                s.setInstitucija(institucija);
                
                try {
                    Komunikacija.getInstance().ubaciSertifikat(s);
                    String ispis="Sistem je zapamtio sertifikat:\nnaziv: "+s.getNaziv()+"\ninstitucija: "+s.getInstitucija();
                    JOptionPane.showMessageDialog(usf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().getSertifikatFormaController().pripremiFormu();
                    usf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(usf, "Sistem ne može da zapamti sertifikat.", "Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(UbaciSertifikatFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        
        usf.addButtonZatvoriActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usf.dispose();
            }
        });
        
    }
    
    
}
