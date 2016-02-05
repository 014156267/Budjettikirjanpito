package budjettikirjanpito.logiikka.kayttajat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
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
        assertEquals("Perhe: \n\nMatti Meikäläinen\nKatti Keikäläinen\nTatti Teikäläinen", perhe.toString());
    }
    
    @Test
    public void lisaaHenkiloToimii() {
        perhe.lisaaHenkilo(new Henkilo("Paavo","Ruotsalainen"));
        assertEquals("Perhe: \n\nMatti Meikäläinen\nKatti Keikäläinen\nTatti Teikäläinen\nPaavo Ruotsalainen", perhe.toString());
    }
    
    @Test
    public void velatYhteensaToimii() {
        Henkilo henkilo1 = new Henkilo("Pertti", "Järvinen");
        Henkilo henkilo2 = new Henkilo("Markku", "Järvinen");
        Henkilo henkilo3 = new Henkilo("Pertti", "Kenkäniemi");
        perhe.lisaaHenkilo(henkilo1);
        perhe.lisaaHenkilo(henkilo2);
        perhe.lisaaHenkilo(henkilo3);
        Velka velka0 = new Velka(200, "Puhelin", 14, 2.5);
        Velka velka1 = new Velka(250, "Puhelin", 14, 2.5);
        Velka velka2 = new Velka(250, "Puhelin", 14, 2.5);
        Velka velka3 = new Velka(300, "Puhelin", 14, 2.5);
        Velka velka4 = new Velka(250, "Puhelin", 14, 2.5);
        henkilo1.tapahtumat.add(velka0);
        henkilo1.tapahtumat.add(velka1);
        henkilo3.tapahtumat.add(velka2);
        henkilo2.tapahtumat.add(velka3);
        henkilo2.tapahtumat.add(velka4);
        assertEquals(1250.00, perhe.getHenkiloidenVelatYhteensa(), 0.009);
    }
    
    @Test
    public void TulotYhteensaToimii() {
        Henkilo henkilo1 = new Henkilo("Pertti", "Järvinen");
        Henkilo henkilo2 = new Henkilo("Markku", "Järvinen");
        Henkilo henkilo3 = new Henkilo("Pertti", "Kenkäniemi");
        perhe.lisaaHenkilo(henkilo1);
        perhe.lisaaHenkilo(henkilo2);
        perhe.lisaaHenkilo(henkilo3);
        Tulo tulo0 = new Tulo("Kone Oy", 200, "Puhelin");
        Tulo tulo1 = new Tulo("Kone Oy", 200, "Puhelin");
        Tulo tulo2 = new Tulo("Kone Oy", 500, "Puhelin");
        Tulo tulo3 = new Tulo("Kone Oy", 200, "Puhelin");
        Tulo tulo4 = new Tulo("Kone Oy", 29.42, "Puhelin");
        henkilo1.tapahtumat.add(tulo0);
        henkilo1.tapahtumat.add(tulo2);
        henkilo2.tapahtumat.add(tulo1);
        henkilo2.tapahtumat.add(tulo3);
        henkilo3.tapahtumat.add(tulo4);
        assertEquals(1129.42, perhe.getHenkiloidenTulotYhteensa(), 0.009);
    }
    
    @Test
    public void OstotYhteensaToimii() {
        Henkilo henkilo1 = new Henkilo("Pertti", "Järvinen");
        Henkilo henkilo2 = new Henkilo("Markku", "Järvinen");
        Henkilo henkilo3 = new Henkilo("Pertti", "Kenkäniemi");
        perhe.lisaaHenkilo(henkilo1);
        perhe.lisaaHenkilo(henkilo2);
        perhe.lisaaHenkilo(henkilo3);
        Ostos ostos0 = new Ostos(300.45, "Puhelin");
        Ostos ostos1 = new Ostos(250, "Puhelin");
        Ostos ostos2 = new Ostos(250, "Puhelin");
        Ostos ostos3 = new Ostos(250, "Puhelin");
        Ostos ostos4 = new Ostos(250, "Puhelin");
        henkilo1.tapahtumat.add(ostos0);
        henkilo1.tapahtumat.add(ostos1);
        henkilo2.tapahtumat.add(ostos2);
        henkilo2.tapahtumat.add(ostos3);
        henkilo3.tapahtumat.add(ostos4);
        assertEquals(1300.45, perhe.getHenkiloidenOstotYhteensa(), 0.009);
    }

}
