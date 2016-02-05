package budjettikirjanpito.logiikka.rahaliikenne;

import java.io.Serializable;

public class Tapahtuma implements Serializable{

    public double maara;
    public String selitys;

    public Tapahtuma(double maara, String selitys) {
        this.maara = maara;
        this.selitys = selitys;
    }

    public double getMaara() {
        return maara;
    }

    public void setMaara(double maara) {
        this.maara = maara;
    }

    public String getSelitys() {
        return selitys;
    }

    public void setSelitys(String selitys) {
        this.selitys = selitys;
    }

    public String toString() {
        return "Määrä: " + maara + "\nSelitys: " + selitys;
    }

}
