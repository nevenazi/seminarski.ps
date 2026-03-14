/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dizajnerOperacije;

import java.util.List;
import model.Dizajner;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class VratiListuSviDizajnerSO extends ApstraktnaGenerickaOperacija {

    List<Dizajner> dizajneri;

    public List<Dizajner> getDizajneri() {
        return dizajneri;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof Dizajner)){
            throw new Exception("Sistem ne može da nađe dizajnere po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        dizajneri=broker.getAll(param, null);
    }
    
}
