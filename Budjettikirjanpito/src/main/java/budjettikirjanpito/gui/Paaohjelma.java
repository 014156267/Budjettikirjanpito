package budjettikirjanpito.gui;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Pääohjelma saa luokkamuuttujana Kayttoliittyma-olion ja suorittaa
 * main-metodissa kaynnista-metodin eli suorittaa käyttöliittymän.
 */
public class Paaohjelma {

    public static Kayttoliittyma kayttoliittyma;

    public static final void main(final String[] args) throws IOException,
            FileNotFoundException, ClassNotFoundException {
        kayttoliittyma = new Kayttoliittyma();
        kayttoliittyma.kaynnista();
    }
}
