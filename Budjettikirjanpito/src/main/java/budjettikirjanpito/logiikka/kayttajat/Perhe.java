package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import java.util.ArrayList;

 /**
 * Perheellä on Kayttajalta peritty salasana ja tapahtumalista, ja perheeseen
 * kuuluu joukko Henkilo-luokan oliota. Lisäksi getterit, joista saa perheen
 * tulot, velat ja ostot.
 */

public class Perhe extends Kayttaja {

    private ArrayList<Henkilo> henkilot;

    public Perhe() {
        super();
        henkilot = new ArrayList<>();
    }
    
    public final void lisaaHenkilo(final Henkilo henkilo) {
        henkilot.add(henkilo);
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
    
    public final double getHenkiloidenTulotYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getTulot()) {
                summa += v.maara;
            }
        }
        return summa;
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

    @Override
    public final String toString() {
        String tulostettava = "Perhe: \n";
        for (Henkilo h : henkilot) {
            tulostettava += "\n" + h.toString();
        }
        return tulostettava;
    }
}
