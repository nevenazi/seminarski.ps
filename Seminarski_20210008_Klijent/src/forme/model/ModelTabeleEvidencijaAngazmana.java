/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.EvidencijaAngazmana;

/**
 *
 * @author N
 */
public class ModelTabeleEvidencijaAngazmana extends AbstractTableModel {

    List<EvidencijaAngazmana> lista;
    String[] kolone={"id","ukupan iznos","rok","zavrsen","dizajner","marketing menadzer","kompanija"};
    
    public ModelTabeleEvidencijaAngazmana(List<EvidencijaAngazmana> evidencije){
        lista=evidencije;
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
        EvidencijaAngazmana e=lista.get(rowIndex);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex){
            case 0: return e.getIdEvidencijaAngazmana();
            case 1: return e.getUkupanIznos();
            case 2: return sdf.format(e.getRok());
            case 3: return (e.isZavrsen()==true) ? "DA":"NE";
            case 4: return e.getDizajner().getIme()+" "+e.getDizajner().getPrezime();
            case 5: return e.getMarketingMenadzer().getIme()+" "+e.getMarketingMenadzer().getPrezime();
            case 6: return e.getMarketingMenadzer().getKompanija().getNaziv();
            default:return "N/A";
        }
        
        
        
    }

    public List<EvidencijaAngazmana> getLista() {
        return lista;
    }
    
}
