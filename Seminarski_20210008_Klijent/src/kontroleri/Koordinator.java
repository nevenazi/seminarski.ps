/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DizajnerForma;
import forme.GlavnaForma;
import forme.KreirajDizajnerForma;
import forme.LoginForma;
import java.util.logging.Logger;
import model.Dizajner;

/**
 *
 * @author N
 */
public class Koordinator {
    private static Koordinator instance;
    private Dizajner ulogovaniKorisnik;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private DizajnerFormaController dizajnerFormaController;
    private KreirajDizajnerFormaController kreirajDizajnerFormaController;

    public LoginController getLoginController() {
        return loginController;
    }

    public GlavnaFormaController getGlavnaFormaController() {
        return glavnaFormaController;
    }

    public DizajnerFormaController getDizajnerFormaController() {
        return dizajnerFormaController;
    }

    public KreirajDizajnerFormaController getKreirajDizajnerFormaController() {
        return kreirajDizajnerFormaController;
    }
    
    

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    

    private Koordinator() {
        
    }
    
    public static Koordinator getInstance(){
        if (instance==null){
            instance=new Koordinator();
        } return instance;
    }

    public void otvoriLoginFormu() {
        loginController=new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    void otvoriGlavnuFormu() {
        glavnaFormaController=new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public Dizajner getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(Dizajner ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

    public void otvoriDizajnerFormu() {
        dizajnerFormaController=new DizajnerFormaController(new DizajnerForma());
        dizajnerFormaController.otvoriFormu();
    }
    
    public void otvoriKreirajDizajnerFormu(){
        kreirajDizajnerFormaController=new KreirajDizajnerFormaController(new KreirajDizajnerForma());
        kreirajDizajnerFormaController.otvoriFormu();
    }

    

    
    
}
