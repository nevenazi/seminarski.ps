/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author N
 */
public class Sertifikat implements ApstraktniDomenskiObjekat{
    
    private int idSertifikat;
    private String naziv;
    private String institucija;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikat, String naziv, String institucija) {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.institucija = institucija;
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    
    @Override
    public String toString() {
        return "Sertifikat{" + "idSertifikat=" + idSertifikat + ", naziv=" + naziv + ", institucija=" + institucija + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sertifikat other = (Sertifikat) obj;
        return this.idSertifikat == other.idSertifikat;
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int idSertifikat=rs.getInt("sertifikat.idSertifikat");
            String naziv=rs.getString("sertifikat.naziv");
            String institucija=rs.getString("sertifikat.institucija");
            
            Sertifikat sert=new Sertifikat(idSertifikat, naziv, institucija);
            lista.add(sert);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,institucija";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'"+naziv+"','"+institucija+"'";
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "sertifikat.idSertifikat="+idSertifikat;
    }


    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='"+naziv+"', institucija='"+institucija+"'";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }
    

    
    
    
}
