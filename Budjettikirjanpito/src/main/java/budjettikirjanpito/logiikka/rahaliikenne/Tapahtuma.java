package budjettikirjanpito.logiikka.rahaliikenne;

import budjettikirjanpito.gui.Kayttoliittyma;
import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Tapahtuma toimii yliluokkana Ostokselle, Saastolle, Tulolle ja Velalle.
 * Tapahtumalla on määrä ja selitys.
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

    public final String getMaaraString() {
        return Kayttoliittyma.muotoilu.format(maara);
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
        return "Määrä: " + Kayttoliittyma.muotoilu.format(maara)
                + " euroa" + "\nSelitys: " + selitys;
    }

}
