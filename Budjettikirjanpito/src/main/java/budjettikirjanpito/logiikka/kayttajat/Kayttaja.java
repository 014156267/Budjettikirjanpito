package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Kayttaja toimii yliluokkana Henkilolle, Perheelle seka Yritykselle.
 * Kayttajalla on paljon Tapahtumia sekä salasana. Luokka sisältää getterin
 * kunkin tapahtumatyypin listalle.
 *
 */
public abstract class Kayttaja implements Serializable {

    public ArrayList<Tapahtuma> tapahtumat;
    public String salasana;

    public Kayttaja() {
        tapahtumat = new ArrayList<>();
        salasana = "";
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
    
                /**
 * getKuukaudenMenotYhteensa() laskee käyttäjän ostosten ja velkojen kuukausimaksujen summan.
 * 
 * @return ostosten ja velkojen kuukausimaksujen summa
 */

    public double getKuukaudenMenotYhteensa() {
        double yht = 0;
        for (Tapahtuma v : getVelat()) {
            Velka velka = (Velka) v;
            yht += velka.kuukausimaksu();
        }
        for (Tapahtuma o : getOstokset()) {
            yht += o.getMaara();
        }
        return yht;
    }

                    /**
 * getKuukaudenTulotYhteensa() laskee käyttäjän tulojen summan.
 * 
 * @return tulojen summa
 */
    
    public double getKuukaudenTulotYhteensa() {
        double yht = 0;
        for (Tapahtuma t : getTulot()) {
            yht += t.maara;
        }
        return yht;
    }
    
                    /**
 * Metodi laskee käyttäjän tämänhetkisen rahatilanteen laskemalla getKuukaudenTulot() - getKuukaudenMenot().
 * 
 * @return tulojen ja menojen erotus
 */
    
    public double getRahatilanne() {
        return (getKuukaudenTulotYhteensa() - getKuukaudenMenotYhteensa());
    }

    public ArrayList<Tapahtuma> getVelat() {
        ArrayList<Tapahtuma> listattavatTapahtumat = new ArrayList<>();
        for (Tapahtuma t : tapahtumat) {
            if (t instanceof Velka) {
                listattavatTapahtumat.add(t);
            }
        }
        return listattavatTapahtumat;
    }

    public ArrayList<Tapahtuma> getTulot() {
        ArrayList<Tapahtuma> listattavatTapahtumat = new ArrayList<>();
        for (Tapahtuma t : tapahtumat) {
            if (t instanceof Tulo) {
                listattavatTapahtumat.add(t);
            }
        }
        return listattavatTapahtumat;
    }

    public ArrayList<Tapahtuma> getSaastot() {
        ArrayList<Tapahtuma> listattavatTapahtumat = new ArrayList<>();
        for (Tapahtuma t : tapahtumat) {
            if (t instanceof Saasto) {
                listattavatTapahtumat.add(t);
            }
        }
        return listattavatTapahtumat;
    }

    public ArrayList<Tapahtuma> getOstokset() {
        ArrayList<Tapahtuma> listattavatTapahtumat = new ArrayList<>();
        for (Tapahtuma t : tapahtumat) {
            if (t instanceof Ostos) {
                listattavatTapahtumat.add(t);
            }
        }
        return listattavatTapahtumat;
    }

    public abstract String toString();

}
