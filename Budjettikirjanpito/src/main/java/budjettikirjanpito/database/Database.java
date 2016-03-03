package budjettikirjanpito.database;

import budjettikirjanpito.gui.Kayttoliittyma;
import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Perhe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * Tämä luokka hoitaa tiedon siirron ohjelman ja tiedoston kayttajat.ser
 * välillä.
 */
public class Database {

    /**
     * Metodi suorittuu kaynnista-metodin toimesta ja lataa tietokannan sisällön
     * kayttajat-listaan.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static final void tietojenLataus()
            throws FileNotFoundException,
            IOException, ClassNotFoundException {

        if (new File(Kayttoliittyma.current.tunnus + ".ser").exists()) {
            FileInputStream fis = new FileInputStream(Kayttoliittyma.current.tunnus + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Kayttoliittyma.current = (Kayttaja) ois.readObject();
            ois.close();
            fis.close();
        }
    }

    /**
     * Metodille annetaan parametrina sen Henkilo-olion käyttäjätunnus, joka
     * halutaan palauttaa. Metodi katsoo, onko olemassa tiedosto muotoa
     * tunnus.ser ja jos on, metodi katsoo, onko siellä sisällä
     * Henkilo-tyyppinen olio ja palauttaa sen, mikäli on.
     *
     * @param tunnus Haettavan henkilön käyttäjätunnus
     *
     * @return Palauttaa tunnusta vastaavan Henkilo-olion.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static final Henkilo tietynHenkilonTietojenLataus(String tunnus)
            throws FileNotFoundException,
            IOException, ClassNotFoundException {

        if (new File(tunnus + ".ser").exists()) {
            FileInputStream fis = new FileInputStream(tunnus + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Kayttaja p = (Kayttaja) ois.readObject();
            Henkilo l = (Henkilo) p;
            ois.close();
            fis.close();
            return l;
        }
        return null;
    }

    /**
     * Metodille annetaan parametrina sen Kayttaja-olion käyttäjätunnus, joka
     * halutaan palauttaa. Metodi katsoo, onko olemassa tiedosto muotoa
     * tunnus.ser ja jos on, metodi palauttaa kyseisen tiedoston sisältämän
     * Kayttaja-olion.
     *
     * @param tunnus Haettavan käyttäjän käyttäjätunnus
     *
     * @return Palauttaa tunnusta vastaavan Kayttaja-olion.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static final Kayttaja tietynTietojenLataus(String tunnus)
            throws FileNotFoundException,
            IOException, ClassNotFoundException {
        if (new File(tunnus + ".ser").exists()) {
            FileInputStream fis = new FileInputStream(tunnus + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Kayttaja p = (Kayttaja) ois.readObject();
            ois.close();
            fis.close();
            return p;
        }
        return null;
    }

    /**
     * Metodi katsoo, onko olemassa tiedosto muotoa tunnus.ser ja jos on, metodi
     * katsoo, onko siellä sisällä Henkilo-tyyppinen olio ja palauttaa true,
     * mikäli on.
     *
     * @param tunnus on mahdollisen Henkilön käyttäjätunnus.
     *
     * @return totuusarvo sille, että on olemassa tunnusta vastaava
     * Henkilo-olio.
     */
    public static final boolean onkoHenkiloLuotu(String tunnus)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        if (new File(tunnus + ".ser").exists()) {
            FileInputStream fis = new FileInputStream(tunnus + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Kayttaja kayttaja = (Kayttaja) ois.readObject();
            if (kayttaja instanceof Henkilo) {
                return true;
            }
            ois.close();
            fis.close();
        }
        return false;
    }

    /**
     * Metodi katsoo, onko olemassa tiedosto muotoa tunnus.ser ja jos on, metodi
     * palauttaa true.
     *
     * @param tunnus on mahdollisen Henkilön käyttäjätunnus.
     * @return totuusarvo sille, että on olemassa tunnusta vastaava Kayttaja-
     * olio.
     */
    public static final boolean onkoKayttajaLuotu(String tunnus)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        return (new File(tunnus + ".ser").exists());
    }

    public static final void tietojenLatausPerhe(Perhe perhe)
            throws FileNotFoundException,
            IOException, ClassNotFoundException {

        if (new File(Kayttoliittyma.current.tunnus + ".ser").exists()) {
            for (String tunnus : perhe.getTunnukset()) {
                FileInputStream fis = new FileInputStream(tunnus + ".ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                ois.close();
                fis.close();
            }
        }
    }

    /**
     * Metodi tallentaa kayttajat-listan kayttajat.ser-tiedostoon. Metodi
     * suoritetaan, mikäli ohjelman käyttäjä haluaa niin.
     *
     * @throws java.io.FileNotFoundException
     */
    public static final void tietojenTallennusEhdolla() throws IOException {
        System.out.println("\nTallenetaanko muutokset? (k/e)");
        while (true) {
            String vastaus = Kayttoliittyma.lukija.nextLine();
            if (vastaus.equals("k")) {
                System.out.println("\nTallennetaan...");
                tietojenTallennus();
                System.out.println("Tallennettu.");
                break;
            } else if (vastaus.equals("e")) {
                break;
            } else {
                System.out.println("\nVastaa k tai e.");
            }
        }
    }

    /**
     * Metodi tallentaa kayttajat-listan kayttajat.ser-tiedostoon.
     *
     * @throws java.io.FileNotFoundException
     */
    public static final void tietojenTallennus() throws IOException {
        FileOutputStream fos = new FileOutputStream(Kayttoliittyma.current.tunnus + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Kayttoliittyma.current);
        oos.close();
        fos.close();
    }

    /**
     * Metodi hakee tällä hetkellä kirjautuneena olevan käyttäjän tietokannan ja
     * poistaa sen.
     *
     */
    public static final void tietojenPoisto() {
        File f = new File(Kayttoliittyma.current.tunnus + ".ser");
        f.delete();
    }

}
