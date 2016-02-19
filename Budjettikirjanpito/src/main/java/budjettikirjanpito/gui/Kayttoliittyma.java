package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Perhe;
import budjettikirjanpito.logiikka.kayttajat.Yritys;
import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Käyttöliittymässä on lista käyttäjiä, joita ohjelman käyttäjä voi luoda.
 * Current on tällä hetkellä kirjautuneena oleva käyttäjä. Lisäksi
 * käyttöliittymä hoitaa desimaalilukujen muotoilun ja tietokannan (juuressa
 * sijaitseva kayttajat.ser) päivittämisen.
 */
public class Kayttoliittyma {

    private ArrayList<Kayttaja> kayttajat;
    private Kayttaja current;
    public Scanner lukija;
    private String tiedostonNimi;
    private static final DecimalFormat muotoilu = new DecimalFormat("###.##");

    public Kayttoliittyma() {
        kayttajat = new ArrayList<>();
        current = null;
        lukija = new Scanner(System.in);
        tiedostonNimi = "";
    }

    /**
     * kaynnista() on metodi, joka suorittaa tekstikäyttöliittymän. Aluksi
     * metodi lataa tietokannasta sinne aiemmilla käyttökerroilla talletetut
     * tiedot käyttäjät-listaan. Tämän jälkeen metodi tarjoaa ohjelman
     * käyttäjälle mahdollisuuden luoda uuden tunnuksen tai kirjautua sisään ja
     * sitten syöttää tuloja ja menoja ja seurata niitä. Lopuksi metodi
     * tallentaa muutokset tietokantaan käyttäjän valitessa niin.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public final void kaynnista() throws IOException,
            FileNotFoundException, ClassNotFoundException {
        tietojenLataus();
        System.out.println("Tervetuloa!");
        while (true) {
            System.out.println("");
            if (current == null) {
                System.out.println("Lisää käyttäjä syöttämällä 1");
                System.out.println("Kirjaudu sisään syöttämällä 2");
                System.out.println("Lopeta syöttämällä x");
                String syote = lukija.nextLine();
                if (syote.equals("x")) {
                    break;
                } else if (syote.equals("1")) {
                    uudenKayttajanLisays();
                } else if (syote.equals("2")) {
                    sisaanKirjautuminen();
                } else {
                    System.out.println("Anna kunnon syöte.");
                }
            } else {

                System.out.println("Lisää tapahtuma syöttämällä 1");
                System.out.println("Poista tapahtuma syöttämällä 2");
                System.out.println("Tietoja tapahtumista syöttämällä 3");
                System.out.println("Kirjaudu ulos syöttämällä 4");
                System.out.println("Poista käyttäjä syöttämällä 5");
                System.out.println("Lopeta syöttämällä x");
                String syote = lukija.nextLine();
                if (syote.equals("x")) {
                    tietojenTallennusEhdolla();
                    break;
                } else if (syote.equals("1")) {
                    lisaaTapahtuma();
                } else if (syote.equals("2")) {
                    poistaTapahtuma();
                } else if (syote.equals("3")) {
                    tapahtumaToiminnot();
                } else if (syote.equals("4")) {
                    current = null;
                    tietojenTallennusEhdolla();
                } else if (syote.equals("5")) {
                    poistaKayttaja();
                } else {
                    System.out.println("Syötä kelvollinen syöte.");
                }
            }
        }
    }

    /**
     * Metodi mahdollistaa Ohjelman käyttäjän sisäänkirjautumisen.
     */
    public final void sisaanKirjautuminen() {
        System.out.println("Syötä salasanasi: ");
        String salasana = lukija.nextLine();
        for (Kayttaja k : kayttajat) {
            if (k.salasana.equals(salasana)) {
                current = k;
                System.out.println("Tervetuloa " + k.toString());
                break;
            }
        }
        if (current == null) {
            System.out.println("Kirjautuminen epäonnistui.");
        }
    }

    /**
     * Metodi suorittuu kaynnista-metodin toimesta ja lataa tietokannan sisällön
     * kayttajat-listaan.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public final void tietojenLataus() throws FileNotFoundException,
            IOException, ClassNotFoundException {
        if (new File("kayttajat.ser").exists()) {
            FileInputStream fis = new FileInputStream("kayttajat.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            kayttajat = (ArrayList<Kayttaja>) ois.readObject();
            ois.close();
            fis.close();
        }
    }

    /**
     * Metodi tallentaa kayttajat-listan kayttajat.ser-tiedostoon. Metodi
     * suoritetaan, mikäli ohjelman käyttäjä haluaa niin.
     *
     * @throws java.io.FileNotFoundException
     */
    public final void tietojenTallennusEhdolla() throws IOException {
        System.out.println("Tallenetaanko muutokset? (k/e)");
        while (true) {
            String vastaus = lukija.nextLine();
            if (vastaus.equals("k")) {
                System.out.println("Tallennetaan...");
                tietojenTallennus();
                System.out.println("Tallennettu.");
                break;
            } else if (vastaus.equals("e")) {
                break;
            } else {
                System.out.println("Vastaa k tai e.");
            }
        }
    }

    /**
     * Metodi tallentaa kayttajat-listan kayttajat.ser-tiedostoon.
     *
     * @throws java.io.FileNotFoundException
     */
    public final void tietojenTallennus() throws IOException {
        FileOutputStream fos = new FileOutputStream("kayttajat.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(kayttajat);
        oos.close();
        fos.close();
    }

    /**
     * Metodi kysyy, minkälaisen tapahtuman ohjelman käyttäjä haluaa lisätä ja
     * kutsuu kyseisen tapahtumatyypin lisäysmetodia. Esim. jos ohjelman
     * käyttäjä syöttää 1 eli haluaa lisätä uuden kertaostoksen, kutsuu tämä
     * metodi syötteen perusteella metodia lisaaOstos().
     */
    public final void lisaaTapahtuma() {
        System.out.println("Valitse tapahtuma: ");
        System.out.println("1. Kertaostos");
        System.out.println("2. Tulo");
        System.out.println("3. Velka");
        System.out.println("4. Säästö");
        String luku = lukija.nextLine();
        if (luku.equals("1")) {
            lisaaOstos();
        } else if (luku.equals("2")) {
            lisaaTulo();
        } else if (luku.equals("3")) {
            lisaaVelka();
        } else if (luku.equals("4")) {
            lisaaSaasto();
        } else {
            System.out.println("Anna kelvollinen syöte.");
        }
    }

    /**
     * Metodi kysyy, minkälaisen tapahtuman ohjelman käyttäjä haluaa poistaa ja
     * kutsuu kyseisen tapahtumatyypin poistometodia. Esim. jos ohjelman
     * käyttäjä syöttää 1 eli haluaa poistaa uuden kertaostoksen, kutsuu tämä
     * metodi syötteen perusteella metodia poistaOstos().
     */
    public final void poistaTapahtuma() {
        System.out.println("Valitse tapahtuma: ");
        System.out.println("1. Kertaostos");
        System.out.println("2. Tulo");
        System.out.println("3. Velka");
        System.out.println("4. Säästö");
        int luku = kysyKokonaisluku(1, 4);
        if (luku == 1) {
            poistaOstos();
        } else if (luku == 2) {
            poistaTulo();
        } else if (luku == 3) {
            poistaVelka();
        } else if (luku == 4) {
            poistaSaasto();
        }
    }

    /**
     * Metodi kysyy syötettävän ostoksen tiedot ja lisää ostoksen currentin
     * ostoksiin.
     */
    public final void lisaaOstos() {
        System.out.println("Ostoksen suuruus: ");
        double maara = kysyLuku(0, 999999999);
        System.out.println("Selitys: ");
        String selitys = lukija.nextLine();
        Ostos ostos = new Ostos(maara, selitys);
        current.tapahtumat.add(ostos);
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman ostoksen currentin
     * tapahtumista.
     */
    public final void poistaOstos() {
        int i = 1;
        for (Tapahtuma ostos : current.getOstokset()) {
            System.out.print(i + ". " + ostos);
            i++;
        }
        System.out.println("Syötä ostoksen järjestysnumero: ");
        int syote = kysyKokonaisluku(1, current.getOstokset().size() + 1);
        current.tapahtumat.remove(current.getOstokset().get(syote - 1));
        System.out.println("Ostos poistettu.");
    }

    /**
     * Metodi kysyy lisättävän tulon tiedot, luo tietojen perusteella Tulo-olion
     * ja lisää sen currentin tapahtumiin.
     */
    public final void lisaaTulo() {
        System.out.println("Maksaja: ");
        String maksaja = lukija.nextLine();
        System.out.println("Tulon suuruus: ");
        double maara = kysyLuku(0, 999999999);
        System.out.println("Selitys: ");
        String selitys = lukija.nextLine();
        Tulo tulo = new Tulo(maksaja, maara, selitys);
        current.tapahtumat.add(tulo);
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman tulon currentin
     * tapahtumista.
     */
    public void poistaTulo() {
        int i = 1;
        for (Tapahtuma tulo : current.getTulot()) {
            System.out.print(i + ". " + tulo);
            i++;
        }
        System.out.println("Syötä tulon järjestysnumero: ");
        int syote = kysyKokonaisluku(1, current.getTulot().size() + 1);
        current.tapahtumat.remove(current.getTulot().get(syote - 1));
        System.out.println("Tulo poistettu.");
    }

    /**
     * Metodi kysyy lisättävän velan tiedot, luo tietojen perusteella
     * Velka-olion ja lisää sen currentin tapahtumiin.
     */
    public final void lisaaVelka() {
        System.out.println("Velan suuruus: ");
        double maara = kysyLuku(0, 999999999);
        System.out.println("Vuosikorko (prosentteina): ");
        double korko = kysyLuku(0, 999999999);
        System.out.println("Lyhennysaika (kuukausina): ");
        int kk = 0;
        while (true) {
            kk = kysyKokonaisluku(0, 999999999);
            if (kk > 0) {
                break;
            }
            System.out.println("Syötä positiivinen kokonaisluku.");
        }
        Velka velka = new Velka(maara, "", kk, korko);
        System.out.println("Velan aihe: ");
        String aihe = lukija.nextLine();
        velka.setAihe(aihe);
        System.out.println("Velan selitys: ");
        String selitys = lukija.nextLine();
        velka.setSelitys(selitys);
        current.tapahtumat.add(velka);
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman velan currentin
     * tapahtumista.
     */
    public final void poistaVelka() {
        int i = 1;
        for (Tapahtuma velka : current.getVelat()) {
            System.out.print(i + ". " + velka);
            i++;
        }
        System.out.println("Syötä velan järjestysnumero: ");
        int syote = kysyKokonaisluku(1, current.getVelat().size() + 1);
        current.tapahtumat.remove(current.getVelat().get(syote - 1));
        System.out.println("Velka poistettu.");
    }

    /**
     * Metodi kysyy lisättävän säästön tiedot, luo tietojen perusteella
     * Saasto-olion ja lisää sen currentin tapahtumiin.
     */
    public final void lisaaSaasto() {
        System.out.println("Säästön suuruus: ");
        double maara = kysyLuku(0, 999999999);
        System.out.println("Säästön selitys: ");
        String selitys = lukija.nextLine();
        Saasto saasto = new Saasto(maara, selitys);
        System.out.println("Haluatko määritellä kuukausisumman (syötä 1) vai"
                + " säästämisjakson pituuden (syötä 2)?");
        String syote = lukija.nextLine();
        while (true) {
            if (syote.equals("1")) {
                System.out.println("Syötä kuukaudessa maksettava määrä: ");
                double summa = kysyLuku(0, 999999999);
                saasto.setKuukausiSumma(summa);
                break;
            } else if (syote.equals("2")) {
                System.out.println("Syötä kuukausien määrä: ");
                int kk = kysyKokonaisluku(1, 999999999);
                saasto.setKuukausiMaara(kk);
                break;
            } else {
                System.out.println("Syötä joko 1 tai 2.");
            }

            current.tapahtumat.add(saasto);
        }
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman säästön currentin
     * tapahtumista.
     */
    public final void poistaSaasto() {
        int i = 1;
        for (Tapahtuma saasto : current.getSaastot()) {
            System.out.print(i + ". " + saasto);
            i++;
        }
        System.out.println("Syötä säästön järjestysnumero: ");
        int syote = kysyKokonaisluku(1, current.getSaastot().size() + 1);
        current.tapahtumat.remove(current.getSaastot().get(syote - 1));
        System.out.println("Säästö poistettu.");
    }
    
    /**
     * Mikäli etodi käy läpi käyttäjän valitsemat currentin tapahtumat ja palauttaa 
     * ne pitkänä merkkijonona.
     */

    public final void tapahtumaToiminnot() {
        System.out.println("Valitse seuraavista:");
        System.out.println("");
        System.out.println("Kaikki tietyn tyyppiset tapahtumat syöttämällä 2");
        System.out.println("Tulosta kaikki tapahtumat syöttämällä 3");
        System.out.println("Takaisin syöttämällä x");
        String syote = lukija.nextLine();
        if (syote.equals("x")) {
            return;
        } else if (syote.equals("1")) {
            yksittaisenTapahtumanHallinta();
        } else if (syote.equals("2")) {
        System.out.println("Mitä seuraavista haluat tulostaa?");
        System.out.println("Kertaostokset, syötä 1");
        System.out.println("Tulot, syötä 2");
        System.out.println("Velat, syötä 3");
        System.out.println("Säästöt, syötä 4");
            int luku = kysyKokonaisluku(1, 4);
            if (luku == 1) {
               listaaTapahtumat(true, false, false, false); 
            } else if (luku == 2) {
                listaaTapahtumat(false, false, true, false);
            } else if (luku == 3) {
                listaaTapahtumat(false, true, false, false);
            } else if (luku == 4) {
                listaaTapahtumat(false, false, false, true);
            }
        } else if (syote.equals("3")) {
            listaaTapahtumat(true, true, true, true);
        } else {
            System.out.println("Anna kunnon syöte.");
        }
    }

    /**
     * @param ostot on true, mikäli käyttäjä haluaa ostonsa tulostettavan
     * @param velat on true, mikäli käyttäjä haluaa velkansa tulostettavan
     * @param tulot on true, mikäli käyttäjä haluaa tulonsa tulostettavan
     * @param saastot on true, mikäli käyttäjä haluaa säästönsä tulostettavan
     *
     * Metodi käy läpi kaikki currentin tapahtumat ja tulostaa ne pitkänä
     * merkkijonona.
     */
    public final void listaaTapahtumat(boolean ostot, boolean velat,
            boolean tulot, boolean saastot) {
        String tulostettava = "";
        if (velat) {
            if (current.getVelat().isEmpty()) {
                tulostettava += "Ei velkoja. \n";
            } else {
                tulostettava += "Velat: \n";
                for (Tapahtuma c : current.getVelat()) {
                    tulostettava += c.toString() + " \n";
                }
            }
        }
        if (tulot) {
            if (current.getTulot().isEmpty()) {
                tulostettava += "Ei tuloja. \n";
            } else {
                tulostettava += "Tulot: \n";
                for (Tapahtuma c : current.getTulot()) {
                    tulostettava += c.toString() + " \n";
                }
            }
        }
        if (saastot) {
            if (current.getSaastot().isEmpty()) {
                tulostettava += "Ei säästöjä. \n";
            } else {
                tulostettava += "Säästöt: \n";
                for (Tapahtuma c : current.getSaastot()) {
                    tulostettava += c.toString() + " \n";
                }
            }
        }
        if (ostot) {
            if (current.getOstokset().isEmpty()) {
                tulostettava += "Ei ostoksia. \n";
            } else {
                tulostettava += "Ostokset: \n";
                for (Tapahtuma c : current.getOstokset()) {
                    tulostettava += c.toString() + " \n";
                }
            }
        }
        System.out.println(tulostettava);
    }

    public final void yksittaisenTapahtumanHallinta() {
        System.out.println("Valitse tapahtumasi");
    }
    
    /**
     * Metodi tarkistaa, onko parametrina annettu salasana jonkun
     * kayttajat-listan olion salasana.
     *
     * @return onko kenelläkään syötettyä salasanaa.
     */
    public final boolean salasananTarkistus(String s) {
        for (Kayttaja k : kayttajat) {
            if (k.salasana.equals(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodi pyytää vanhaa salasanaa, jota ohjelma vertaa kirjautuneen
     * käyttäjän syöttämään salasanaan. Tämän jälkeen ohjelma pyytää uutta
     * salasanaa ja liittää sen kirjautuneen käyttäjän salasanaksi.
     */
    public final void vaihdaSalasana(String salasana) {
        System.out.println("Syötä vanha salasana: ");

        String vanha = lukija.nextLine();
        {
            if (current.salasana.equals(vanha)) {
                System.out.println("Syötä uusi salasana: ");
                String uusi = lukija.nextLine();
                current.setSalasana(uusi);
            } else {
                System.out.println("Salasana on virheellinen.");
            }

        }

    }

    /**
     * Metodi kysyy ohjelman käyttäjältä, haluaako tämä lisätä henkilön, perheen
     * vai yrityksen ja kutsuu saamansa syötteen perusteella kyseisen
     * käyttäjätyypin lisäysmetodia.
     */
    public final void uudenKayttajanLisays() {
        while (true) {
            System.out.println("Henkilo 1");
            System.out.println("Perhe 2");
            System.out.println("Yritys 3");
            System.out.println("Takaisin x");
            String tyyppi = lukija.nextLine();
            if (tyyppi.equals("1")) {
                uudenHenkilonLisays();
                break;
            } else if (tyyppi.equals("2")) {
                uudenPerheenLisays();
                break;
            } else if (tyyppi.equals("3")) {
                uudenYrityksenLisays();
                break;
            } else if (tyyppi.equals("x")) {
                break;
            } else {
                System.out.println("Valitse jokin alla olevista "
                        + "vaihtoehdoista:");
            }
        }
    }

    /**
     * Metodi kysyy ohjelman käyttäjältä poistettavan käyttäjän salasanaa ja
     * mikäli salasana on tällä hetkellä kirjautuneena olevan käyttäjän
     * salasana, käyttäjä kirjautuu ulos. Tämän jälkeen salasanaa vastaava
     * käyttäjä poistetaan. Mikäli salasanaa ei ole kenelläkään luodulla
     * käyttäjällä, metodi ilmoittaa salasanan virheellisyydestä ja palaa
     * takaisin valikkoon, josta metodiin päädyttiin.
     *
     * @throws java.io.IOException
     */
    public final void poistaKayttaja() throws IOException {
        System.out.println("Syötä poistettavan käyttäjän salasana: ");
        String salasana = lukija.nextLine();
        boolean onnistui = false;
        for (Kayttaja kayttaja : kayttajat) {
            if (kayttaja.salasana.equals(salasana)) {
                if (current == kayttaja) {
                    current = null;
                }
                kayttajat.remove(kayttaja);
                tietojenTallennus();
                System.out.println("Käyttäjä poistettu.");
                onnistui = true;
                break;
            }
        }
        if (!onnistui) {
            System.out.println("Salasana on virheellinen.");
        }
    }

    /**
     * Metodi kysyy ohjelman käyttäjältä lisättävän henkilön tiedot, luo
     * Henkilo- olion ja lisää sen kayttajat-listaan. Luotu henkilö kirjataan
     * samalla sisään.
     */
    public final void uudenHenkilonLisays() {

        System.out.println("Etunimi: ");
        String etunimi = lukija.nextLine();
        System.out.println("Sukunimi: ");
        String sukunimi = lukija.nextLine();
        Henkilo henkilo = new Henkilo(etunimi, sukunimi);
        System.out.println("Anna salasana: ");
        while (true) {
            String salasana = lukija.nextLine();
            if (salasananTarkistus(salasana)) {
                henkilo.salasana = salasana;
                System.out.println("Henkilö " + henkilo + " luotu.");
                kayttajat.add(henkilo);
                current = henkilo;
                break;
            }
            System.out.println("Salasana käytössä. Anna toinen salasana: ");
        }
    }

    /**
     * Metodi luo aluksi perhe-olion, kysyy ohjelman käyttäjältä lisättävien
     * henkilöiden salasanoja ja lisää salasanoja vastaavat henkilöt perheeseen.
     * Kun käyttäjä on lisännyt haluamansa henkilöt luotavaan perheeseen, metodi
     * pyytää keksimään perheelle vielä uuden salasanan ennen kuin se lisätään
     * kayttajat-listaan ja asetetaan currentiksi.
     */
    public final void uudenPerheenLisays() {
        Perhe perhe = new Perhe();
        while (true) {
            System.out.println("Lisää henkilö perheeseen 1");
            System.out.println("Valmis x");
            String syote2 = lukija.nextLine();
            if (syote2.equals("x")) {
                System.out.println("Anna perheellesi salasana: ");
                while (true) {
                    String salasana = lukija.nextLine();
                    if (salasananTarkistus(salasana)) {
                        perhe.salasana = salasana;
                        kayttajat.add(perhe);
                        current = perhe;
                        System.out.println("Perhe luotu.");
                        break;
                    }
                    System.out.println("Salasana käytössä. Anna toinen"
                            + " salasana: ");
                }
                break;
            } else if (syote2.equals("1")) {
                System.out.println("Henkilön salasana: ");
                String salasana = lukija.nextLine();
                boolean onnistui = false;
                for (Kayttaja kayttaja : kayttajat) {
                    if (kayttaja.salasana.equals(salasana)
                            && kayttaja instanceof Henkilo) {
                        onnistui = true;
                        System.out.println(kayttaja + " lisätty perheeseen.");
                        perhe.lisaaHenkilo((Henkilo) kayttaja);
                        break;
                    }
                }
                if (!onnistui) {
                    System.out.println("Antamallasi salasanalla ei löydy "
                            + "henkilöä.");
                }

            } else {
                System.out.println("Syötä 1 tai x.");
            }
        }
    }

    /**
     * Metodi kysyy ohjelman käyttäjältä lisättävän yrityksen tiedot, luo
     * Yritys- olion ja lisää sen kayttajat-listaan. Luotu yritys kirjataan
     * samalla sisään.
     */
    public final void uudenYrityksenLisays() {
        System.out.println("Anna yrityksen nimi: ");
        String nimi = lukija.nextLine();
        System.out.println("Anna yrityksen y-tunnus: ");
        String ytunnus = lukija.nextLine();
        System.out.println("Anna yrityksellesi salasana: ");
        String salasana = lukija.nextLine();
        Yritys yritys = new Yritys(nimi, ytunnus);
        yritys.salasana = salasana;
        System.out.println("Yritys " + yritys + " luotu.");
        kayttajat.add(yritys);
        current = yritys;
    }

    /**
     * @param a luvun alaraja
     * @param b luvun yläraja
     *
     * Tämä apumetodi kysyy ohjelman käyttäjältä lukua, muokkaa sen
     * desimaaliluvuksi ja heittää poikkeuksen, mikäli ohjelman käyttäjä syöttää
     * jotain muuta kuin välillä [a,b] luvun.
     *
     * @return ohjelman käyttäjän syöttämä luku
     */
    public final double kysyLuku(int a, int b) {
        while (true) {
            try {
                double luku = Double.parseDouble(lukija.nextLine().replace(",", "."));
                if (luku >= a && luku <= b) {
                    return luku;
                } else {
                    System.out.println("Syötä luku, joka on välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("Syötä kunnollinen luku!");
            }
        }
    }

    /**
     * @param a luvun alaraja
     * @param b luvun yläraja
     *
     * Tämä apumetodi kysyy ohjelman käyttäjältä lukua ja heittää poikkeuksen,
     * mikäli ohjelman käyttäjä syöttää jotain muuta kuin kokonaisluvun.
     *
     * @return ohjelman käyttäjän syöttämä luku
     */
    public final int kysyKokonaisluku(int a, int b) {
        while (true) {
            try {
                int luku = Integer.parseInt(lukija.nextLine());
                if (luku >= a && luku <= b) {
                    return luku;
                } else {
                    System.out.println("Syötä luku, joka on välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("Syötä kunnollinen luku!");
            }
        }
    }

}
