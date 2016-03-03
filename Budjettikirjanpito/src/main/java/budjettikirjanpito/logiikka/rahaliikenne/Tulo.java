package budjettikirjanpito.logiikka.rahaliikenne;

/**
 * Tulo voi olla palkka, palkkio, lahjoitus, voitto tai mikä tahansa muu
 * rahaliikenne käyttäjän suuntaan. Tulolla on Tapahtumalta perittyjen
 * attribuuttien lisäksi maksaja.
 */
public class Tulo extends Tapahtuma {

    public String maksaja;

    public Tulo(String maksaja, double maara, String selitys) {
        super(maara, selitys);
        this.maksaja = maksaja;
    }
}
