/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacije.dizajnerOperacije;

import model.Dizajner;
import model.MarketingMenadzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author N
 */
public class PrijaviDizajnerSOTest {
    
    private PrijaviDizajnerSO so;
    private Dizajner dizajner;
    
    
    
    @Before
    public void setUp() {
        so=new PrijaviDizajnerSO();
        dizajner=new Dizajner();
    }
    
    @After
    public void tearDown() {
        so=null;
        dizajner=null;
    }

    @Test
    public void testIspravanPreduslovi() throws Exception {
        dizajner.setKorisnickoIme("anapet");
        dizajner.setSifra("anapet");
        so.preduslovi(dizajner);
    }
    
    @Test(expected = Exception.class)
    public void testNullParametarPreduslovi() throws Exception {
        so.preduslovi(null);
    }
    
    @Test(expected = Exception.class)
    public void testNeispravnaKlasaPreduslovi() throws Exception {
        so.preduslovi(new MarketingMenadzer());
    }

    @Test(expected = Exception.class)
    public void testPredusloviPraznoKorisnickoIme() throws Exception {
        dizajner.setKorisnickoIme("");
        dizajner.setSifra("anapet");
        so.preduslovi(dizajner);
    }
    @Test(expected = Exception.class)
    public void testPredusloviNullKorisnickoIme() throws Exception {
        dizajner.setKorisnickoIme("");
        dizajner.setSifra("anapet");
        so.preduslovi(dizajner);
    }

    @Test(expected = Exception.class)
    public void testPredusloviPraznaSifra() throws Exception {
        dizajner.setKorisnickoIme("anapet");
        dizajner.setSifra("");
        so.preduslovi(dizajner);
    }
    @Test(expected = Exception.class)
    public void testPredusloviNullSifra() throws Exception {
        dizajner.setKorisnickoIme("anapet");
        dizajner.setSifra("");
        so.preduslovi(dizajner);
    }

    @Test(expected = Exception.class)
    public void testPredusloviKratkaSifra() throws Exception {
        dizajner.setKorisnickoIme("anapet");
        dizajner.setSifra("12345");
        so.preduslovi(dizajner);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() throws Exception {
        dizajner.setKorisnickoIme("anapet");
        dizajner.setSifra("anapet");
        so.izvrsiOperaciju(dizajner, null);

        assertNotNull(so.getDizajner());
        assertEquals("anapet", so.getDizajner().getKorisnickoIme());
    }

    @Test
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        dizajner.setKorisnickoIme("anapet");
        dizajner.setSifra("ana");
        so.izvrsiOperaciju(dizajner, null);

        assertNull(so.getDizajner());
    }
    
    
    
}
