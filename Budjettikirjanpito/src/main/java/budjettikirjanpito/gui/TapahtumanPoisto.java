
package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import java.util.Scanner;

public class TapahtumanPoisto {

    /**
     * Metodi kysyy, minkälaisen tapahtuman ohjelman käyttäjä haluaa poistaa ja
     * kutsuu kyseisen tapahtumatyypin poistometodia. Esim. jos ohjelman
     * käyttäjä syöttää 1 eli haluaa poistaa uuden kertaostoksen, kutsuu tämä
     * metodi syötteen perusteella metodia poistaOstos().
     * 
     * 
     */
    public static final void poistaTapahtuma() {
        System.out.println("Valitse tapahtuma: ");
        System.out.println("1. Kertaostos");
        System.out.println("2. Tulo");
        System.out.println("3. Velka");
        System.out.println("4. Säästö");
        int luku = Toimintoja.kysyKokonaisluku(1, 4);
        if (luku == 1) {
            poistaOstos();
        } else if (luku == 2) {
            poistaTulo();
        } else if (luku == 3) {
            poistaVelka();
        } else if (luku == 4) {
            poistaSaasto();
        }
    }
    
     /**
     * Metodi poistaa ohjelman käyttäjän valitseman ostoksen currentin
     * tapahtumista.
     * 
     */
    public static final void poistaOstos() {
        int i = 1;
        for (Tapahtuma ostos : Kayttoliittyma.current.getOstokset()) {
            System.out.println(i + ". " + ostos);
            i++;
        }
        System.out.println("Syötä ostoksen järjestysnumero: ");
        int syote = Toimintoja.kysyKokonaisluku(1, 
                Kayttoliittyma.current.getOstokset().size() + 1);
        Kayttoliittyma.current.tapahtumat.remove
        (Kayttoliittyma.current.getOstokset().get(syote - 1));
        System.out.println("Ostos poistettu.");
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman tulon currentin
     * tapahtumista.
     * 
     */
    public static final void poistaTulo() {
        int i = 1;
        for (Tapahtuma tulo : Kayttoliittyma.current.getTulot()) {
            System.out.print(i + ". " + tulo);
            i++;
        }
        System.out.println("Syötä tulon järjestysnumero: ");
        int syote = Toimintoja.kysyKokonaisluku
        (1, Kayttoliittyma.current.getTulot().size() + 1);
        Kayttoliittyma.current.tapahtumat.remove
        (Kayttoliittyma.current.getTulot().get(syote - 1));
        System.out.println("Tulo poistettu.");
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman velan currentin
     * tapahtumista.
     * 
     */
    public static final void poistaVelka() {
        int i = 1;
        for (Tapahtuma velka : Kayttoliittyma.current.getVelat()) {
            System.out.print(i + ". " + velka);
            i++;
        }
        System.out.println("Syötä velan järjestysnumero: ");
        int syote = Toimintoja.kysyKokonaisluku(1, 
                Kayttoliittyma.current.getVelat().size() + 1);
        Kayttoliittyma.current.tapahtumat.remove
        (Kayttoliittyma.current.getVelat().get(syote - 1));
        System.out.println("Velka poistettu.");
    }

    /**
     * Metodi poistaa ohjelman käyttäjän valitseman säästön currentin
     * tapahtumista.
     * 
     */
    public static final void poistaSaasto() {
        int i = 1;
        for (Tapahtuma saasto : Kayttoliittyma.current.getSaastot()) {
            System.out.print(i + ". " + saasto);
            i++;
        }
        System.out.println("Syötä säästön järjestysnumero: ");
        int syote = Toimintoja.kysyKokonaisluku(1, Kayttoliittyma.current.getSaastot().size() + 1);
        Kayttoliittyma.current.tapahtumat.remove(Kayttoliittyma.current.getSaastot().get(syote - 1));
        System.out.println("Säästö poistettu.");
    }
    
    
}
