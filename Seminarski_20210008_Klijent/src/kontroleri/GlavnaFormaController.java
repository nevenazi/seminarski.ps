/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;

/**
 *
 * @author N
 */
public class GlavnaFormaController {

    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
    }
    
    
    public void otvoriFormu() {
        gf.setVisible(true);
        String ulogovani=Koordinator.getInstance().getUlogovaniKorisnik().getIme()+" "+Koordinator.getInstance().getUlogovaniKorisnik().getPrezime();
        gf.getjLabelUlogovan().setText(ulogovani);
    }

    

    void zatvoriFormu() {
        gf.dispose();
    }
    
}
