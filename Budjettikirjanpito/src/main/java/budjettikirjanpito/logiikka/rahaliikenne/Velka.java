package budjettikirjanpito.logiikka.rahaliikenne;

import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import java.util.Scanner;

public class Velka extends Tapahtuma{

    public String aihe;
    public Integer lyhennysaikakk;
    public double vuosikorko;
    public Scanner lukija;

    public Velka(double maara, String selitys, Integer lyhennysaikakk, double vuosikorko) {
        super(maara, selitys);
        this.lyhennysaikakk = lyhennysaikakk;
        this.vuosikorko = vuosikorko;
        this.aihe = "Ei vielä aihetta.";
        lukija = new Scanner(System.in);
    }
    
    public double getMaara() {
        return maara;
    }

    public int getLyhennysaika() {
        return lyhennysaikakk;
    }
    
    public double getVuosikorko() {
        return vuosikorko;
    }

    public String getAihe() {
        return aihe;
    }

    public void setAihe() {
        System.out.println("Velan aihe: ");
        String a = lukija.nextLine();
        aihe = a;
    }

    public String getSelitys() {
        return selitys;
    }

    public void setSelitys() {
        System.out.println("Velan syy: ");
        String a = lukija.nextLine();
        selitys = a;
    }
    
    

}
