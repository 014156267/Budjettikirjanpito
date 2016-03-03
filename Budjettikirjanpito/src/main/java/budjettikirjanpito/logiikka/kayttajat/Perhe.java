package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.database.Database;
import budjettikirjanpito.gui.Kayttoliittyma;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Perheellä on Kayttajalta peritty salasana ja tapahtumalista, ja perheeseen
 * kuuluu joukko Henkilo-luokan oliota. Lisäksi getterit, joista saa perheen
 * tulot, velat ja ostot.
 */
public class Perhe extends Kayttaja {

    public ArrayList<String> tunnukset;

    public Perhe() {
        super();
        tunnukset = new ArrayList<>();
    }

    public final void lisaaHenkilo(String tunnus) {
        tunnukset.add(tunnus);
    }

    public final ArrayList<Henkilo> getHenkilot() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Henkilo> palautus = new ArrayList();
        for (String t : tunnukset) {
            if (Database.onkoHenkiloLuotu(t)) {
                palautus.add(Database.tietynHenkilonTietojenLataus(t));
            }
        }
        return palautus;
    }

    public final ArrayList<String> poistetut() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<String> poistetut = new ArrayList();
        for (String t : tunnukset) {
            if (!Database.onkoHenkiloLuotu(t)) {
                poistetut.add(t);
            }
        }
        return poistetut;
    }

    public final ArrayList<String> getTunnukset() {
        return tunnukset;
    }

    public final double getHenkiloidenVelatYhteensa() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Henkilo> oliot = getHenkilot();
        double summa = 0;
        for (Henkilo h : oliot) {
            for (Tapahtuma v : h.getVelat()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    public final String getHenkiloidenVelatYhteensaString() throws IOException, FileNotFoundException, ClassNotFoundException {
        return Kayttoliittyma.muotoilu.format(getHenkiloidenVelatYhteensa());
    }

    public final double getHenkiloidenTulotYhteensa() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Henkilo> oliot = getHenkilot();
        double summa = 0;
        for (Henkilo h : oliot) {
            for (Tapahtuma v : h.getTulot()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    public final String getHenkiloidenTulotYhteensaString() throws IOException, FileNotFoundException, ClassNotFoundException {
        return Kayttoliittyma.muotoilu.format(getHenkiloidenTulotYhteensa());
    }

    public final double getHenkiloidenOstotYhteensa() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Henkilo> oliot = getHenkilot();
        double summa = 0;
        for (Henkilo h : oliot) {
            for (Tapahtuma v : h.getOstokset()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    public final String getHenkiloidenOstotYhteensaString() throws IOException, FileNotFoundException, ClassNotFoundException {
        return muotoilu.format(getHenkiloidenOstotYhteensa());
    }

    @Override
    public final String toString() {
        ArrayList<Henkilo> oliot;
        try {
            oliot = getHenkilot();
            String tulostettava = "Perhe: \n";
            for (Henkilo h : oliot) {
                tulostettava += "\n" + h.toString();
            }
            return tulostettava;
        } catch (IOException ex) {
            Logger.getLogger(Perhe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perhe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
