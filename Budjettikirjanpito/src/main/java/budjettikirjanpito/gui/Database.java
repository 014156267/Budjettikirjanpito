package budjettikirjanpito.gui;

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
                Kayttoliittyma.kayttajat.add((Henkilo) ois.readObject());
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
        System.out.println("Tallenetaanko muutokset? (k/e)");
        while (true) {
            String vastaus = Kayttoliittyma.lukija.nextLine();
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
    public static final void tietojenTallennus() throws IOException {
        FileOutputStream fos = new FileOutputStream(Kayttoliittyma.current.tunnus + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Kayttoliittyma.current);
        oos.close();
        fos.close();
    }

    public static final void tietojenPoisto() {
        Kayttoliittyma.kayttajat.remove(Kayttoliittyma.current);
        File f = new File(Kayttoliittyma.current.tunnus + ".ser");
        f.delete();
    }

}
