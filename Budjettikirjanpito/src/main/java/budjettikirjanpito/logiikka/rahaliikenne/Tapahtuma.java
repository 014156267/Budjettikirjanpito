
package budjettikirjanpito.logiikka.rahaliikenne;

public class Tapahtuma {
    
    public double maara;
    public String selitys;

    public Tapahtuma(double maara, String selitys) {
        this.maara = maara;
        this.selitys = selitys;
    }
    
    public double getMaara() {
        return maara;
    }
    
    public String getSelitys() {
        return selitys;
    }
    
    public String toString() {
        return "\n Määrä: " + maara + "\n Selitys: " + selitys;
    }
    
}
