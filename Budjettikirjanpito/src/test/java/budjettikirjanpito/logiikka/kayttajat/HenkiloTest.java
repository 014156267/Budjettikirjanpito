package budjettikirjanpito.logiikka.kayttajat;

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
 * @author Timo
 */
public class HenkiloTest {
    
    Henkilo henkilo;
    
    public HenkiloTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        henkilo = new Henkilo("Matti", "Meikäläinen");
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
     public void toStringToimii() {
         assertEquals("Matti Meikäläinen", henkilo.toString());
     }
     
     
     
}
