/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author N
 */
public class Dizajner implements ApstraktniDomenskiObjekat {
    
    private int idDizajner;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Dizajner() {
    }

    public Dizajner(int id, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idDizajner = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdDizajner() {
        return idDizajner;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setIdDizajner(int id) {
        this.idDizajner = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return "Dizajner{" + "id=" + idDizajner + ", ime=" + ime + ", prezime=" + prezime + ", korisnickoIme=" + korisnickoIme + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Dizajner other = (Dizajner) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    
    

    @Override
    public String vratiNazivTabele() {
        return "dizajner";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int idDizajner=rs.getInt("idDizajner");
            String ime=rs.getString("ime");
            String prezime=rs.getString("prezime");
            String korisnickoIme=rs.getString("korisnickoIme");
            String sifra=rs.getString("sifra");
            
            Dizajner dizajner= new Dizajner(idDizajner, ime, prezime, korisnickoIme, sifra);
            lista.add(dizajner);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "dizajner.idDizajner="+idDizajner;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {

        return this;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', korisnickoIme='"+korisnickoIme+"', sifra='"+sifra+"'";
    }
    
    
}
