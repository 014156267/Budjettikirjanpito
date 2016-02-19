package budjettikirjanpito.logiikka.rahaliikenne;

import java.io.Serializable;
/**
 * Tapahtuma toimii yliluokkana Ostokselle, Saastolle, Tulolle ja Velalle. Tapahtumalla on määrä ja selitys.
 */
public class Tapahtuma implements Serializable {

    public double maara;
    public String selitys;

    public Tapahtuma(double maara, String selitys) {
        this.maara = maara;
        this.selitys = selitys;
    }

    public final double getMaara() {
        return maara;
    }

    public final void setMaara(double maara) {
        this.maara = maara;
    }

    public final String getSelitys() {
        return selitys;
    }

    public final void setSelitys(String selitys) {
        this.selitys = selitys;
    }

    @Override
    public final String toString() {
        return "Määrä: " + maara + "\nSelitys: " + selitys;
    }

}
