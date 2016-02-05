 package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;

public class Henkilo extends Kayttaja {

    private String etunimi;
    private String sukunimi;

    public Henkilo(String etunimi, String sukunimi) {
        super();
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }
    

    @Override
    public String toString() {
        String tulostettava = "";
        tulostettava += etunimi + " " + sukunimi;
        return tulostettava;

    }

}
