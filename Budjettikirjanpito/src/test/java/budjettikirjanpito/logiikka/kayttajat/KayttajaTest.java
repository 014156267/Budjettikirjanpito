/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.util.ArrayList;
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
public class KayttajaTest {

    Henkilo henkilo;

    public KayttajaTest() {
        henkilo = new Henkilo("Matti", "Pekkanen");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        henkilo.tapahtumat.add(new Velka(5000, "Kävin syömässä", 25, 1));
        henkilo.tapahtumat.add(new Velka(1000, "Kävin juomassa", 40, 2));
        henkilo.tapahtumat.add(new Ostos(3000, "Pesukone itselle ja viidelle naapurille, sai halvalla"));
        henkilo.tapahtumat.add(new Saasto(200, "saasto"));
        henkilo.tapahtumat.add(new Tulo("Hyy", 3000, "hyvää työtä"));
        henkilo.tapahtumat.add(new Tulo("Hyy", 3002, "hyvää työtä vieläkin"));

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getVelatPelaa() {
        ArrayList<Tapahtuma> velat = new ArrayList();
        for (int i = 0; i < 2; i++) {
            velat.add(henkilo.tapahtumat.get(i));
        }
        assertEquals(velat, henkilo.getVelat());
        henkilo.tapahtumat.remove(0);
        henkilo.tapahtumat.remove(3);
        ArrayList<Tapahtuma> velat2 = new ArrayList();
        velat2.add(henkilo.tapahtumat.get(0));
        assertEquals(velat2, henkilo.getVelat());
        
        
    }

    @Test
    public void getSaastotPelaa() {
        ArrayList<Tapahtuma> saastot = new ArrayList();
        saastot.add(henkilo.tapahtumat.get(3));
        assertEquals(saastot, henkilo.getSaastot());
    }
    
    @Test
    public void getTulotPelaa() {
        ArrayList<Tapahtuma> tulot = new ArrayList();
        tulot.add(henkilo.tapahtumat.get(4));
        tulot.add(henkilo.tapahtumat.get(5));
        assertEquals(tulot, henkilo.getTulot());
    }
    
    @Test
    public void getOstotPelaa() {
        ArrayList<Tapahtuma> ostot = new ArrayList();
        ostot.add(henkilo.tapahtumat.get(2));
        assertEquals(ostot, henkilo.getOstokset());

    }
    
    @Test
    public void getKuukaudenTulotYhteensaPelaa() {
        Tulo tulo = new Tulo("Mummu", 56.23, "kauppareissu");
        henkilo.tapahtumat.add(tulo);
        assertEquals(6058.23, henkilo.getKuukaudenTulotYhteensa(), 0.009);
        Tulo tulo2 = new Tulo("Mummu", 50.40, "kauppareissu taas");
        henkilo.tapahtumat.add(tulo2);
        assertEquals(6108.63, henkilo.getKuukaudenTulotYhteensa(), 0.009);
    }
    
    @Test
    public void getKuukaudenMenotYhteensaPelaa() {
        Velka velka = new Velka(100, "kauppareissu", 10, 10);
        henkilo.tapahtumat.add(velka);
        assertEquals(204.166666 + 26.666666 + 3000 + 10.833333, henkilo.getKuukaudenMenotYhteensa(), 0.009);
        Ostos ostos = new Ostos(60, "laukku");
        henkilo.tapahtumat.add(ostos);
        assertEquals(60 + 204.166666 + 26.666666 + 3000 + 10.833333, henkilo.getKuukaudenMenotYhteensa(), 0.009);
    }
    
    @Test
    public void rahatilanneToimii() {
        Tulo tulo = new Tulo("Mummu", 56.23, "kauppareissu");
        henkilo.tapahtumat.add(tulo);
        Tulo tulo2 = new Tulo("Mummu", 50.40, "kauppareissu taas");
        henkilo.tapahtumat.add(tulo2);
        Velka velka = new Velka(100, "kauppareissu", 10, 10);
        henkilo.tapahtumat.add(velka);
        Ostos ostos = new Ostos(60, "laukku");
        henkilo.tapahtumat.add(ostos);
        assertEquals(6108.63 - (60 + 204.166666 + 26.666666 + 3000 + 10.8333333), henkilo.getRahatilanne(), 0.009);
    }
    
    
}
