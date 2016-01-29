package budjettikirjanpito.logiikka.rahaliikenne;

public class Saasto extends Tapahtuma {
    
    private double kuukausisumma;
    private int kuukausimaara;

    public Saasto(double maara, String selitys) {
        super(maara, selitys);
        kuukausisumma = 0;
        kuukausimaara = 0;
    }
    
    public void setKuukausisumma(double summa) {
        this.kuukausisumma = summa;
    }
    
    public void setKuukausisumma(int kk) {
        this.kuukausimaara= kk;
    }
    
    
    
}
