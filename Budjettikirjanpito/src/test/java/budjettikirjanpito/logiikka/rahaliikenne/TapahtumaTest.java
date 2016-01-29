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
public class TapahtumaTest {

    Tapahtuma tapahtuma;

    public TapahtumaTest() {
        tapahtuma = new Tapahtuma(10.5, "Shakkilaudan osto");
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
    public void toStringToimii() {

        assertEquals("Määrä: 10.5\nSelitys: Shakkilaudan osto", tapahtuma.toString());
    }
}
