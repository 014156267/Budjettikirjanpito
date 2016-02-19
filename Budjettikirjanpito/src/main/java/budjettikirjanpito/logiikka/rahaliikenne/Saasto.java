package budjettikirjanpito.logiikka.rahaliikenne;

/**
 * Tämä luokka on säästösuunnitelmaa varten. Käyttäjä voi syöttää 
 * kuukausimäärän, jonka aikana tavoitesumma (maara) pitäisi saada kasaan. 
 *Tällöin ohjelma laskee, paljonko kuukaudessa tulee säästää. Vaihtoehtoisesti 
 * käyttäjä voi määrittää summan, jonka hän haluaa kuukaudessa säästää. Ohjelma
 * puolestaan laskee, monenko kuukauden kuluttua säästö on kasassa.
 */

public class Saasto extends Tapahtuma {

    private double kuukausisumma;
    private int kuukausimaara;

    public Saasto(double maara, String selitys) {
        super(maara, selitys);
        kuukausisumma = 0;
        kuukausimaara = 0;
    }

        /**
 * Metodi asettaa kuukausisummaksi käyttäjän antaman syötteen ja laskee sen 
 * perusteella, montako kuukautta säästämiseen kuluu käyttäjän määrittämällä
 * kuukausisummalla ja päivittää kuukausimaara-attribuutin.
 * 
 * @param  summa  Käyttäjän syöttämä rahasumma, jonka hän haluaa kuukaudessa 
 * säästää.
 */
    
    public final void setKuukausiSumma(double summa) {
        if (summa > 0 && summa <= maara) {
            this.kuukausisumma = summa;
            int kkmaara = (int) (Math.ceil(maara / (double) summa));
            this.kuukausimaara = kkmaara;
        }
    }

    public final void setKuukausiMaara(int kk) {
        if (kk > 0) {
            this.kuukausimaara = kk;
            this.kuukausisumma = maara / (double) kk;
        }
    }

    public final double getKuukausiSumma() {
        return kuukausisumma;
    }

    public final int getKuukausiMaara() {
        return kuukausimaara;
    }

}
