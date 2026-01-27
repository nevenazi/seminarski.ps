/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet; 

/**
 *
 * @author N
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    
    public String vratiNazivTabele();
    
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    public String vratiKoloneZaUbacivanje();
    
    public String vratiVrednostZaUbacivanje();
    
    public String vratiPrimatniKljuc();
    
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    public String vratiVrednostZaIzmenu();
}
