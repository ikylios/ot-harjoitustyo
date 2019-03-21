/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xilxilx
 */
public class MaksukorttiTest {        
    
    Maksukortti kortti;
    
    public MaksukorttiTest() {        
    }
    
    @Test
    public void hello() {}
    
        @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {                
        String vastaus = kortti.toString();        
        assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {        
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoEdullisesti();
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaSaldoa() {
        kortti.lataaRahaa(-3.0);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void voiOstaaEdullisenKunSaldoaVainEdullisenVerran() {
        kortti = new Maksukortti(2.5);
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
    
    @Test
    public void voiOstaaMaukkaanKunSaldoaVainMaukkaanVerran() {
        kortti = new Maksukortti(4);
        kortti.syoMaukkaasti();        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

}
