/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.TipVizuala;

/**
 *
 * @author N
 */
public class ModelTabeleTipVizuala extends AbstractTableModel {

    List<TipVizuala> lista;
    String[] kolone={"id","naziv","dimenzije","model boja","osnovna cena"};
    
    public ModelTabeleTipVizuala(List<TipVizuala> vizuali){
        lista=vizuali;
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
        TipVizuala t=lista.get(rowIndex);
        switch (columnIndex){
            case 0: return t.getIdTipVizuala();
            case 1: return t.getNaziv();
            case 2: return t.getDimenzije();
            case 3: return t.getModelBoja();
            case 4: return t.getOsnovnaCena();
            default:return "N/A";
        }
        
        
        
    }

    public List<TipVizuala> getLista() {
        return lista;
    }
    
}
