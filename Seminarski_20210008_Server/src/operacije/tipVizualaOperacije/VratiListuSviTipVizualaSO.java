/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.tipVizualaOperacije;


import java.util.List;
import model.TipVizuala;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author N
 */
public class VratiListuSviTipVizualaSO extends ApstraktnaGenerickaOperacija {

    List<TipVizuala> vizuali;

    public List<TipVizuala> getVizuali() {
        return vizuali;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof TipVizuala)){
            throw new Exception("Sistem ne može da nađe tipove vizuala po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        vizuali=broker.getAll(param, null);
    }
    
}
