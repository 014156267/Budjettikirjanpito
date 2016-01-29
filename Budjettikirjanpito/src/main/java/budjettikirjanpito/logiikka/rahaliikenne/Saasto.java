package budjettikirjanpito.logiikka.rahaliikenne;

public class Saasto extends Tapahtuma {

    private double kuukausisumma;
    private int kuukausimaara;

    public Saasto(double maara, String selitys) {
        super(maara, selitys);
        kuukausisumma = 0;
        kuukausimaara = 0;
    }

    public void setKuukausiSumma(double summa) {
        if (summa > 0 && summa <= maara) {
            this.kuukausisumma = summa;
            int kkmaara = (int) (Math.ceil(maara / (double) summa));
            this.kuukausimaara = kkmaara;
        }
    }

    public void setKuukausiMaara(int kk) {
        if (kk > 0) {
            this.kuukausimaara = kk;
            this.kuukausisumma = maara / (double) kk;
        }
    }

    public double getKuukausiSumma() {
        return kuukausisumma;
    }

    public int getKuukausiMaara() {
        return kuukausimaara;
    }

}
