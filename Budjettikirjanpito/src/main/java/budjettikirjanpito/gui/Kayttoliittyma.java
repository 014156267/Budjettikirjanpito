package budjettikirjanpito.gui;

import budjettikirjanpito.database.Database;
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

    public static Kayttaja current;
    public static Scanner lukija = new Scanner(System.in);
    private String tiedostonNimi;
    public static DecimalFormat muotoilu = new DecimalFormat("###.##");

    public Kayttoliittyma() {
        current = null;
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
    public static final void kaynnista() throws IOException,
            FileNotFoundException, ClassNotFoundException {
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
                    Kayttajanlisays.uudenKayttajanLisays();
                } else if (syote.equals("2")) {
                    Kirjautuminen.sisaanKirjautuminen();
                } else {
                    System.out.println("Anna kunnon syöte.");
                }
            } else {

                System.out.println("Lisää tapahtuma syöttämällä 1");
                System.out.println("Poista tapahtuma syöttämällä 2");
                System.out.println("Tietoja tapahtumista syöttämällä 3");
                System.out.println("Omat tiedot syöttämällä 4");
                System.out.println("Kirjaudu ulos syöttämällä 5");
                System.out.println("Vaihda salasana syöttämällä 6");
                System.out.println("Poista käyttäjä syöttämällä 7");
                System.out.println("Lopeta syöttämällä x");
                String syote = lukija.nextLine();
                if (syote.equals("x")) {
                    Database.tietojenTallennusEhdolla();
                    break;
                } else if (syote.equals("1")) {
                    Tapahtumanlisays.lisaaTapahtuma();
                } else if (syote.equals("2")) {
                    TapahtumanPoisto.poistaTapahtuma();
                } else if (syote.equals("3")) {
                    TapahtumienHallinta.tapahtumaToiminnot();
                } else if (syote.equals("4")) {
                    System.out.println("\n" + current.toString());
                } else if (syote.equals("5")) {
                    Database.tietojenTallennusEhdolla();
                    current = null;
                } else if (syote.equals("6")) {
                    Toimintoja.vaihdaSalasana();
                } else if (syote.equals("7")) {
                    KayttajanPoistoJaMuokkaus.poistaKayttaja();
                } else {
                    System.out.println("Syötä kelvollinen syöte.");
                }
            }
        }
    }
}
