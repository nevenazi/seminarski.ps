/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.DizajnerForma;
import forme.EvidencijaAngazmanaForma;
import forme.GlavnaForma;
import forme.KompanijaForma;
import forme.KreirajDizajnerForma;
import forme.KreirajEvidencijaAngazmanaForma;
import forme.KreirajMarketingMenadzerForma;
import forme.LoginForma;
import forme.MarketingMenadzerForma;
import forme.SertifikatForma;
import forme.TipVizualaForma;
import forme.VrstaForme;
import java.util.HashMap;
import java.util.Map;
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
    private Map<String, Object> parametri;
    private KompanijaFormaController kompanijaFormaController;
    private KreirajKompanijaFormaController kreirajKompanijaFormaController;
    private MMenadzerFormaController mmenadzerFormaController;
    private KreirajMMenadzerFormaController kreirajMMenadzerFormaController;
    private SertifikatFormaController sertifikatFormaController;
    private UbaciSertifikatFormaController ubaciSertifikatFormaController;
    private TipVizualaFormaController tipVizualaFormaController;
    private EvidencijaAngazmanaFormaController evidencijaAngazmanaFormaController;
    private KreirajEvidencijaAngazmanaFormaController kreirajEvidencijaAngazmanaFormaController;
    
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

    public KompanijaFormaController getKompanijaFormaController() {
        return kompanijaFormaController;
    }

    public void setKompanijaFormaController(KompanijaFormaController kompanijaFormaController) {
        this.kompanijaFormaController = kompanijaFormaController;
    }

    public KreirajKompanijaFormaController getKreirajKompanijaFormaController() {
        return kreirajKompanijaFormaController;
    }

    public MMenadzerFormaController getMmenadzerFormaController() {
        return mmenadzerFormaController;
    }

    public KreirajMMenadzerFormaController getKreirajMMenadzerFormaController() {
        return kreirajMMenadzerFormaController;
    }

    public TipVizualaFormaController getTipVizualaFormaController() {
        return tipVizualaFormaController;
    }

    public EvidencijaAngazmanaFormaController getEvidencijaAngazmanaFormaController() {
        return evidencijaAngazmanaFormaController;
    }

    public KreirajEvidencijaAngazmanaFormaController getKreirajEvidencijaAngazmanaFormaController() {
        return kreirajEvidencijaAngazmanaFormaController;
    }

    
    public void setKreirajKompanijaFormaController(KreirajKompanijaFormaController kreirajKompanijaFormaController) {
        this.kreirajKompanijaFormaController = kreirajKompanijaFormaController;
    }
    
    

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public SertifikatFormaController getSertifikatFormaController() {
        return sertifikatFormaController;
    }

    public UbaciSertifikatFormaController getUbaciSertifikatFormaController() {
        return ubaciSertifikatFormaController;
    }
    

    private Koordinator() {
        parametri=new HashMap<>();
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
        kreirajDizajnerFormaController.otvoriFormu(VrstaForme.KREIRAJ);
    }

    public void dodajParametar(String s, Object o){
        parametri.put(s,o);
    }
    public Object vratiParametar(String s){
        return parametri.get(s);
    }

    void otvoriPromeniDizajnerFormu() {
        kreirajDizajnerFormaController=new KreirajDizajnerFormaController(new KreirajDizajnerForma());
        kreirajDizajnerFormaController.otvoriFormu(VrstaForme.PROMENI);
    }

    public void otvoriKompanijaFormu() {
        kompanijaFormaController=new KompanijaFormaController(new KompanijaForma());
        kompanijaFormaController.otvoriFormu();
    }

    void otvoriKreirajKompanijaFormu() {
        kreirajKompanijaFormaController=new KreirajKompanijaFormaController();
        kreirajKompanijaFormaController.otvoriFormu(VrstaForme.KREIRAJ);
    }

    void otvoriPromeniKompanijaFormu() {
        kreirajKompanijaFormaController=new KreirajKompanijaFormaController();
        kreirajKompanijaFormaController.otvoriFormu(VrstaForme.PROMENI);
    }
    
    void zatvoriGlavnuFormu(){
        glavnaFormaController.zatvoriFormu();
    }

    void otvoriPrikaziDizajnerFormu() {
        kreirajDizajnerFormaController=new KreirajDizajnerFormaController(new KreirajDizajnerForma());
        kreirajDizajnerFormaController.otvoriFormu(VrstaForme.PRIKAZI);
    }

    void otvoriPrikaziKompanijaFormu() {
        kreirajKompanijaFormaController=new KreirajKompanijaFormaController();
        kreirajKompanijaFormaController.otvoriFormu(VrstaForme.PRIKAZI);
    }

    public void otvoriMarketingMenadzerFormu() {
        mmenadzerFormaController=new MMenadzerFormaController(new MarketingMenadzerForma());
        mmenadzerFormaController.otvoriFormu();
    }
    
    public void otvoriKreirajMarketingMenadzerFormu() {
        kreirajMMenadzerFormaController=new KreirajMMenadzerFormaController(new KreirajMarketingMenadzerForma());
        kreirajMMenadzerFormaController.otvoriFormu(VrstaForme.KREIRAJ);
    }
    
    public void otvoriPromeniMarketingMenadzerFormu() {
        kreirajMMenadzerFormaController=new KreirajMMenadzerFormaController(new KreirajMarketingMenadzerForma());
        kreirajMMenadzerFormaController.otvoriFormu(VrstaForme.PROMENI);
    }
    
    public void otvoriPrikaziMarketingMenadzerFormu() {
        kreirajMMenadzerFormaController=new KreirajMMenadzerFormaController(new KreirajMarketingMenadzerForma());
        kreirajMMenadzerFormaController.otvoriFormu(VrstaForme.PRIKAZI);
    }

    void otvoriUbaciSertifikatFormu() {
        ubaciSertifikatFormaController=new UbaciSertifikatFormaController();
        ubaciSertifikatFormaController.otvoriFormu();
        
    }

    public void otvoriSertifikatFormu() {
        sertifikatFormaController=new SertifikatFormaController(new SertifikatForma());
        sertifikatFormaController.otvoriFormu();
    }

    public void otvoriTipVizualaFormu() {
        tipVizualaFormaController=new TipVizualaFormaController(new TipVizualaForma());
        tipVizualaFormaController.otvoriFormu();
    }

    public void otvoriEvidencijaAngazmanaFormu() {
        evidencijaAngazmanaFormaController=new EvidencijaAngazmanaFormaController(new EvidencijaAngazmanaForma());
        evidencijaAngazmanaFormaController.otvoriFormu();
    }

    void otvoriKreirajEvidencijaAngazmanaFormu() {
        kreirajEvidencijaAngazmanaFormaController=new KreirajEvidencijaAngazmanaFormaController(new KreirajEvidencijaAngazmanaForma());
        kreirajEvidencijaAngazmanaFormaController.otvoriFormu(VrstaForme.KREIRAJ);
    }
    
    void otvoriPromeniEvidencijaAngazmanaFormu() {
        kreirajEvidencijaAngazmanaFormaController=new KreirajEvidencijaAngazmanaFormaController(new KreirajEvidencijaAngazmanaForma());
        kreirajEvidencijaAngazmanaFormaController.otvoriFormu(VrstaForme.PROMENI);
    }

    void otvoriPrikaziEvidencijaAngazmanaFormu() {
        kreirajEvidencijaAngazmanaFormaController=new KreirajEvidencijaAngazmanaFormaController(new KreirajEvidencijaAngazmanaForma());
        kreirajEvidencijaAngazmanaFormaController.otvoriFormu(VrstaForme.PRIKAZI);
    }

    
    
}
