package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(250);
    }

    @Test
    public void oikeaMaaraRahaaJaMyytyjaLounaita() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kateisOstoToimiiLounaidenOsalta() {
        assertEquals(10, kassapaate.syoEdullisesti(250));
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        
        assertEquals(230, kassapaate.syoEdullisesti(230));
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        
        assertEquals(10, kassapaate.syoMaukkaasti(410));
        assertEquals(100640, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        
        assertEquals(390, kassapaate.syoMaukkaasti(390));
        assertEquals(100640, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());

    }
    
    @Test
    public void korttiOstoToimiiLounaidenOsalta() {
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
        assertEquals(10, kortti.saldo());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(false, kassapaate.syoEdullisesti(kortti));
        assertEquals(10, kortti.saldo());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
        
        kortti = new Maksukortti(410);
        
        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
        assertEquals(10, kortti.saldo());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(false, kassapaate.syoMaukkaasti(kortti));
        assertEquals(10, kortti.saldo());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void kortinSaldoMuuttuuLadattaessaRahaa() {
        kassapaate.lataaRahaaKortille(kortti, 50);
        assertEquals(300, kortti.saldo());
        assertEquals(100050, kassapaate.kassassaRahaa());
        kassapaate.lataaRahaaKortille(kortti, -20);
        assertEquals(300, kortti.saldo());
    }

}
