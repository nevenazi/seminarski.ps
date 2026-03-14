/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaAngazmana;

/**
 *
 * @author N
 */
public class ModelTabeleStavkaAngazmana extends AbstractTableModel {

    List<StavkaAngazmana> lista;
    String[] kolone={"ev. ang.","rb","količina","opis","cena","nekorigovan izn.","korekcija iznosa","korigovan iznos","završena","tip vizuala"};
    
    public ModelTabeleStavkaAngazmana(List<StavkaAngazmana> stavke){
        lista=stavke;
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
        StavkaAngazmana s=lista.get(rowIndex);
        switch (columnIndex){
            case 0: return s.getEvidencijaAngazmana().getIdEvidencijaAngazmana();
            case 1: return s.getRb();
            case 2: return s.getKolicina();
            case 3: return s.getOpis();
            case 4: return s.getCena();
            case 5: return s.getNekorigovanIznos();
            case 6: return s.getKorekcijaIznosa();
            case 7: return s.getKorigovanIznos();
            case 8: return s.isZavrsena();
            case 9: return s.getTipVizuala().getNaziv();
            default:return "N/A";
        }
        
        
        
    }

    public List<StavkaAngazmana> getLista() {
        return lista;
    }
    
}
