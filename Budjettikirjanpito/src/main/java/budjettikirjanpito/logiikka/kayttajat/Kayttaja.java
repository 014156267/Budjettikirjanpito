
package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class Kayttaja {
  
    public ArrayList<Velka> velat;
    public ArrayList<Tapahtuma> tapahtumat;
    public ArrayList<Saasto> saastot;
    public ArrayList<Ostos> menot;
    public Scanner lukija;

    public Kayttaja() {
        this.lukija = new Scanner(System.in);
        velat = new ArrayList<>();
        tapahtumat = new ArrayList<>();
    }
    
    public void lisaaTapahtuma() {
        
        System.out.println("Maksaja: ");
        String maksaja = lukija.nextLine();
        System.out.println("Tulon suuruus: ");
        double maara = lukija.nextDouble();
        System.out.println("Selitys: ");
        String selitys = lukija.nextLine();
        Tapahtuma tulo = new Tapahtuma(maksaja, maara, selitys);
        tulot.add(tulo);
    }
    
    
    
    public void lisaaVelka() {
        System.out.println("Velan suuruus: ");
        double maara = lukija.nextDouble();
        System.out.println("Vuosikorko (prosentteina): ");
        double korko = lukija.nextDouble();
        System.out.println("Lyhennysaika (kuukausina): ");
        int kk = lukija.nextInt();
        Velka velka = new Velka(maara, kk, korko);
        velka.setAihe();
        velka.setSelitys();
        velat.add(velka);
    }
    
  
    @Override
    public String toString() {
        String tulostettava = "";
        tulostettava += "Velat: \n";
        if (velat.isEmpty()) {
            tulostettava += "Ei velkoja. \n";
        } else {
            for (Velka c : velat) {
                tulostettava += c.toString() + " \n";
            }
        }
        if (tulot.isEmpty()) {
            tulostettava += "Ei tuloja. \n";
        } else {
            tulostettava += "Tulot \n";
            for (Tapahtuma c : tulot) {
                tulostettava += c.toString() + " \n";
            }
        }

        return tulostettava;
    }
    
}
