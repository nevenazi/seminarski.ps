/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Dizajner;

/**
 *
 * @author N
 */
public class ModelTabeleDizajner extends AbstractTableModel {

    List<Dizajner> lista;
    String[] kolone={"idDizajner","ime","prezime","korisnickoIme"};
    
    public ModelTabeleDizajner(List<Dizajner> dizajneri){
        lista=dizajneri;
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
        Dizajner d=lista.get(rowIndex);
        switch (columnIndex){
            case 0: return d.getIdDizajner();
            case 1: return d.getIme();
            case 2: return d.getPrezime();
            case 3: return d.getKorisnickoIme();
            default:return "N/A";
        }
        
        
        
    }

    public List<Dizajner> getLista() {
        return lista;
    }
    
}
