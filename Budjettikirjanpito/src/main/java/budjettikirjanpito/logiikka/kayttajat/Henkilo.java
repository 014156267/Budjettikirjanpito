package budjettikirjanpito.logiikka.kayttajat;

/**
 * Henkilolla on Kayttaja-luokalta perittyjen salasanan ja tapahtumalistan
 * lisäksi etu- ja sukunimi.
 */
public class Henkilo extends Kayttaja {

    private final String etunimi;
    private final String sukunimi;

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
