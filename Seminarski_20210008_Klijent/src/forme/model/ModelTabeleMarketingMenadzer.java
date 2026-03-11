/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.MarketingMenadzer;

/**
 *
 * @author N
 */
public class ModelTabeleMarketingMenadzer extends AbstractTableModel {

    List<MarketingMenadzer> lista;
    String[] kolone={"id","ime","prezime","telefon","email","kompanija"};
    
    public ModelTabeleMarketingMenadzer(List<MarketingMenadzer> mm){
        lista=mm;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MarketingMenadzer m=lista.get(rowIndex);
        switch (columnIndex){
            case 0:return m.getIdMarketingMenadzer();
            case 1: return m.getIme();
            case 2: return m.getPrezime();
            case 3: return m.getTelefon();
            case 4: return m.getEmail();
            case 5: return m.getKompanija().getNaziv();
            default:return "N/A";
        }
        
        
        
    }

    public List<MarketingMenadzer> getLista() {
        return lista;
    }
    
}
