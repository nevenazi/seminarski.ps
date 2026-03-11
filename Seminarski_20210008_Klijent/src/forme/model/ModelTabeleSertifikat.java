/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Sertifikat;

/**
 *
 * @author N
 */
public class ModelTabeleSertifikat extends AbstractTableModel {

    List<Sertifikat> lista;
    String[] kolone={"id","naziv","institucija"};
    
    public ModelTabeleSertifikat(List<Sertifikat> sertifikati){
        lista=sertifikati;
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
        Sertifikat s=lista.get(rowIndex);
        switch (columnIndex){
            case 0: return s.getIdSertifikat();
            case 1: return s.getNaziv();
            case 2: return s.getInstitucija();
            default:return "N/A";
        }
        
        
        
    }

    public List<Sertifikat> getLista() {
        return lista;
    }
    
}
