/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sertifikatOperacije;

import model.Sertifikat;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class UbaciSertifikatSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof Sertifikat)){
            throw new Exception("Sistem ne može da zapamti sertifikat.");
        }
        
        Sertifikat s=(Sertifikat) param;
        if(s.getNaziv()==null || s.getNaziv().isEmpty() || s.getInstitucija()==null || s.getInstitucija().isEmpty()){
            throw new Exception("Greška u unosu podataka o sertifikatu.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Sertifikat)param);
    }
    
}
