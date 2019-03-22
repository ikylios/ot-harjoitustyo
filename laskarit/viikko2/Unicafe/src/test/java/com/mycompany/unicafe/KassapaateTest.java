/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    
    Kassapaate kassa;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }

    @Test 
    public void kassaAlustettuOikeinRahat() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test 
    public void kassaAlustettuOikeinEdullisiaMyyty() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test 
    public void kassaAlustettuOikeinMaukkaitaMyyty() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaMyyEdullisiaOikeinRahaaRiittavasti() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaMyyEdullisiaOikeinRahaaRiittavastiKortilla() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);        
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaMyyEdullisiaOikeinRahaEiRiita() {
        kassa.syoEdullisesti(230);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaMyyEdullisiaOikeinRahaEiRiitaKortilla() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);        
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstonJalkeenKortillaOikeinRahaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoEdullisesti(kortti);
        assertTrue(760 == kortti.saldo());
    }
    
    @Test
    public void maukkaanOstonJalkeenKortillaOikeinRahaa() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);
        assertTrue(600 == kortti.saldo());
    }
    
    @Test
    public void kassaMyyMaukkaastiOikeinRahaaRiittavasti() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaMyyMaukkaitaOikeinRahaaRiittavastiKortilla() {
        Maksukortti kortti = new Maksukortti(1000);
        kassa.syoMaukkaasti(kortti);        
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassaMyyMaukkaastiOikeinRahaEiRiita() {
        kassa.syoMaukkaasti(230);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());        
    }
    
    @Test
    public void kassaMyyMaukkaitaOikeinRahaEiRiitaKortilla() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.syoMaukkaasti(kortti);        
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void lataaKortilleOikein() {
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(1000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaKortilleNegatiivisen() {
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, -200);
        assertEquals(0, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
}
