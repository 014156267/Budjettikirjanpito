package budjettikirjanpito.logiikka.kayttajat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import budjettikirjanpito.gui.Kayttoliittyma;
import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    Henkilo alfa;
    Henkilo beetta;
    Henkilo cecilia;

    public PerheTest() {
        perhe = new Perhe();
        alfa = new Henkilo("Matti", "Meikäläinen");
        alfa.tunnus = "masa";
        beetta = new Henkilo("Katti", "Keikäläinen");
        beetta.tunnus = "kasa";
        cecilia = new Henkilo("Tatti", "Teikäläinen");
        cecilia.tunnus = "tasa";
    }

    public final void tietojenTallennus(String tunnus, Henkilo h) throws IOException {
        FileOutputStream fos = new FileOutputStream(tunnus + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(h);
        oos.close();
        fos.close();
    }

    public static final void tietojenPoisto(String tunnus) {
        File f = new File(tunnus + ".ser");
        f.delete();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        perhe.lisaaHenkilo("masa");
        perhe.lisaaHenkilo("kasa");
        perhe.lisaaHenkilo("tasa");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void toStringToimii() throws IOException {
        tietojenTallennus("masa", alfa);
        tietojenTallennus("kasa", beetta);
        tietojenTallennus("tasa", cecilia);
        assertEquals("Perhe: \n\nMatti Meikäläinen\nKatti Keikäläinen\nTatti Teikäläinen", perhe.toString());
        tietojenPoisto("masa");
        tietojenPoisto("kasa");
        tietojenPoisto("tasa");
    }

    @Test
    public void lisaaHenkiloToimii() throws IOException {
        tietojenTallennus("masa", alfa);
        tietojenTallennus("kasa", beetta);
        tietojenTallennus("tasa", cecilia);
        Henkilo paavo = new Henkilo("Paavo", "Ruotsalainen");
        paavo.tunnus = "pave";
        tietojenTallennus("pave", paavo);
        perhe.lisaaHenkilo(paavo.tunnus);
        assertEquals("Perhe: \n\nMatti Meikäläinen\nKatti Keikäläinen\nTatti Teikäläinen\nPaavo Ruotsalainen", perhe.toString());
        tietojenPoisto("masa");
        tietojenPoisto("kasa");
        tietojenPoisto("tasa");
        tietojenPoisto("pave");
    }

    @Test
    public void velatYhteensaToimii() throws IOException, FileNotFoundException, ClassNotFoundException {
        Henkilo henkilo1 = new Henkilo("Pertti", "Järvinen");
        Henkilo henkilo2 = new Henkilo("Markku", "Järvinen");
        Henkilo henkilo3 = new Henkilo("Pertti", "Kenkäniemi");
        henkilo1.tunnus = "pera";
        henkilo2.tunnus = "make";
        henkilo3.tunnus = "pertsa";
        perhe.lisaaHenkilo(henkilo1.tunnus);
        perhe.lisaaHenkilo(henkilo2.tunnus);
        perhe.lisaaHenkilo(henkilo3.tunnus);
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
        tietojenTallennus("pera", henkilo1);
        tietojenTallennus("make", henkilo2);
        tietojenTallennus("pertsa", henkilo3);
        assertEquals(1250.00, perhe.getHenkiloidenVelatYhteensa(), 0.009);
        tietojenPoisto("pera");
        tietojenPoisto("make");
        tietojenPoisto("pertsa");
    }

    @Test
    public void TulotYhteensaToimii() throws IOException, FileNotFoundException, ClassNotFoundException {
        Henkilo henkilo1 = new Henkilo("Pertti", "Järvinen");
        Henkilo henkilo2 = new Henkilo("Markku", "Järvinen");
        Henkilo henkilo3 = new Henkilo("Pertti", "Kenkäniemi");
        henkilo1.tunnus = "pera";
        henkilo2.tunnus = "make";
        henkilo3.tunnus = "pertsa";
        perhe.lisaaHenkilo(henkilo1.tunnus);
        perhe.lisaaHenkilo(henkilo2.tunnus);
        perhe.lisaaHenkilo(henkilo3.tunnus);
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
        tietojenTallennus("pera", henkilo1);
        tietojenTallennus("make", henkilo2);
        tietojenTallennus("pertsa", henkilo3);
        assertEquals(1129.42, perhe.getHenkiloidenTulotYhteensa(), 0.009);
        tietojenPoisto("pera");
        tietojenPoisto("make");
        tietojenPoisto("pertsa");
    }

    @Test
    public void OstotYhteensaToimii() throws IOException, FileNotFoundException, ClassNotFoundException {
        Henkilo henkilo1 = new Henkilo("Pertti", "Järvinen");
        Henkilo henkilo2 = new Henkilo("Markku", "Järvinen");
        Henkilo henkilo3 = new Henkilo("Pertti", "Kenkäniemi");
        henkilo1.tunnus = "pera";
        henkilo2.tunnus = "make";
        henkilo3.tunnus = "pertsa";
        perhe.lisaaHenkilo(henkilo1.tunnus);
        perhe.lisaaHenkilo(henkilo2.tunnus);
        perhe.lisaaHenkilo(henkilo3.tunnus);
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
        tietojenTallennus("pera", henkilo1);
        tietojenTallennus("make", henkilo2);
        tietojenTallennus("pertsa", henkilo3);
        assertEquals(1300.45, perhe.getHenkiloidenOstotYhteensa(), 0.009);
        tietojenPoisto("pera");
        tietojenPoisto("make");
        tietojenPoisto("pertsa");
    }

}
