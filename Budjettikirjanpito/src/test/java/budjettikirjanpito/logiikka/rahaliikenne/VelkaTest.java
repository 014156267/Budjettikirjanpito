/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettikirjanpito.logiikka.rahaliikenne;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timo
 */
public class VelkaTest {
    
    Velka velka;
    
    public VelkaTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void paljonkoVelkaaJaljellaKkPaastaToimii() {
        velka = new Velka(480.00, "Puhelin", 24, 2.0);
        assertEquals(160.00,velka.paljonkoVelkaaJaljellaXKkPaasta(16), 0.009);
        assertEquals(240.00,velka.paljonkoVelkaaJaljellaXKkPaasta(12), 0.009);
        assertEquals(0,velka.paljonkoVelkaaJaljellaXKkPaasta(130), 0.009);
        assertEquals(0,velka.paljonkoVelkaaJaljellaXKkPaasta(24), 0.009);
        assertEquals(20.00,velka.paljonkoVelkaaJaljellaXKkPaasta(23), 0.009);
    }
    
    @Test
    public void velanKorkoYhteensaToimii() {
        Velka velka2 = new Velka(480.00, "Puhelin", 24, 2.0);
        assertEquals(14.40, velka2.velanKorkoYhteensa(), 0.009);
        Velka velka3 = new Velka(0.00, "Puhelin", 24, 2.0);
        assertEquals(0.00, velka3.velanKorkoYhteensa(), 0.009);
        Velka velka4 = new Velka(50.00, "Puhelin", 10, 12.0);
        assertEquals(5.00, velka4.velanKorkoYhteensa(), 0.009);
    }
    
    @Test
    public void kuukausimaksuToimii() {
        Velka velka2 = new Velka(480.00, "Puhelin", 24, 2.0);
        assertEquals(20.80, velka2.kuukausimaksu(), 0.009);
        Velka velka3 = new Velka(0, "Puhelin", 12, 10.0);
        assertEquals(0, velka3.kuukausimaksu(), 0.009);
        Velka velka4 = new Velka(100.00, "Puhelin", 10, 12.0);
        assertEquals(11.00, velka4.kuukausimaksu(), 0.009);
    }
    
    @Test
    public void kuukaudenLyhennysEraToimii() {
        Velka velka2 = new Velka(480.00, "Puhelin", 24, 2.0);
        assertEquals(20.00, velka2.kuukaudenLyhennysEra(), 0.009);
        Velka velka3 = new Velka(0, "Puhelin", 12, 10.0);
        assertEquals(0, velka3.kuukaudenLyhennysEra(), 0.009);
        Velka velka4 = new Velka(120.12, "Puhelin", 12, 12.0);
        assertEquals(10.01, velka4.kuukaudenLyhennysEra(), 0.009);
    }
    
    
}
