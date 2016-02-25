package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.gui.Database;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Perheellä on Kayttajalta peritty salasana ja tapahtumalista, ja perheeseen
 * kuuluu joukko Henkilo-luokan oliota. Lisäksi getterit, joista saa perheen
 * tulot, velat ja ostot.
 */
public class Perhe extends Kayttaja {

    public ArrayList<Henkilo> henkilot;

    public Perhe() {
        super();
        henkilot = new ArrayList<>();
    }

    public final void lisaaHenkilo(final Henkilo henkilo) {
        henkilot.add(henkilo);
    }

    public final void paivita() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<String> tunnuksia = new ArrayList();
        for (String t : getTunnukset()) {
            tunnuksia.add(t);
        }
        henkilot.clear();
        for (String tunnus : tunnuksia) {
            if (Database.onkoHenkiloLuotu(tunnus)) {
                lisaaHenkilo(Database.tietynHenkilonTietojenLataus(tunnus));
            }
        }

    }

    public final ArrayList<String> getTunnukset() {
        ArrayList<String> tunnukset = new ArrayList<>();
        for (Henkilo t : henkilot) {
            tunnukset.add(t.tunnus);
        }
        return tunnukset;
    }

    public final double getHenkiloidenVelatYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getVelat()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    public final String getHenkiloidenVelatYhteensaString() {
        return muotoilu.format(getHenkiloidenVelatYhteensa());
    }

    public final double getHenkiloidenTulotYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getTulot()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    public final String getHenkiloidenTulotYhteensaString() {
        return muotoilu.format(getHenkiloidenTulotYhteensa());
    }

    public final double getHenkiloidenOstotYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getOstokset()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    public final String getHenkiloidenOstotYhteensaString() {
        return muotoilu.format(getHenkiloidenVelatYhteensa());
    }

    @Override
    public final String toString() {
        String tulostettava = "Perhe: \n";
        for (Henkilo h : henkilot) {
            tulostettava += "\n" + h.toString();
        }
        return tulostettava;
    }
}
