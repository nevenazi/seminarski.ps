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

/**
 *
 * @author N
 */
public class ObrisiMarketingMenadzerSOTest {
    
    private ObrisiMarketingMenadzerSO so;
    private MarketingMenadzer menadzer;
    
    
    @Before
    public void setUp() {
        so=new ObrisiMarketingMenadzerSO();
        menadzer=new MarketingMenadzer();
    }
    
    @After
    public void tearDown() {
        so=null;
        menadzer=null;
    }

    @Test
    public void testPredusloviIspravan() throws Exception {
        menadzer.setIdMarketingMenadzer(3);
        so.preduslovi(menadzer);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNevalidanObjekat() throws Exception {
        so.preduslovi(new Object());
    }

    @Test(expected = Exception.class)
    public void testPredusloviNull() throws Exception {
        so.preduslovi(null);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() throws Exception {
        menadzer.setIdMarketingMenadzer(4);
        so.izvrsiOperaciju(menadzer, null);
        assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        menadzer.setIdMarketingMenadzer(2);
        so.izvrsiOperaciju(menadzer, null);
        assertFalse(true);
    }
    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuDvaput() throws Exception {
        menadzer.setIdMarketingMenadzer(3);
        so.izvrsiOperaciju(menadzer, null);
        so.izvrsiOperaciju(menadzer, null);
        assertFalse(true);
    }
          
    
}
