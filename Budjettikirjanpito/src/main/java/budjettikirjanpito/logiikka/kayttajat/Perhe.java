package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.util.ArrayList;

public class Perhe extends Kayttaja {

    private ArrayList<Henkilo> henkilot;

    public Perhe() {
        super();
        henkilot = new ArrayList<>();
    }

    public void lisaaHenkilo(Henkilo henkilo) {
        henkilot.add(henkilo);
    }

    public double getHenkiloidenVelatYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getVelat()) {
                summa += v.maara;
            }
        }
        return summa;
    }
    
    public double getHenkiloidenTulotYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getTulot()) {
                summa += v.maara;
            }
        }
        return summa;
    }
    
    public double getHenkiloidenOstotYhteensa() {
        double summa = 0;
        for (Henkilo h : henkilot) {
            for (Tapahtuma v : h.getOstokset()) {
                summa += v.maara;
            }
        }
        return summa;
    }

    

    @Override
    public String toString() {
        String tulostettava = "Perhe: \n";
        for (Henkilo h : henkilot) {
            tulostettava += "\n" + h.toString();
        }
        return tulostettava;
    }

}
