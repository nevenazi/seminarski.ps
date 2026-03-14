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
public class TipVizuala implements ApstraktniDomenskiObjekat {
    
    private int idTipVizuala;
    private String naziv;
    private String dimenzije;
    private String modelBoja;
    private double osnovnaCena;

    public TipVizuala() {
    }

    public TipVizuala(int idTipVizuala, String naziv, String dimenzije, String modelBoja, double osnovnaCena) {
        this.idTipVizuala = idTipVizuala;
        this.naziv = naziv;
        this.dimenzije = dimenzije;
        this.modelBoja = modelBoja;
        this.osnovnaCena = osnovnaCena;
    }

    public int getIdTipVizuala() {
        return idTipVizuala;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getDimenzije() {
        return dimenzije;
    }

    public String getModelBoja() {
        return modelBoja;
    }

    public double getOsnovnaCena() {
        return osnovnaCena;
    }

    public void setIdTipVizuala(int idTipVizuala) {
        this.idTipVizuala = idTipVizuala;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setDimenzije(String dimenzije) {
        this.dimenzije = dimenzije;
    }

    public void setModelBoja(String modelBoja) {
        this.modelBoja = modelBoja;
    }

    public void setOsnovnaCena(double osnovnaCena) {
        this.osnovnaCena = osnovnaCena;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final TipVizuala other = (TipVizuala) obj;
        return this.idTipVizuala == other.idTipVizuala;
    }

    @Override
    public String toString() {
        return "id=" + idTipVizuala + ", naziv=" + naziv + ", dimenzije=" + dimenzije;
    }

    @Override
    public String vratiNazivTabele() {
        return "tipvizuala";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("tipvizuala.idTipVizuala");
            String naziv=rs.getString("tipvizuala.naziv");
            String dimenzije=rs.getString("tipvizuala.dimenzije");
            String modelBoja=rs.getString("tipvizuala.modelBoja");
            double osnovnaCena=rs.getDouble("tipvizuala.osnovnaCena");
            
            TipVizuala tipvizuala=new TipVizuala(id, naziv, dimenzije, modelBoja, osnovnaCena);
            lista.add(tipvizuala);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,dimenzije,modelBoja,osnovnaCena";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'"+naziv+"','"+dimenzije+"','"+modelBoja+"',"+osnovnaCena;
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "tipvizuala.idTipVizuala="+idTipVizuala;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='"+naziv+"', dimenzije='"+dimenzije+"', modelBoja='"+modelBoja+"', osnovnaCena="+osnovnaCena;
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
