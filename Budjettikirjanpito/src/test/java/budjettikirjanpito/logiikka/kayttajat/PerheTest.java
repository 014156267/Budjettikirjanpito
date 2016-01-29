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
public class PerheTest {

    Perhe perhe;

    public PerheTest() {
        perhe = new Perhe();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        perhe.lisaaHenkilo(new Henkilo("Matti", "Meikäläinen"));
        perhe.lisaaHenkilo(new Henkilo("Katti", "Keikäläinen"));
        perhe.lisaaHenkilo(new Henkilo("Tatti", "Teikäläinen"));
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("Matti Meikäläinen\nKatti Keikäläinen\nTatti Teikäläinen\n", perhe.toString());
    }
    
    @Test
    public void lisaaHenkiloToimii() {
        perhe.lisaaHenkilo(new Henkilo("Paavo","Ruotsalainen"));
        assertEquals("Matti Meikäläinen\nKatti Keikäläinen\nTatti Teikäläinen\nPaavo Ruotsalainen\n", perhe.toString());
    }

}
