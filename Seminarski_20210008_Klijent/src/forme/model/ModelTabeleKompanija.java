/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kompanija;

/**
 *
 * @author N
 */
public class ModelTabeleKompanija extends AbstractTableModel {

    List<Kompanija> lista;
    String[] kolone={"idKompanija","naziv","sajt"};
    
    public ModelTabeleKompanija(List<Kompanija> kompanije){
        lista=kompanije;
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
        Kompanija k=lista.get(rowIndex);
        switch (columnIndex){
            case 0: return k.getIdKompanija();
            case 1: return k.getNaziv();
            case 2: return k.getSajt();
            default:return "N/A";
        }
        
        
        
    }

    public List<Kompanija> getLista() {
        return lista;
    }
    
}
