/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author N
 */
public class DizajnerSertifikat implements ApstraktniDomenskiObjekat {
    
    private Dizajner dizajner;
    private Sertifikat sertifikat;
    private Date datumIzdavanja;

    public DizajnerSertifikat() {
    }

    public DizajnerSertifikat(Dizajner dizajner, Sertifikat sertifikat, Date datumIzdavanja) {
        this.dizajner = dizajner;
        this.sertifikat = sertifikat;
        this.datumIzdavanja = datumIzdavanja;
    }

    public Dizajner getDizajner() {
        return dizajner;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDizajner(Dizajner dizajner) {
        this.dizajner = dizajner;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    @Override
    public String toString() {
        return "DizajnerSertifikat{" + "dizajner=" + dizajner + ", sertifikat=" + sertifikat + ", datumIzdavanja=" + datumIzdavanja + '}';
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
        final DizajnerSertifikat other = (DizajnerSertifikat) obj;
        if (!Objects.equals(this.dizajner, other.dizajner)) {
            return false;
        }
        if (!Objects.equals(this.sertifikat, other.sertifikat)) {
            return false;
        }
        return Objects.equals(this.datumIzdavanja, other.datumIzdavanja);
    }

    @Override
    public String vratiNazivTabele() {
        return "dizajnersertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            
            int idDizajner=rs.getInt("dizajner.idDizajner");
            String ime=rs.getString("dizajner.ime");
            String prezime=rs.getString("dizajner.prezime");
            String korisnickoIme=rs.getString("dizajner.korisnickoIme");
            String sifra=rs.getString("dizajner.sifra");
            
            Dizajner dizajner= new Dizajner(idDizajner, ime, prezime, korisnickoIme, sifra);
            
            int idSertifikat=rs.getInt("sertifikat.idSertifikat");
            String naziv=rs.getString("sertifikat.naziv");
            String institucija=rs.getString("sertifikat.institucija");
            
            Sertifikat sertifikat=new Sertifikat(idSertifikat, naziv, institucija);
            
            java.sql.Date datumsql=rs.getDate("dizajnersertifikat.datumIzdavanja");
            java.util.Date datumIzdavanja=new java.util.Date(datumsql.getTime());
            
            DizajnerSertifikat dizser=new DizajnerSertifikat(dizajner, sertifikat, datumIzdavanja);
            lista.add(dizser);
            
        }
        
        return lista;

    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "dizajner,sertifikat,datumIzdavanja";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        java.sql.Date sqlDatum = new java.sql.Date(datumIzdavanja.getTime());
        return dizajner.getIdDizajner()+","+sertifikat.getIdSertifikat()+","+"'"+sqlDatum+"'";
    }

    @Override
    public String vratiPrimatniKljuc() {
        java.sql.Date sqlDatum= new java.sql.Date(datumIzdavanja.getTime());
        return "dizajnersertifikat.dizajner="+dizajner.getIdDizajner()+" AND dizajnersertifikat.sertifikat="+sertifikat.getIdSertifikat()+" AND dizajnersertifikat.datumIzdavanja='"+sqlDatum+"'";
    }


    @Override
    public String vratiVrednostZaIzmenu() {
        java.sql.Date sqlDatum = new java.sql.Date(datumIzdavanja.getTime());
        return "dizajner="+dizajner.getIdDizajner()+", sertifikat="+sertifikat.getIdSertifikat()+", datumIzdavanja='"+sqlDatum+"'";

    }

    @Override
    public String join() {
        return "JOIN dizajner ON dizajnersertifikat.dizajner = dizajner.idDizajner "
             + "JOIN sertifikat ON dizajnersertifikat.sertifikat = sertifikat.idSertifikat";
    }

    @Override
    public String uslov() {
        return "";
    }
    
    
}
