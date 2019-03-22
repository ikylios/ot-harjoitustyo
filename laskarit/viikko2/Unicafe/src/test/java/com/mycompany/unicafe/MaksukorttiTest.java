package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }        
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals(2000, kortti.saldo()); 
    }
    
    @Test
    public void hyvaksyyNegatiivisenRahanLataamisen() {
        kortti.lataaRahaa(-500);
        assertEquals(500, kortti.saldo()); 
    }
    
    @Test
    public void saldoVaheneeOikeinKunRahaaTarpeeksi() {
        kortti.otaRahaa(500);
        assertTrue(kortti.saldo() > 0);
    }
    
    @Test
    public void saldoaEiVaheneKunRahaaEiOleTarpeeksi() {
        int vanhaSaldo = kortti.saldo();
        kortti.otaRahaa(2000);
        assertFalse(kortti.saldo() < vanhaSaldo);
    }

    
}
