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
    public String vratiTabelu() {
        return "stavkaangazmana";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int idEvidencijaAngazmana=rs.getInt("stavkaangazmana.evidencijaAngazmana");
            /// evidencija
            int rb=rs.getInt("stavkaangazmana.rb");
            int kolicina=rs.getInt("stavkaangazmana.kolicina");
            String opis=rs.getString("stavkaangazmana.opis");
            double cena=rs.getDouble("stavkaangazmana.cena");
            double nekorigovanIznos=rs.getDouble("stavkaangazmana.nekorigovanIznos");
            double korekcijaIznosa=rs.getDouble("stavkaangazmana.korekcijaIznosa");
            double korigovanIznos=rs.getDouble("stavkaangazmana.korigovanIznos");
            boolean zavrsena=rs.getBoolean("stavkaangazmana.zavrsena");
            int idTipVizuala=rs.getInt("stavkaangazmana.tipVizuala");
            
            ////tip vizuala
            
            
        }
        
        return lista;
    }

    @Override
    public String vratiKolonuZaUbacivanje() {
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
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "evidencijaAngazmana="+evidencijaAngazmana.getIdEvidencijaAngazmana()+", rb="+rb+", kolicina="+kolicina+", opis='"+opis+"', cena="+cena+", nekorigovanIznos="+nekorigovanIznos+", korekcijaIznosa="+korekcijaIznosa+", korigovanIznos="+korigovanIznos+", zavrsena="+zavrsena+", tipVizuala="+tipVizuala.getIdTipVizuala();
    }

    

    
    
    
    
}
