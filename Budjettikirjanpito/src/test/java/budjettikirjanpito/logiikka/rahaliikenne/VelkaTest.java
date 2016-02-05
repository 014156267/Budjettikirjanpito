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
    
//    @Test
//    public void velanKorkoYhteensaToimii() {
//        Velka velka2 = new Velka(480.00, "Puhelin", 24, 2.0);
//        assertEquals(14.40, velka2.velanKorkoYhteensa(), 0.009);
//        
//    }
    
}
