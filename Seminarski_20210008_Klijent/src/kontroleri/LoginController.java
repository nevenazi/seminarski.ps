/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        Dizajner ulogovan=Komunikacija.getInstance().login(username,password);
        if (ulogovan==null){
            JOptionPane.showMessageDialog(lf, "Loše uneti kredencijal. Dizajner ne postoji u bazi. Pokušajte ponovo", "Loš unos", JOptionPane.WARNING_MESSAGE);
        } else {
            //glavnaforma
            JOptionPane.showMessageDialog(lf, "Prijava je uspešna. Ulovogan je dizajner "+ulogovan.getIme()+" "+ulogovan.getPrezime()+".", "Dobro došli!", JOptionPane.INFORMATION_MESSAGE);
            lf.dispose();
        }
    }
    
    public void otvoriFormu() {
        lf.setVisible(true);
    }
   
}
