/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author N
 */
public class EvidencijaAngazmana implements ApstraktniDomenskiObjekat{
    
    private int idEvidencijaAngazmana;
    private double ukupanIznos;
    private Date rok;
    private boolean zavrsen;
    private List<StavkaAngazmana> stavkeAngazmana;
    private Dizajner dizajner;
    private MarketingMenadzer marketingMenadzer;

    public EvidencijaAngazmana() {
    }

    public EvidencijaAngazmana(int idEvidencijaAngazmana, double ukupanIznos, Date rok, boolean zavrsen, List<StavkaAngazmana> stavkeAngazmana, Dizajner dizajner, MarketingMenadzer marketingMenadzer) {
        this.idEvidencijaAngazmana = idEvidencijaAngazmana;
        this.ukupanIznos = ukupanIznos;
        this.rok = rok;
        this.zavrsen = zavrsen;
        this.stavkeAngazmana = stavkeAngazmana;
        this.dizajner = dizajner;
        this.marketingMenadzer = marketingMenadzer;
    }

    
    public int getIdEvidencijaAngazmana() {
        return idEvidencijaAngazmana;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public Date getRok() {
        return rok;
    }

    public boolean isZavrsen() {
        return zavrsen;
    }

    public List<StavkaAngazmana> getStavkeAngazmana() {
        return stavkeAngazmana;
    }

    public Dizajner getDizajner() {
        return dizajner;
    }

    public MarketingMenadzer getMarketingMenadzer() {
        return marketingMenadzer;
    }

    public void setIdEvidencijaAngazmana(int idEvidencijaAngazmana) {
        this.idEvidencijaAngazmana = idEvidencijaAngazmana;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }

    public void setZavrsen(boolean zavrsen) {
        this.zavrsen = zavrsen;
    }

    public void setStavkeAngazmana(List<StavkaAngazmana> stavkeAngazmana) {
        this.stavkeAngazmana = stavkeAngazmana;
    }

    public void setDizajner(Dizajner dizajner) {
        this.dizajner = dizajner;
    }

    public void setMarketingMenadzer(MarketingMenadzer marketingMenadzer) {
        this.marketingMenadzer = marketingMenadzer;
    }

    @Override
    public String toString() {
        return "EvidencijaAngazmana{" + "idEvidencijaAngazmana=" + idEvidencijaAngazmana + ", ukupanIznos=" + ukupanIznos + ", rok=" + rok + ", zavrsen=" + zavrsen + ", stavkeAngazmana=" + stavkeAngazmana + ", dizajner=" + dizajner + ", marketingMenadzer=" + marketingMenadzer + '}';
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
        final EvidencijaAngazmana other = (EvidencijaAngazmana) obj;
        return this.idEvidencijaAngazmana == other.idEvidencijaAngazmana;
    }

    @Override
    public String vratiNazivTabele() {
        return "evidencijaangazmana";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int idEvidencijaAngazmana=rs.getInt("evidencijaangazmana.idEvidencijaAngazmana");
            double ukupanIznos=rs.getDouble("evidencijaangazmana.ukupanIznos");
            java.sql.Date roksql=rs.getDate("evidencijaangazmana.rok");
            java.util.Date rok=new java.util.Date(roksql.getTime());
            boolean zavrsen=rs.getBoolean("evidencijaangazmana.zavrsen");
            
            int dizajnerid=rs.getInt("dizajner.idDizajner");
            String imed=rs.getString("dizajner.ime");
            String prezimed=rs.getString("dizajner.prezime");
            String korisnickoIme=rs.getString("dizajner.korisnickoIme");
            String sifra=rs.getString("dizajner.sifra");
            
            Dizajner dizajner=new Dizajner(dizajnerid, imed, prezimed, korisnickoIme, sifra);
            
            int menadzid=rs.getInt("marketingmenadzer.idMarketingMenadzer");
            String imem=rs.getString("marketingmenadzer.ime");
            String prezimem=rs.getString("marketingmenadzer.prezime");
            String telefon=rs.getString("marketingmenadzer.telefon");
            String email=rs.getString("marketingmenadzer.email");
            
            int kompid=rs.getInt("kompanija.idKompanija");
            String naziv=rs.getString("kompanija.naziv");
            String sajt=rs.getString("kompanija.sajt");
            Kompanija kompanija=new Kompanija(kompid, naziv, sajt);
            
            MarketingMenadzer marketingMenadzer=new MarketingMenadzer(menadzid, imem, prezimem, telefon, email, kompanija);
            
            EvidencijaAngazmana evidencija=new EvidencijaAngazmana(idEvidencijaAngazmana, ukupanIznos, rok, zavrsen, null, dizajner, marketingMenadzer);
            
            lista.add(evidencija);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupanIznos,rok,zavrsen,dizajner,marketingMenadzer";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        java.sql.Date sqlRok= new java.sql.Date(rok.getTime());
        return ukupanIznos+"'"+sqlRok+"'"+zavrsen+"'"+dizajner.getIdDizajner()+"'"+marketingMenadzer.getIdMarketingMenadzer();
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "evidencijaangazmana.idEvidencijaAngazmana="+idEvidencijaAngazmana;
    }


    @Override
    public String vratiVrednostZaIzmenu() {
        java.sql.Date sqlRok= new java.sql.Date(rok.getTime());
        return "ukupanIznos="+ukupanIznos+", rok='"+sqlRok+"', zavrsen="+zavrsen+", dizajner="+dizajner.getIdDizajner()+", marketingMenadzer="+marketingMenadzer.getIdMarketingMenadzer();
    }

    @Override
    public String join() {
        return "JOIN dizajner ON (evidencijaangazmana.dizajner=dizajner.idDizajner)" 
                + "JOIN marketingmenadzer ON (evidencijaangazmana.marketingMenadzer=marketingMenadzer.idMarketingMenadzer)" 
                + "JOIN kompanija ON (marketingMenadzer.kompanija = kompanija.idKompanija)";
    }

    @Override
    public String uslov() {
        ArrayList<String> uslovi = new ArrayList<>();

        if (this!=null && this.marketingMenadzer != null && marketingMenadzer.getIdMarketingMenadzer()> 0) {
            uslovi.add("evidencijaangazmana.marketingmenadzer = " + marketingMenadzer.getIdMarketingMenadzer());
        }
        
        if (this!=null && this.dizajner != null && dizajner.getIdDizajner()> 0) {
            uslovi.add("evidencijaangazmana.dizajner = " + dizajner.getIdDizajner());
        }
        
   
        if (uslovi.isEmpty()) {
            return ""; 
        }
        return "WHERE " + String.join(" AND ", uslovi);
    }
    
    
}
