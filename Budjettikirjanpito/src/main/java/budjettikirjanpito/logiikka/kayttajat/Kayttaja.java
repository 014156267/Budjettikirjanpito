
package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class Kayttaja {
  
    
    
    public ArrayList<Tapahtuma> tapahtumat;
    public ArrayList<Saasto> saastot;
    public ArrayList<Ostos> ostokset;
    public ArrayList<Tulo> tulot;
    public ArrayList<Velka> velat;
    public Scanner lukija;

    public Kayttaja() {
        this.lukija = new Scanner(System.in);
        velat = new ArrayList<>();
        
    }
    
    public void lisaaTapahtuma() {
        System.out.println("Valitse tapahtuma: ");
        System.out.println("1. Kertaostos");
        System.out.println("2. Tulo");
        System.out.println("3. Velka");
        System.out.println("4. Säästö");
        int luku = Integer.parseInt(lukija.nextLine());
        if (luku == 1) {
            
        }
    }
    
    public void lisaaKertaostos() {
        System.out.println("");
    }
    
    public void lisaaTulo() {
        System.out.println("Maksaja: ");
        String maksaja = lukija.nextLine();
        System.out.println("Tulon suuruus: ");
        double maara = lukija.nextDouble();
        System.out.println("Selitys: ");
        String selitys = lukija.nextLine();
        Tulo tulo = new Tulo(maksaja, maara, selitys);
        tulot.add(tulo);
        tapahtumat.add(tulo);
    }
    
    public void lisaaVelka() {
        System.out.println("Velan suuruus: ");
        double maara = lukija.nextDouble();
        System.out.println("Vuosikorko (prosentteina): ");
        double korko = lukija.nextDouble();
        System.out.println("Lyhennysaika (kuukausina): ");
        int kk = lukija.nextInt();
        Velka velka = new Velka(maara, "", kk, korko);
        velka.setAihe();
        velka.setSelitys();
        velat.add(velka);
        tapahtumat.add(velka);
    }
    
    public void lisaaSaasto() {
        
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
