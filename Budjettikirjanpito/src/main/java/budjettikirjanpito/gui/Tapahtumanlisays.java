/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.util.Scanner;

/**
 *
 * @author Timo
 */
public class Tapahtumanlisays {

    /**
     * Metodi kysyy, minkälaisen tapahtuman ohjelman käyttäjä haluaa lisätä ja
     * kutsuu kyseisen tapahtumatyypin lisäysmetodia. Esim. jos ohjelman
     * käyttäjä syöttää 1 eli haluaa lisätä uuden kertaostoksen, kutsuu tämä
     * metodi syötteen perusteella metodia lisaaOstos().
     *
     */
    public static final void lisaaTapahtuma() {
        System.out.println("\nValitse tapahtuma: ");
        System.out.println("1. Kertaostos");
        System.out.println("2. Tulo");
        System.out.println("3. Velka");
        System.out.println("4. Säästö");
        String luku = Kayttoliittyma.lukija.nextLine();
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
     * Metodi kysyy syötettävän ostoksen tiedot ja lisää ostoksen currentin
     * ostoksiin.
     *
     */
    public static final void lisaaOstos() {
        System.out.println("\nOstoksen suuruus: ");
        double maara = Toimintoja.kysyLuku(0, 999999999);
        System.out.println("\nSelitys: ");
        String selitys = Kayttoliittyma.lukija.nextLine();
        Ostos ostos = new Ostos(maara, selitys);
        Kayttoliittyma.current.tapahtumat.add(ostos);
    }

    /**
     * Metodi kysyy lisättävän tulon tiedot, luo tietojen perusteella Tulo-olion
     * ja lisää sen currentin tapahtumiin.
     *
     */
    public static final void lisaaTulo() {
        System.out.println("\nMaksaja: ");
        String maksaja = Kayttoliittyma.lukija.nextLine();
        System.out.println("\nTulon suuruus: ");
        double maara = Toimintoja.kysyLuku(0, 999999999);
        System.out.println("\nSelitys: ");
        String selitys = Kayttoliittyma.lukija.nextLine();
        Tulo tulo = new Tulo(maksaja, maara, selitys);
        Kayttoliittyma.current.tapahtumat.add(tulo);
    }

    /**
     * Metodi kysyy lisättävän velan tiedot, luo tietojen perusteella
     * Velka-olion ja lisää sen currentin tapahtumiin.
     *
     */
    public static final void lisaaVelka() {
        System.out.println("\nVelan suuruus: ");
        double maara = Toimintoja.kysyLuku(0, 999999999);
        System.out.println("\nVuosikorko (prosentteina): ");
        double korko = Toimintoja.kysyLuku(0, 100);
        System.out.println("\nLyhennysaika (kuukausina): ");
        int kk = Toimintoja.kysyKokonaisluku(1, 999999999);
        Velka velka = new Velka(maara, "", kk, korko);
        System.out.println("\nVelan aihe: ");
        String aihe = Kayttoliittyma.lukija.nextLine();
        velka.setAihe(aihe);
        System.out.println("\nVelan selitys: ");
        String selitys = Kayttoliittyma.lukija.nextLine();
        velka.setSelitys(selitys);
        Kayttoliittyma.current.tapahtumat.add(velka);
    }

    /**
     * Metodi kysyy lisättävän säästön tiedot, luo tietojen perusteella
     * Saasto-olion ja lisää sen currentin tapahtumiin.
     *
     */
    public static final void lisaaSaasto() {
        System.out.println("\nSäästön suuruus: ");
        double maara = Toimintoja.kysyLuku(0, 999999999);
        System.out.println("\nSäästön selitys: ");
        String selitys = Kayttoliittyma.lukija.nextLine();
        Saasto saasto = new Saasto(maara, selitys);
        System.out.println("\nHaluatko määritellä kuukausisumman (syötä 1) vai"
                + " säästämisjakson pituuden (syötä 2)?");
        int luku = Toimintoja.kysyKokonaisluku(1, 2);
        if (luku == 1) {
            System.out.println("\nSyötä kuukaudessa maksettava määrä: ");
            double summa = Toimintoja.kysyLuku(0, 999999999);
            saasto.setKuukausiSumma(summa);
        }
        if (luku == 2) {
            System.out.println("\nSyötä kuukausien määrä: ");
            int kk = Toimintoja.kysyKokonaisluku(1, 999999999);
            saasto.setKuukausiMaara(kk);
        }
        Kayttoliittyma.current.tapahtumat.add(saasto);
    }
}
