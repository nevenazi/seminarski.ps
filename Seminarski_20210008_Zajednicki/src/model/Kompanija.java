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
public class Kompanija implements ApstraktniDomenskiObjekat{
    
    private int idKompanija;
    private String naziv;
    private String sajt;

    public Kompanija() {
    }

    public Kompanija(int idKompanija, String naziv, String sajt) {
        this.idKompanija = idKompanija;
        this.naziv = naziv;
        this.sajt = sajt;
    }

    public int getIdKompanija() {
        return idKompanija;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getSajt() {
        return sajt;
    }

    public void setIdKompanija(int idKompanija) {
        this.idKompanija = idKompanija;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSajt(String sajt) {
        this.sajt = sajt;
    }

    @Override
    public String toString() {
        return "Kompanija{" + "idKompanija=" + idKompanija + ", naziv=" + naziv + ", sajt=" + sajt + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "kompanija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int idKompanija=rs.getInt("kompanija.idKompanija");
            String naziv=rs.getString("kompanija.naziv");
            String sajt=rs.getString("kompanija.sajt");
            
            Kompanija komp=new Kompanija(idKompanija, naziv, sajt);
            lista.add(komp);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,sajt";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'"+naziv+"','"+sajt+"'";
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "kompanija.idKompanija="+idKompanija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='"+naziv+"', sajt='"+sajt+"'";
    }
    
    
}
