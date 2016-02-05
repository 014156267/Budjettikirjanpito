package budjettikirjanpito.logiikka.rahaliikenne;

import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import java.util.Scanner;

public class Velka extends Tapahtuma {

    public String aihe;
    public Integer lyhennysaikakk;
    public double vuosikorko;
    public static Scanner lukija;

    public Velka(double maara, String selitys, Integer lyhennysaikakk, double vuosikorko) {
        super(maara, selitys);
        this.lyhennysaikakk = lyhennysaikakk;
        this.vuosikorko = vuosikorko;
        this.aihe = "Ei vielä aihetta.";
        lukija = new Scanner(System.in);
    }

    public double paljonkoVelkaaJaljellaXKkPaasta(int kk) {
        if (kk >= lyhennysaikakk) {
            return 0;
        }
        double paljonko = this.maara;
        double kkera = maara / (double) lyhennysaikakk;
        paljonko -= (kk * kkera);
        return paljonko;
    }

    public double velanKorkoYhteensa() {
        double summa = 0;
        int aikaaJaljella = lyhennysaikakk;
        int aikaaKulunut = 0;
        double paaoma = maara;
        while (true) {
            if (aikaaJaljella < 12) {
                double lisattava = (((this.vuosikorko * paaoma) / (double) 100)) / (aikaaJaljella / (double) 12);
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

    public void setAihe() {
        System.out.println("Velan aihe: ");
        String a = lukija.nextLine();
        aihe = a;
    }

    public void setSelitys() {
        System.out.println("Velan syy: ");
        String a = lukija.nextLine();
        selitys = a;
    }

}
