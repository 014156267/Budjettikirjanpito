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
    public static ArrayList<Kayttaja> kayttajat;
    public static Kayttaja current;
    public static Scanner lukija =new Scanner(System.in);
    private String tiedostonNimi;

    public Kayttoliittyma() {
        kayttajat = new ArrayList<>();
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
                    Database.tietojenTallennusEhdolla();
                    break;
                } else if (syote.equals("1")) {
                    Tapahtumanlisays.lisaaTapahtuma();
                } else if (syote.equals("2")) {
                    TapahtumanPoisto.poistaTapahtuma();
                } else if (syote.equals("3")) {
                    Toimintoja.tapahtumaToiminnot();
                } else if (syote.equals("4")) {
                    Database.tietojenTallennusEhdolla();
                    kayttajat.clear();
                    current = null; 
                } else if (syote.equals("5")) {
                    KayttajanPoistoJaMuokkaus.poistaKayttaja();
                } else {
                    System.out.println("Syötä kelvollinen syöte.");
                }
            }
        }
    }

    /**
     * Metodi mahdollistaa Ohjelman käyttäjän sisäänkirjautumisen.
     */
    public static final void sisaanKirjautuminen() throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("\nSyötä käyttäjätunnuksesi: ");
        String tunnus = lukija.nextLine();
            if (Database.onkoKayttajaLuotu(tunnus)) {
                Kayttaja k = Database.tietynTietojenLataus(tunnus);
                System.out.println("\nSyötä salasanasi: ");
                String salasana = lukija.nextLine();
                if (k.salasana.equals(salasana)) {
                    current = k;
                    Database.tietojenLataus();
                    if (k instanceof Perhe) {
                        Perhe p = (Perhe) k;
                        p.paivita();
                        current = p;
                    }
                    
                    System.out.println("\nTervetuloa " + k.toString() + "!");
                    
                    return;
                } else {
                    System.out.println("\nSyöttämäsi salasana ei täsmää "
                            + "syöttämäsi käyttäjätunnuksen kanssa. Olet"
                            + "han varmasti luonut tunnuksen? Jos et, syötä 1");
                    return;
                }
            }
        
        System.out.println("Syöttämääsi käyttäjätunnusta ei ole.");
    }

    

    

    /**
     * Metodi tarkistaa, onko parametrina annettu tunnus jonkun kayttajat-listan
     * olion tunnus.
     *
     * @param s käyttäjän syöte
     *
     * @return onko syötettyä tunnusta olemassa.
     */
    public final boolean tunnuksenTarkistus(String s) {
        for (Kayttaja k : kayttajat) {
            if (k.tunnus.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi käy läpi kayttajat-listan ja tarkistaa, onko parametreina annettu
     * tunnus ja salasana jonkun kayttajat-listan olion tunnus ja salasana.
     *
     * @param tunnus on syöte, jota verrataan yhdessä syötteen salasana kanssa
     * aina vuorossa olevaan käyttäjät-listan alkion tunnukseen ja salasanaan.
     * @param salasana on syöte, jota verrataan yhdessä syötteen tunnus kanssa
     * aina vuorossa olevaan käyttäjät-listan alkion tunnukseen ja salasanaan.
     *
     * @return onko olemassa tunnusta, johon syötetty tunnus ja salasana
     * täsmäävät.
     */
    public final boolean tasmaakoSalasanaTunnukseen(String tunnus, String salasana) {
        for (Kayttaja k : kayttajat) {
            if (k.tunnus.equals(tunnus) && k.salasana.equals(salasana)) {
                return true;
            }
        }
        return false;
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
}
