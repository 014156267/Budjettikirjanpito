package budjettikirjanpito.gui;

import static budjettikirjanpito.gui.Kayttoliittyma.current;
import static budjettikirjanpito.gui.Kayttoliittyma.lukija;

public class Toimintoja {

    /**
     * Metodi pyytää vanhaa salasanaa, jota ohjelma vertaa kirjautuneen
     * käyttäjän syöttämään salasanaan. Tämän jälkeen ohjelma pyytää uutta
     * salasanaa ja liittää sen kirjautuneen käyttäjän salasanaksi.
     */
    public static final void vaihdaSalasana() {
        System.out.println("\nSyötä vanha salasana: ");

        String vanha = lukija.nextLine();
        {
            if (current.salasana.equals(vanha)) {
                System.out.println("\nSyötä uusi salasana: ");
                String uusi = lukija.nextLine();
                current.setSalasana(uusi);
                System.out.println("\nSalasana vaihdettu!");
            } else {
                System.out.println("\nSalasana on virheellinen.");
            }
        }
    }

    /**
     * @param a luvun alaraja
     * @param b luvun yläraja
     *
     * Tämä apumetodi kysyy ohjelman käyttäjältä lukua, muokkaa sen
     * desimaaliluvuksi ja heittää poikkeuksen, mikäli ohjelman käyttäjä syöttää
     * jotain muuta kuin välillä [a,b] luvun.
     *
     * @return ohjelman käyttäjän syöttämä luku
     */
    public static final double kysyLuku(int a, int b) {
        while (true) {
            try {
                double luku = Double.parseDouble(Kayttoliittyma.lukija.nextLine().replace(",", "."));
                if (luku >= a && luku <= b) {
                    return luku;
                } else {
                    System.out.println("\nSyötä luku, joka on välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("\nLue mitä pyydetään syöttämään!");
            }
        }
    }

    /**
     * @param a luvun alaraja
     * @param b luvun yläraja
     *
     * Tämä apumetodi kysyy ohjelman käyttäjältä lukua ja heittää poikkeuksen,
     * mikäli ohjelman käyttäjä syöttää jotain muuta kuin kokonaisluvun.
     *
     * @return ohjelman käyttäjän syöttämä luku
     */
    public static final int kysyKokonaisluku(int a, int b) {
        while (true) {
            try {
                int luku = Integer.parseInt(Kayttoliittyma.lukija.nextLine());
                if (luku >= a && luku <= b) {
                    return luku;
                } else {
                    System.out.println("\nSyötä kokonaisluku, joka on "
                            + "välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("\nLue mitä pyydetään syöttämään!");
            }
        }
    }

}
