
package budjettikirjanpito.logiikka.rahaliikenne;
/**
 * Ostos voi olla mikä tahansa käyttäjän tekemä yksittäinen ostotapahtuma.
 */
public class Ostos extends Tapahtuma {
    
    public Ostos(double maara, String selitys) {
        super(maara, selitys);
    }
    
}
