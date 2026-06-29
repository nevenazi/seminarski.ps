/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.evidencijeAngazmanaOperacije;


import model.Dizajner;
import model.EvidencijaAngazmana;
import model.MarketingMenadzer;
import operacije.ApstraktnaGenerickaOperacija;
import java.util.List;
import model.StavkaAngazmana;

/**
 *
 * @author N
 */
public class PromeniEvidencijaAngazmanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param==null || !(param instanceof EvidencijaAngazmana) || ((EvidencijaAngazmana) param).getIdEvidencijaAngazmana()<=0){
            throw new Exception("Prosleđeni parametar nije evidencija angažmana.");
        }
        
        EvidencijaAngazmana ea= (EvidencijaAngazmana) param;
        if(ea.getDizajner()==null || !(ea.getDizajner() instanceof Dizajner) || ea.getMarketingMenadzer()==null 
                || !(ea.getMarketingMenadzer() instanceof MarketingMenadzer) || ea.getRok()==null 
                || ea.getStavkeAngazmana()==null || ea.getStavkeAngazmana().isEmpty()|| ea.getUkupanIznos()<0){
            throw new Exception("Greška u unosu podataka evidencije angažmana.");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        EvidencijaAngazmana novaEvidencija = (EvidencijaAngazmana) param;
        List<StavkaAngazmana> noveStavke=novaEvidencija.getStavkeAngazmana();
        EvidencijaAngazmana bivsaEvidencija=(EvidencijaAngazmana) 
                broker.getAll(param, " WHERE idEvidencijaAngazmana="+novaEvidencija.getIdEvidencijaAngazmana()).get(0);
        List<StavkaAngazmana> bivseStavke=bivsaEvidencija.getStavkeAngazmana();
        broker.edit(param);
        boolean izmenjena;
        for (StavkaAngazmana novaStavka : noveStavke) {
            izmenjena=false;
            for (StavkaAngazmana bivsaStavka: bivseStavke){
                if (novaStavka.equals(bivsaStavka)){
                    //edit stavke ako je vec upisana
                    broker.edit(novaStavka);
                    izmenjena=true;
                    bivseStavke.remove(bivsaStavka);
                    break;
                }
            }
            if(!izmenjena)broker.add(novaStavka); //dodavanje stavke ako nije vec upisana
        }

        for (StavkaAngazmana bivsaStavka: bivseStavke){//brisanje stavki koje ne postoje u novoj listi
            broker.delete(bivsaStavka);
        }
    }
    
}
