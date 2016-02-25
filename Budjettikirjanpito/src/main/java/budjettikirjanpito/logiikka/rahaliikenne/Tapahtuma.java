package budjettikirjanpito.logiikka.rahaliikenne;

import java.io.Serializable;
import java.text.DecimalFormat;
/**
 * Tapahtuma toimii yliluokkana Ostokselle, Saastolle, Tulolle ja Velalle. Tapahtumalla on määrä ja selitys.
 */
public class Tapahtuma implements Serializable {
    public final DecimalFormat muotoilu;
    public double maara;
    public String selitys;

    public Tapahtuma(double maara, String selitys) {
        this.maara = maara;
        this.selitys = selitys;
        this.muotoilu = new DecimalFormat("###.##");
    }

    public final double getMaara() {
        return maara;
    }
    
    public final String getMaaraString() {
        return muotoilu.format(maara);
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
        return "Määrä: " + muotoilu.format(maara) + "\nSelitys: " + selitys;
    }

}
