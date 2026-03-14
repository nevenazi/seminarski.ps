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
public class MarketingMenadzer implements ApstraktniDomenskiObjekat{
    
    private int idMarketingMenadzer;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private Kompanija kompanija;

    public MarketingMenadzer() {
    }

    public MarketingMenadzer(int idMarketingMenadzer, String ime, String prezime, String telefon, String email, Kompanija kompanija) {
        this.idMarketingMenadzer = idMarketingMenadzer;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.kompanija=kompanija;
    }

    public Kompanija getKompanija() {
        return kompanija;
    }

    public int getIdMarketingMenadzer() {
        return idMarketingMenadzer;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setIdMarketingMenadzer(int idMarketingMenadzer) {
        this.idMarketingMenadzer = idMarketingMenadzer;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    @Override
    public String toString() {
        return "id=" + idMarketingMenadzer + ", ime=" + ime + " " + prezime + ", kompanija=" + kompanija.getNaziv();
    }

    @Override
    public String vratiNazivTabele() {
        return "marketingmenadzer";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        while (rs.next()){
            int idMarketingMenadzer=rs.getInt("idMarketingMenadzer");
            String ime=rs.getString("ime");
            String prezime=rs.getString("prezime");
            String telefon=rs.getString("telefon");
            String email=rs.getString("email");
            
            int idKompanije=rs.getInt("marketingmenadzer.kompanija");
            String nazivKompanije=rs.getString("kompanija.naziv");
            String sajtKompanije=rs.getString("kompanija.sajt");
            
            Kompanija k=new Kompanija(idKompanije, nazivKompanije, sajtKompanije);
            MarketingMenadzer mm=new MarketingMenadzer(idMarketingMenadzer, ime, prezime, telefon, email, k);
            
            lista.add(mm);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,telefon,email,kompanija";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+telefon+"','"+email+"',"+kompanija.getIdKompanija();
    }

    @Override
    public String vratiPrimatniKljuc() {
        return "marketingmenadzer.idMarketingMenadzer="+idMarketingMenadzer;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', telefon='"+telefon+"', email='"+email+"', kompanija="+kompanija.getIdKompanija();
    }

    @Override
    public String join() {
        return "JOIN kompanija ON marketingmenadzer.kompanija = kompanija.idKompanija ";
    }

    @Override
    public String uslov() {
        ArrayList<String> uslovi = new ArrayList<>();

        if (this!=null && this.kompanija != null && kompanija.getIdKompanija() > 0) {
            uslovi.add("marketingmenadzer.kompanija = " + kompanija.getIdKompanija());
        }
        if (this != null && this.getIme()!=null) {
            uslovi.add("marketingmenadzer.ime = '" + this.getIme()+"'");
        }
        
        if (this != null && this.getPrezime()!=null) {
            uslovi.add("marketingmenadzer.prezime = '" + this.getPrezime()+"'");
        }
   
        if (uslovi.isEmpty()) {
            return ""; 
        }
        return "WHERE " + String.join(" AND ", uslovi);
    }

    
    
    
}
