package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;

public class TapahtumaTulostus {

    /**
     * @param ostot on true, mikäli käyttäjä haluaa ostonsa tulostettavan
     * @param velat on true, mikäli käyttäjä haluaa velkansa tulostettavan
     * @param tulot on true, mikäli käyttäjä haluaa tulonsa tulostettavan
     * @param saastot on true, mikäli käyttäjä haluaa säästönsä tulostettavan
     *
     * Metodi käy läpi kaikki currentin tapahtumat ja tulostaa ne pitkänä
     * merkkijonona.
     */
    public static final void listaaTapahtumat(boolean ostot, boolean velat,
            boolean tulot, boolean saastot) {
        int i = 1;
        String tulostettava = "";
        if (velat) {
            tulostettava += "\n";
            if (Kayttoliittyma.current.getVelat().isEmpty()) {
                tulostettava += "Ei velkoja. \n";
            } else {
                tulostettava += "Velat: \n";
                for (Tapahtuma c : Kayttoliittyma.current.getVelat()) {
                    tulostettava += i + ". " + c.toString() + " \n";
                    i++;
                }
            }
        }
        i = 1;
        if (tulot) {
            tulostettava += "\n";
            if (Kayttoliittyma.current.getTulot().isEmpty()) {
                tulostettava += "Ei tuloja. \n";
            } else {
                tulostettava += "Tulot: \n";
                for (Tapahtuma c : Kayttoliittyma.current.getTulot()) {
                    tulostettava += i + ". " + c.toString() + " \n";
                    i++;
                }
            }
        }
        i = 1;
        if (saastot) {
            tulostettava += "\n";
            if (Kayttoliittyma.current.getSaastot().isEmpty()) {
                tulostettava += "Ei säästöjä. \n";
            } else {
                tulostettava += "Säästöt: \n";
                for (Tapahtuma c : Kayttoliittyma.current.getSaastot()) {
                    tulostettava += i + ". " + c.toString() + " \n";
                    i++;
                }
            }
        }
        i = 1;
        if (ostot) {
            tulostettava += "\n";
            if (Kayttoliittyma.current.getOstokset().isEmpty()) {
                tulostettava += "Ei ostoksia. \n";
            } else {
                tulostettava += "Ostokset: \n";
                for (Tapahtuma c : Kayttoliittyma.current.getOstokset()) {
                    tulostettava += i + ". " + c.toString() + " \n";
                    i++;
                }
            }
        }
        System.out.println(tulostettava);
    }

}
