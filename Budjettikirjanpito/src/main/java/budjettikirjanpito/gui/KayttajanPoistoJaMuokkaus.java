package budjettikirjanpito.gui;

import budjettikirjanpito.database.Database;
import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class KayttajanPoistoJaMuokkaus {

    /**
     * Metodi kysyy ohjelman käyttäjältä poistettavan käyttäjän salasanaa ja
     * mikäli salasana on tällä hetkellä kirjautuneena olevan käyttäjän
     * salasana, käyttäjä kirjautuu ulos. Tämän jälkeen salasanaa vastaava
     * käyttäjä poistetaan. Mikäli salasanaa ei ole kenelläkään luodulla
     * käyttäjällä, metodi ilmoittaa salasanan virheellisyydestä ja palaa
     * takaisin valikkoon, josta metodiin päädyttiin.
     *
     * @throws java.io.IOException
     */
    public static final void poistaKayttaja() throws IOException {
        System.out.println("\nSyötä salasanasi: ");
        String salasana = Kayttoliittyma.lukija.nextLine();
        if (Kayttoliittyma.current.salasana.equals(salasana)) {
            Kayttoliittyma.kayttajat.remove(Kayttoliittyma.current);
            
            Database.tietojenPoisto();
            System.out.println("\n" + Kayttoliittyma.current.toString()
                    + " poistettu." );
            Kayttoliittyma.current = null;
        } else {
            System.out.println("\nSalasana ei täsmää käyttäjätunnukseesi.");
        }
    }

}
