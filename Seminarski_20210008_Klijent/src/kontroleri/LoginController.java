/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Dizajner;

/**
 *
 * @author N
 */
public class LoginController {

    
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }
        });
    }
    
    private void prijava(ActionEvent e){
  
        String username=lf.getjTextFieldUsername().getText();
        String password=String.valueOf(lf.getjPasswordField().getPassword());
        
        Komunikacija.getInstance().konekcija();
        Dizajner ulogovan;
        try {
            ulogovan = Komunikacija.getInstance().prijaviDizajner(username,password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Loš unos", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        if (ulogovan==null){
            JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "Loš unos", JOptionPane.WARNING_MESSAGE);
            return;
        }
                
        
        JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "Dobro došli!", JOptionPane.INFORMATION_MESSAGE);
            
            
        Koordinator.getInstance().setUlogovaniKorisnik(ulogovan);
        Koordinator.getInstance().otvoriGlavnuFormu();
            
            lf.dispose();
        
    }
    
    public void otvoriFormu() {
        lf.setVisible(true);
    }
   
}
