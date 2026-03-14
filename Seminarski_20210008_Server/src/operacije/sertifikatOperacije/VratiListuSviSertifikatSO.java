/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.sertifikatOperacije;

import java.util.List;
import model.Sertifikat;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class VratiListuSviSertifikatSO extends ApstraktnaGenerickaOperacija {

    List<Sertifikat> sertifikati;

    public List<Sertifikat> getSertifikati() {
        return sertifikati;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof Sertifikat)){
            throw new Exception("Sistem ne može da nađe sertifikate po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        sertifikati=broker.getAll(param, null);
    }
    
}
