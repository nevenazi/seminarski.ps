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
public class StavkaAngazmana implements ApstraktniDomenskiObjekat {
    
    private EvidencijaAngazmana evidencijaAngazmana;
    private int rb;
    private int kolicina;
    private String opis;
    private double cena;
    private double nekorigovanIznos;
    private double korekcijaIznosa;
    private double korigovanIznos;
    private boolean zavrsena;
    private TipVizuala tipVizuala;

    public StavkaAngazmana() {
    }

    public StavkaAngazmana(EvidencijaAngazmana evidencijaAngazmana, int rb, int kolicina, String opis, double cena, double nekorigovanIznos, double korekcijaIznosa, double korigovanIznos, boolean zavrsena, TipVizuala tipVizuala) {
        this.evidencijaAngazmana = evidencijaAngazmana;
        this.rb = rb;
        this.kolicina = kolicina;
        this.opis = opis;
        this.cena = cena;
        this.nekorigovanIznos = nekorigovanIznos;
        this.korekcijaIznosa = korekcijaIznosa;
        this.korigovanIznos = korigovanIznos;
        this.zavrsena = zavrsena;
        this.tipVizuala = tipVizuala;
    }

    

    public EvidencijaAngazmana getEvidencijaAngazmana() {
        return evidencijaAngazmana;
    }

    public int getRb() {
        return rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public double getCena() {
        return cena;
    }

    public double getNekorigovanIznos() {
        return nekorigovanIznos;
    }

    public double getKorekcijaIznosa() {
        return korekcijaIznosa;
    }

    public double getKorigovanIznos() {
        return korigovanIznos;
    }

    public TipVizuala getTipVizuala() {
        return tipVizuala;
    }
    

    public boolean isZavrsena() {
        return zavrsena;
    }

    public void setEvidencijaAngazmana(EvidencijaAngazmana evidencijaAngazmana) {
        this.evidencijaAngazmana = evidencijaAngazmana;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setNekorigovanIznos(double nekorigovanIznos) {
        this.nekorigovanIznos = nekorigovanIznos;
    }

    public void setKorekcijaIznosa(double korekcijaIznosa) {
        this.korekcijaIznosa = korekcijaIznosa;
    }

    public void setKorigovanIznos(double korigovanIznos) {
        this.korigovanIznos = korigovanIznos;
    }

    public void setZavrsena(boolean zavrsena) {
        this.zavrsena = zavrsena;
    }

    public void setTipVizuala(TipVizuala tipVizuala) {
        this.tipVizuala = tipVizuala;
    }

    @Override
    public String toString() {
        return "StavkaAngazmana{" + "evidencijaAngazmana=" + evidencijaAngazmana + ", rb=" + rb + ", kolicina=" + kolicina + ", opis=" + opis + ", cena=" + cena + ", korigovanIznos=" + korigovanIznos + ", zavrsena=" + zavrsena + ", tipVizuala=" + tipVizuala + '}';
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
        final StavkaAngazmana other = (StavkaAngazmana) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.evidencijaAngazmana, other.evidencijaAngazmana);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaangazmana";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            
            
            int rb=rs.getInt("stavkaangazmana.rb");
            int kolicina=rs.getInt("stavkaangazmana.kolicina");
            String opis=rs.getString("stavkaangazmana.opis");
            double cena=rs.getDouble("stavkaangazmana.cena");
            double nekorigovanIznos=rs.getDouble("stavkaangazmana.nekorigovanIznos");
            double korekcijaIznosa=rs.getDouble("stavkaangazmana.korekcijaIznosa");
            double korigovanIznos=rs.getDouble("stavkaangazmana.korigovanIznos");
            boolean zavrsena=rs.getBoolean("stavkaangazmana.zavrsena");
            
            //evidencija angazmana
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
            
            //tip vizuala
            int idviz=rs.getInt("tipvizuala.idTipVizuala");
            String nazivviz=rs.getString("tipvizuala.naziv");
            String dimenzije=rs.getString("tipvizuala.dimenzije");
            String modelBoja=rs.getString("tipvizuala.modelBoja");
            double osnovnaCena=rs.getDouble("tipvizuala.osnovnaCena");
            
            TipVizuala tipvizuala=new TipVizuala(idviz, nazivviz, dimenzije, modelBoja, osnovnaCena);
            
            StavkaAngazmana stavka=new StavkaAngazmana(evidencija, rb, kolicina, opis, cena, nekorigovanIznos, korekcijaIznosa, korigovanIznos, zavrsena, tipvizuala);
            
            lista.add(stavka);
            
            
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "EvidencijaAngazmana,rb,kolicina,opis,cena,nekorigovanIznos,korekcijaIznosa,korigovanIznos,zavrsena,tipVizuala";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return evidencijaAngazmana.getIdEvidencijaAngazmana()+","+rb+","+kolicina+",'"+opis+"',"+cena+","+nekorigovanIznos+","+korekcijaIznosa+","+korigovanIznos+","+zavrsena+","+tipVizuala.getIdTipVizuala();
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "stavkaangazmana.evidencijaAngazmana="+evidencijaAngazmana.getIdEvidencijaAngazmana()+" AND stavkaangazmana.rb="+rb;
    }


    @Override
    public String vratiVrednostZaIzmenu() {
        return "evidencijaAngazmana="+evidencijaAngazmana.getIdEvidencijaAngazmana()+", rb="+rb+", kolicina="+kolicina+", opis='"+opis+"', cena="+cena+", nekorigovanIznos="+nekorigovanIznos+", korekcijaIznosa="+korekcijaIznosa+", korigovanIznos="+korigovanIznos+", zavrsena="+zavrsena+", tipVizuala="+tipVizuala.getIdTipVizuala();
    }

    @Override
    public String join() {
        return " JOIN tipvizuala ON (stavkaangazmana.tipVizuala=tipvizuala.idTipVizuala)"
                + "JOIN evidencijaangazmana ON (stavkaangazmana.evidencijaAngazmana=evidencijaangazmana.idEvidencijaAngazmana)"
                + "JOIN dizajner ON (evidencijaangazmana.dizajner=dizajner.idDizajner)" 
                + "JOIN marketingmenadzer ON (evidencijaangazmana.marketingMenadzer=marketingMenadzer.idMarketingMenadzer)" 
                + "JOIN kompanija ON (marketingMenadzer.kompanija = kompanija.idKompanija)";
    }

    @Override
    public String uslov() {
        ArrayList<String> uslovi = new ArrayList<>();

        if (this!=null && this.evidencijaAngazmana != null && evidencijaAngazmana.getIdEvidencijaAngazmana()> 0) {
            uslovi.add("stavkaangazmana.evidencijaAngazmana = " + evidencijaAngazmana.getIdEvidencijaAngazmana());
        }
        
   
        if (uslovi.isEmpty()) {
            return ""; 
        }
        return "WHERE " + String.join(" AND ", uslovi);
    }

    

    
    
    
    
}
