package budjettikirjanpito.gui;

import budjettikirjanpito.database.Database;
import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Perhe;
import budjettikirjanpito.logiikka.kayttajat.Yritys;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Kayttajanlisays {

    /**
     * Metodi kysyy ohjelman käyttäjältä, haluaako tämä lisätä henkilön, perheen
     * vai yrityksen ja kutsuu saamansa syötteen perusteella kyseisen
     * käyttäjätyypin lisäysmetodia.
     *
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static final void uudenKayttajanLisays() throws IOException, FileNotFoundException, ClassNotFoundException {
        while (true) {
            System.out.println("\nHenkilo 1");
            System.out.println("Perhe 2");
            System.out.println("Yritys 3");
            System.out.println("Takaisin x");
            String tyyppi = Kayttoliittyma.lukija.nextLine();
            if (tyyppi.equals("1")) {
                uudenHenkilonLisays();
                Database.tietojenTallennus();
                break;
            } else if (tyyppi.equals("2")) {
                uudenPerheenLisays();
                break;
            } else if (tyyppi.equals("3")) {
                uudenYrityksenLisays();
                Database.tietojenTallennus();
                break;
            } else if (tyyppi.equals("x")) {
                break;
            } else {
                System.out.println("\nValitse jokin alla olevista "
                        + "vaihtoehdoista:");
            }
        }
    }

    /**
     * Metodi kysyy ohjelman käyttäjältä lisättävän henkilön tiedot, luo
     * Henkilo- olion ja lisää sen kayttajat-listaan. Luotu henkilö kirjataan
     * samalla sisään.
     *
     */
    public static final void uudenHenkilonLisays() throws IOException, FileNotFoundException, ClassNotFoundException {

        System.out.println("\nEtunimi: ");
        String etunimi = Kayttoliittyma.lukija.nextLine();
        System.out.println("\nSukunimi: ");
        String sukunimi = Kayttoliittyma.lukija.nextLine();
        Henkilo henkilo = new Henkilo(etunimi, sukunimi);
        System.out.println("\nAnna luotavalle henkilölle käyttäjätunnus: ");
        while (true) {
            String tunnus = Kayttoliittyma.lukija.nextLine();
            if (tunnuksenTarkistus(tunnus)) {
                henkilo.tunnus = tunnus;
                System.out.println("\nAnna luotavalle henkilölle salasana");
                String salasana = Kayttoliittyma.lukija.nextLine();
                henkilo.salasana = salasana;
                System.out.println("\nHenkilö " + henkilo + " luotu.");
                Kayttoliittyma.current = henkilo;
                break;
            }
            System.out.println("\nSyöttämäsi tunnus on käytössä. Anna toinen "
                    + "käyttäjätunnus: ");
        }
    }

    /**
     * Metodi luo aluksi perhe-olion, kysyy ohjelman käyttäjältä lisättävien
     * henkilöiden salasanoja ja lisää salasanoja vastaavat henkilöt perheeseen.
     * Kun käyttäjä on lisännyt haluamansa henkilöt luotavaan perheeseen, metodi
     * pyytää keksimään perheelle vielä uuden käyttäjätunnuksen ja salasanan
     * ennen kuin se lisätään kayttajat-listaan ja asetetaan currentiksi.
     *
     */
    public static final void uudenPerheenLisays() throws IOException, FileNotFoundException, ClassNotFoundException {
        Perhe perhe = new Perhe();
        while (true) {
            System.out.println("\nLisää henkilö perheeseen 1");
            System.out.println("Valmis x");
            String syote2 = Kayttoliittyma.lukija.nextLine();
            boolean luotu = false;
            if (syote2.equals("x")) {
                System.out.println("\nAnna perheellesi käyttäjätunnus: ");
                while (true) {
                    String tunnus = Kayttoliittyma.lukija.nextLine();
                    if (tunnuksenTarkistus(tunnus)) {
                        perhe.tunnus = tunnus;
                        System.out.println("\nAnna perheellesi salasana: ");
                        while (true) {
                            String salasana = Kayttoliittyma.lukija.nextLine();
                            perhe.salasana = salasana;
                            Kayttoliittyma.current = perhe;
                            System.out.println("\nPerhe luotu.");
                            Database.tietojenTallennus();
                            luotu = true;
                            break;
                        }
                        if (luotu) {
                            break;
                        }
                    } else {
                        System.out.println("\nAntamasi tunnus on käytössä."
                                + " Keksi toinen tunnus: ");
                    }
                }
                break;
            } else if (syote2.equals("1")) {
                System.out.println("\nSyötä lisättävän henkilön"
                        + " käyttäjätunnus: ");
                String tunnus = Kayttoliittyma.lukija.nextLine();
                boolean onnistui = false;
                if (Database.onkoHenkiloLuotu(tunnus)) {
                    Henkilo kayttaja = Database.tietynHenkilonTietojenLataus(tunnus);
                    onnistui = true;
                    System.out.println("\nAnna henkilön salasana: ");
                    String salasana = Kayttoliittyma.lukija.nextLine();
                    if (kayttaja.salasana.equals(salasana)) {
                        System.out.println("\n" + kayttaja + " lisätty perheeseen.");
                        perhe.lisaaHenkilo(kayttaja.tunnus);
                    } else {
                        System.out.println("\nVirheellinen salasana. Henkilöä"
                                + " ei lisätty.");
                    }

                }
                if (!onnistui) {
                    System.out.println("\nAntamaasi käyttäjätunnusta ei ole "
                            + "luotu.");
                }

            } else {
                System.out.println("\nSyötä 1 tai x.");
            }
        }
    }

    /**
     * Metodi kysyy ohjelman käyttäjältä lisättävän yrityksen tiedot, luo
     * Yritys- olion ja lisää sen kayttajat-listaan. Luotu yritys kirjataan
     * samalla sisään.
     */
    public static final void uudenYrityksenLisays() throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("\nAnna yrityksen nimi: ");
        String nimi = Kayttoliittyma.lukija.nextLine();
        System.out.println("\nAnna yrityksen y-tunnus: ");
        String ytunnus = Kayttoliittyma.lukija.nextLine();
        System.out.println("\nAnna yrityksellesi käyttäjätunnus: ");
        while (true) {
            String tunnus = Kayttoliittyma.lukija.nextLine();
            if (tunnuksenTarkistus(tunnus)) {
                System.out.println("\nAnna yrityksellesi salasana: ");
                String salasana = Kayttoliittyma.lukija.nextLine();
                Yritys yritys = new Yritys(nimi, ytunnus);
                yritys.tunnus = tunnus;
                yritys.salasana = salasana;
                System.out.println("\nYritys " + yritys + " luotu.");
                Kayttoliittyma.current = yritys;
                break;
            } else {
                System.out.println("\n Käyttäjätunnus käytössä, keksi toinen"
                        + " tunnus: ");
            }
        }
    }

    /**
     * Metodi tarkistaa, onko parametrina annettu tunnus jonkun kayttajat-listan
     * olion tunnus.
     *
     * @param s käyttäjän syöte
     *
     * @return onko syötettyä tunnusta olemassa.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static final boolean tunnuksenTarkistus(String s) throws IOException, FileNotFoundException, ClassNotFoundException {
        return (!Database.onkoKayttajaLuotu(s));

    }

}
