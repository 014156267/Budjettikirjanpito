/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettikirjanpito.gui;

import budjettikirjanpito.database.Database;
import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Perhe;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Kirjautuminen {
    
    /**
     * Metodi mahdollistaa Ohjelman käyttäjän sisäänkirjautumisen.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static final void sisaanKirjautuminen() throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("\nSyötä käyttäjätunnuksesi: ");
        String tunnus = Kayttoliittyma.lukija.nextLine();
            if (Database.onkoKayttajaLuotu(tunnus)) {
                Kayttaja k = Database.tietynTietojenLataus(tunnus);
                System.out.println("\nSyötä salasanasi: ");
                String salasana = Kayttoliittyma.lukija.nextLine();
                if (k.salasana.equals(salasana)) {
                    Kayttoliittyma.current = k;
                    Database.tietojenLataus();
                    if (k instanceof Perhe) {
                        Perhe p = (Perhe) k;
                        Kayttoliittyma.current = p;
                        p.paivita();
                    }
                    
                    System.out.println("\nTervetuloa " + k.toString() + "!");
                    
                    return;
                } else {
                    System.out.println("\nSyöttämäsi salasana ei täsmää "
                            + "syöttämäsi käyttäjätunnuksen kanssa. Olet"
                            + "han varmasti luonut tunnuksen? Jos et, syötä 1");
                    return;
                }
            }
        
        System.out.println("Syöttämääsi käyttäjätunnusta ei ole.");
    }
    
}
