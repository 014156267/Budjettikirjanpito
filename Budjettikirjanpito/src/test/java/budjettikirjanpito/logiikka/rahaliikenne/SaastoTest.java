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
public class SaastoTest {
    
    Saasto saasto;
    
    public SaastoTest() {
        saasto = new Saasto(1000, "tuhlattavaksi");
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
     public void konstruktoriAsettaaOikein() {
         assertEquals(0, saasto.getKuukausiMaara());
         assertEquals(0, saasto.getKuukausiSumma(), 0.009);
         assertEquals(1000, saasto.maara, 0.009);
         assertEquals("tuhlattavaksi", saasto.selitys);
     }
     
     @Test
     public void kuukausienMaaraOikein() {
         
         saasto.setKuukausiSumma(50);
         assertEquals(20, saasto.getKuukausiMaara(), 0.009);
         assertEquals(50, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiSumma(49.5);
         assertEquals(21, saasto.getKuukausiMaara(), 0.009);
         assertEquals(49.5, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiSumma(51);
         assertEquals(20, saasto.getKuukausiMaara(), 0.009);
         assertEquals(51, saasto.getKuukausiSumma(), 0.009);
         
     }
     
     @Test
     public void kuukausiSummaOikein() {
         
         saasto.setKuukausiMaara(50);
         assertEquals(50, saasto.getKuukausiMaara(), 0.009);
         assertEquals(20, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiMaara(8);
         assertEquals(8, saasto.getKuukausiMaara(), 0.009);
         assertEquals(125, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiMaara(16);
         assertEquals(16, saasto.getKuukausiMaara(), 0.009);
         assertEquals(62.5, saasto.getKuukausiSumma(), 0.009);
         
     }
     
     @Test
     public void eiNegatiivisiaKuukausimaaria() {
         saasto.setKuukausiMaara(-1);
         assertEquals(0, saasto.getKuukausiMaara(), 0.009);
         assertEquals(0, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiMaara(-34);
         assertEquals(0, saasto.getKuukausiMaara(), 0.009);
         assertEquals(0, saasto.getKuukausiSumma(), 0.009);
     }
     
     @Test
     public void eiNegatiivisiaKuukausittaissummia() {
         saasto.setKuukausiSumma(-1);
         assertEquals(0, saasto.getKuukausiMaara(), 0.009);
         assertEquals(0, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiSumma(-34.5);
         assertEquals(0, saasto.getKuukausiMaara(), 0.009);
         assertEquals(0, saasto.getKuukausiSumma(), 0.009);
     }
     
     @Test
     public void eiKokonaisSaastoaIsompaaKuukausieraa() {
         saasto.setKuukausiSumma(1000.1);
         assertEquals(0, saasto.getKuukausiMaara(), 0.009);
         assertEquals(0, saasto.getKuukausiSumma(), 0.009);
         
         saasto.setKuukausiSumma(1000);
         assertEquals(1, saasto.getKuukausiMaara(), 0.009);
         assertEquals(1000, saasto.getKuukausiSumma(), 0.009);
         
         
     }
     
     
}
