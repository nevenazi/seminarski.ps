/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacije.marketingMenadzerOperacije;

import model.Kompanija;
import model.MarketingMenadzer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import repository.Repository;

/**
 *
 * @author N
 */
public class KreirajMarketingMenadzerSOTest {
    private KreirajMarketingMenadzerSO so;
    private MarketingMenadzer menadzer;
    
    
    @Before
    public void setUp() {
        so=new KreirajMarketingMenadzerSO();
        menadzer=new MarketingMenadzer();
    }
    
    @After
    public void tearDown() {
        so=null;
        menadzer=null;
    }

    @Test
    public void testPredusloviIspravan()throws Exception{
        menadzer.setIme("Petar");
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija(1, "Yettel", "https://www.yettel.rs"));
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("+381617897897");
        so.preduslovi(menadzer);
    }
    
    @Test(expected = Exception.class)
    public void testPredusloviBezImena() throws Exception {
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija(1, "Yettel", "https://www.yettel.rs"));
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("+38161234567");

        so.preduslovi(menadzer);
    }
    @Test(expected = Exception.class)
    public void testPredusloviPraznoIme() throws Exception {
        menadzer.setIme("");
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija(1, "Yettel", "https://www.yettel.rs"));
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("+38161234567");

        so.preduslovi(menadzer);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNeispravanEmail() throws Exception {
        menadzer.setIme("Petar");
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija(1, "Yettel", "https://www.yettel.rs"));
        menadzer.setEmail("peraperic@@gmail");
        menadzer.setTelefon("+38161234567");
        so.preduslovi(menadzer);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNeispravanTelefon() throws Exception {
        menadzer.setIme("Petar");
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija(1, "Yettel", "https://www.yettel.rs"));
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("061234567");

        so.preduslovi(menadzer);
    }
    
    @Test(expected = Exception.class)
    public void testPredusloviBezKompanije() throws Exception {
        menadzer.setIme("Petar");
        menadzer.setPrezime("Petrovic");
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("+38161234567");
        menadzer.setKompanija(null);

        so.preduslovi(menadzer);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() throws Exception {
        menadzer.setIme("Petar");
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija(1, "Yettel", "https://www.yettel.rs"));
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("+38161234567");

        so.izvrsiOperaciju(menadzer, null);

        assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        menadzer.setIme("Petar");
        menadzer.setPrezime("Petrovic");
        menadzer.setKompanija(new Kompanija());
        menadzer.setEmail("peraperic@gmail.com");
        menadzer.setTelefon("+38161234567");

        so.izvrsiOperaciju(menadzer, null);

        assertFalse(true);
    }
}
