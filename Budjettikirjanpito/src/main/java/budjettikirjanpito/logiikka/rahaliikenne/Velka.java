package budjettikirjanpito.logiikka.rahaliikenne;

import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import java.util.Scanner;

/**
 * Luokassa on velan perustiedot attribuutteina sekä muutamia työkaluja velan ja sen korkojen seuraamiseen.
 */

public class Velka extends Tapahtuma {

    private String aihe;
    private Integer lyhennysaikakk;
    private double vuosikorko;

    public Velka(double maara, String selitys, Integer lyhennysaikakk, double vuosikorko) {
        super(maara, selitys);
        this.lyhennysaikakk = lyhennysaikakk;
        this.vuosikorko = vuosikorko;
        this.aihe = "Ei vielä aihetta.";
    }

    /**
 * Metodi kertoo velan pääoman suuruuden tietyn kuukausimäärän päästä.
 * 
 * @param  kk  Käyttäjän syöttämä kuukausimäärä.
 * 
 * @return Velan suuruus käyttäjän syöttämän kuukausimäärän kuluttua.
 */
    
    public double paljonkoVelkaaJaljellaXKkPaasta(int kk) {
        if (kk >= lyhennysaikakk) {
            return 0;
        }
        double paljonko = this.maara;
        double kkera = kuukaudenLyhennysEra();
        paljonko -= (kk * kkera);
        return paljonko;
    }
    
            /**
 * Metodi kertoo, kuinka paljon korkoja kyseisestä velasta joudutaan tässä kuussa korkoineen maksamaan.
 * Kuukausittainen korkomäärä päivitetään velan ottohetkellä ja siitä eteenpäin vuoden välein ja se lasketaan seuraavalla kaavalla:
 * Päivityshetkellä jäljellä oleva velkapääoma * (korko/100) / 12;
 * 
 * @return Paljonko käyttäjä joutuu kuukaudessa korkoineen maksamaan, eli velan kuukausimaksu.
 */
    
    public double kuukausimaksu() {
        double kerroin = vuosikorko / (double) 100;
        double lyhennettava = kuukaudenLyhennysEra() + ((kerroin * maara) / 12);
        return lyhennettava;
    }
    
            /**
 * Metodi kertoo, paljonko käyttäjä lyhentää velan pääomaa kuukaudessa.
 * 
 * @return Kuukausimaksu ilman korkoja.
 */
    
    public double kuukaudenLyhennysEra() {
        double kkera = maara / (double) lyhennysaikakk;
        return kkera;
    }
    
        /**
 * Metodi kertoo, kuinka paljon korkoja kyseisestä velasta joudutaan yhteensä maksamaan.
 * Kuukausittainen korkomäärä päivitetään velan ottohetkellä ja siitä eteenpäin vuoden välein ja se lasketaan seuraavalla kaavalla:
 * Päivityshetkellä jäljellä oleva velkapääoma * (korko/100) / 12;
 * 
 * @return Velan suuruus käyttäjän syöttämän kuukausimäärän kuluttua.
 */

    public double velanKorkoYhteensa() {
        double summa = 0;
        int aikaaJaljella = lyhennysaikakk;
        int aikaaKulunut = 0;
        double paaoma = maara;
        while (true) {
            if (aikaaJaljella < 12) {
                double lisattava = (((this.vuosikorko * paaoma) / (double) 100)) * (aikaaJaljella / (double) 12);
                summa += lisattava;
                break;
            }      
            summa += this.vuosikorko * paaoma / (double) 100;         
            aikaaJaljella -= 12;
            aikaaKulunut += 12;
            paaoma = paljonkoVelkaaJaljellaXKkPaasta(aikaaKulunut);
        }
        return summa;
    }

    public int getLyhennysaika() {
        return lyhennysaikakk;
    }

    public void setLyhennysaika(int uusiaika) {
        this.lyhennysaikakk = uusiaika;
    }

    public double getVuosikorko() {
        return vuosikorko;
    }

    public void setVuosikorko(double uusikorko) {
        vuosikorko = uusikorko;
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public void setSelitys(String selitys) {
        this.selitys = selitys;
    }

}
