package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.kayttajat.Perhe;
import budjettikirjanpito.logiikka.rahaliikenne.Ostos;
import budjettikirjanpito.logiikka.rahaliikenne.Saasto;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import budjettikirjanpito.logiikka.rahaliikenne.Tulo;
import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.text.DecimalFormat;

public class Toimintoja {

    public static DecimalFormat muotoilu;

    public Toimintoja() {
        muotoilu = new DecimalFormat("###.##");
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
                    System.out.println("Syötä luku, joka on välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("Lue mitä pyydetään syöttämään!");
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
                    System.out.println("Syötä kokonaisluku, joka on "
                            + "välillä [" + a + ","
                            + b + "].");
                }
            } catch (Exception poikkeus) {
                System.out.println("Lue mitä pyydetään syöttämään!");
            }
        }
    }

    /**
     * Mikäli etodi käy läpi käyttäjän valitsemat currentin tapahtumat ja
     * palauttaa ne pitkänä merkkijonona.
     *
     */
    public static final void tapahtumaToiminnot() {
        System.out.println("\nValitse seuraavista:");
        System.out.println("Siirry yksittäisen tapahtuman tarkasteluun "
                + "syöttämällä 1");
        System.out.println("Kaikki tietyn tyyppiset tapahtumat syöttämällä 2");
        System.out.println("Tulosta kaikki tapahtumat syöttämällä 3");
        System.out.println("Kuukauden menot (ostokset sekä velkojen "
                + "kuukausierät) yhteensä syöttämällä 4");
        System.out.println("Kuukauden tulot yhteensä syöttämällä 5");
        System.out.println("Rahatilanne (tulot-menot) syöttämällä 6");
        if (Kayttoliittyma.current instanceof Perhe) {
            System.out.println("Perheenjäsenten velat yhteensä syöttämällä 7");
            System.out.println("Perheenjäsenten tulot yhteensä syöttämällä 8");
            System.out.println("Perheenjäsenten ostokset yhteensä syöttämällä 9");
        }
        System.out.println("Takaisin syöttämällä x");
        String syote = Kayttoliittyma.lukija.nextLine();
        if (syote.equals("x")) {
            return;
        } else if (syote.equals("1")) {
            yksittaisenTapahtumanHallinta();
        } else if (syote.equals("2")) {
            System.out.println("Mitä seuraavista haluat tulostaa?");
            System.out.println("Kertaostokset, syötä 1");
            System.out.println("Tulot, syötä 2");
            System.out.println("Velat, syötä 3");
            System.out.println("Säästöt, syötä 4");
            int luku = Toimintoja.kysyKokonaisluku(1, 4);
            if (luku == 1) {
                TapahtumaTulostus.listaaTapahtumat(true, false, false, false);
            } else if (luku == 2) {
                TapahtumaTulostus.listaaTapahtumat(false, false, true, false);
            } else if (luku == 3) {
                TapahtumaTulostus.listaaTapahtumat(false, true, false, false);
            } else if (luku == 4) {
                TapahtumaTulostus.listaaTapahtumat(false, false, false, true);
            }
        } else if (syote.equals("3")) {
            TapahtumaTulostus.listaaTapahtumat(true, true, true, true);
        } else if (syote.equals("4")) {
            System.out.println("Kuukauden menot "
                    + "yhteensä: " + Kayttoliittyma.current.getKuukaudenMenotYhteensa());
        } else if (syote.equals("5")) {
            System.out.println("Kuukauden tulot "
                    + "yhteensä: " + Kayttoliittyma.current.getKuukaudenTulotYhteensa());
        } else if (syote.equals("6")) {
            System.out.println("Kuukauden tilanne: " + Kayttoliittyma.current.getRahatilanne());
        } else if (syote.equals("7") || syote.equals("8") || syote.equals("9")) {
            if (Kayttoliittyma.current instanceof Perhe) {
                Perhe tutkittava = (Perhe) Kayttoliittyma.current;
                if (syote.equals("7")) {
                    System.out.println("Perheenjäsenillä velkaa yhteensä "
                            + tutkittava.getHenkiloidenVelatYhteensaString()
                            + " euroa.");
                } else if (syote.equals("8")) {
                    System.out.println("Perheenjäsenillä tuloja yhteensä "
                            + tutkittava.getHenkiloidenTulotYhteensaString()
                            + " euroa.");
                } else if (syote.equals("9")) {
                    System.out.println("Perheenjäsenillä ostoksia yhteensä "
                            + tutkittava.getHenkiloidenOstotYhteensaString()
                            + " euron edestä.");
                }
            } else {
                System.out.println("Anna kunnon syöte.");
            }
        } else {
            System.out.println("Anna kunnon syöte.");
        }
    }

    public static final void yksittaisenTapahtumanHallinta() {
        while (true) {
            System.out.println("Valitse tapahtumasi tyyppi: ");
            System.out.println("Kertaostokset, syötä 1");
            System.out.println("Tulot, syötä 2");
            System.out.println("Velat, syötä 3");
            System.out.println("Säästöt, syötä 4");
            System.out.println("Poistu, syötä 0");
            int luku = Toimintoja.kysyKokonaisluku(0, 4);
            if (luku == 1) {
                TapahtumaTulostus.listaaTapahtumat(true, false, false, false);
                ostostenHallinta();
            } else if (luku == 2) {
                TapahtumaTulostus.listaaTapahtumat(false, false, true, false);
                tulojenHallinta();
            } else if (luku == 3) {
                TapahtumaTulostus.listaaTapahtumat(false, true, false, false);
                velkojenHallinta();
            } else if (luku == 4) {
                TapahtumaTulostus.listaaTapahtumat(false, false, false, true);
                saastojenHallinta();
            } else if (luku == 0) {
                break;
            }
        }
    }

    public static final void ostostenHallinta() {
        Ostos kasiteltava;
        if (Kayttoliittyma.current.getOstokset().isEmpty()) {
            return;
        } else if (Kayttoliittyma.current.getOstokset().size() == 1) {
            kasiteltava = (Ostos) Kayttoliittyma.current.getOstokset().get(0);
        } else {
            System.out.println("Syötä tarkasteltavan ostoksen järjestysnumero.");
            int syote = kysyKokonaisluku(1,
                    Kayttoliittyma.current.getOstokset().size());
            kasiteltava
                    = (Ostos) Kayttoliittyma.current.getOstokset().get(syote - 1);
        }
        while (true) {
            System.out.println("Ostoksen suuruus, syötä 1");
            System.out.println("Ostoksen selitys, syötä 2");
            System.out.println("Takaisin, syötä 0");
            int luku = kysyKokonaisluku(0, 2);
            System.out.println("");
            if (luku == 1) {
                System.out.println(kasiteltava.getMaaraString());
            } else if (luku == 2) {
                System.out.println(kasiteltava.selitys);
            } else if (luku == 0) {
                break;
            }
        }
    }

    public static final void tulojenHallinta() {
        Tulo kasiteltava;
        if (Kayttoliittyma.current.getTulot().isEmpty()) {
            return;
        } else if (Kayttoliittyma.current.getTulot().size() == 1) {
            kasiteltava = (Tulo) Kayttoliittyma.current.getTulot().get(0);
        } else {
            System.out.println("Syötä tarkasteltavan tulon järjestysnumero. \n");
            int syote = kysyKokonaisluku(1,
                    Kayttoliittyma.current.getTulot().size());
            kasiteltava
                    = (Tulo) Kayttoliittyma.current.getTulot().get(syote - 1);
        }
        while (true) {
            System.out.println("Tulon suuruus, syötä 1");
            System.out.println("Tulon selitys, syötä 2");
            System.out.println("Tulon maksaja, syötä 3");
            System.out.println("Takaisin, syötä 0");
            int luku = kysyKokonaisluku(0, 3);
            System.out.println("");
            if (luku == 1) {
                System.out.println(kasiteltava.getMaaraString());
            } else if (luku == 2) {
                System.out.println(kasiteltava.selitys);
            } else if (luku == 3) {
                System.out.println(kasiteltava.maksaja);
            } else if (luku == 0) {
                break;
            }
        }
    }

    public static final void velkojenHallinta() {
        Velka kasiteltava;
        if (Kayttoliittyma.current.getVelat().isEmpty()) {
            return;
        } else if (Kayttoliittyma.current.getVelat().size() == 1) {
            kasiteltava = (Velka) Kayttoliittyma.current.getVelat().get(0);
        } else {
            System.out.println("Syötä tarkasteltavan velan järjestysnumero. \n");
            int syote = kysyKokonaisluku(1,
                    Kayttoliittyma.current.getVelat().size());
            kasiteltava
                    = (Velka) Kayttoliittyma.current.getVelat().get(syote - 1);
        }
        while (true) {
            System.out.println("Velan suuruus, syötä 1");
            System.out.println("Velan aihe, syötä 2");
            System.out.println("Velan selitys, syötä 3");
            System.out.println("Velan lyhennysaika, syötä 4");
            System.out.println("Velan vuosikorko, syötä 5");
            System.out.println("Velan kuukausimaksu, syötä 6");
            System.out.println("Velan kuukausittainen lyhennyserä, syötä 7");
            System.out.println("Paljonko velkaa x kuukauden päästä, "
                    + "syötä 8");
            System.out.println("Paljonko maksan korkoja yhteensä kaikkineen, "
                    + "syötä 9");
            System.out.println("Takaisin, syötä 0");
            System.out.println("");
            int luku = kysyKokonaisluku(0, 9);
            if (luku == 1) {
                System.out.println(kasiteltava.getMaaraString());
            } else if (luku == 2) {
                System.out.println(kasiteltava.getAihe());
            } else if (luku == 3) {
                System.out.println(kasiteltava.getSelitys());
            } else if (luku == 4) {
                System.out.println("Velan lyhennysaika on "
                        + kasiteltava.getLyhennysaika() + " kuukautta.");
            } else if (luku == 5) {
                System.out.println("Velan vuosikorko on "
                        + kasiteltava.getVuosikorkoString() + "%.");
            } else if (luku == 6) {
                System.out.println("Velan kuukausimaksu on "
                        + kasiteltava.kuukausimaksuString() + " euroa.");
            } else if (luku == 7) {
                System.out.println("Lyhennät varsinaista velkaa (ilman korkoja)"
                        + " " + kasiteltava.kuukaudenLyhennysEraString() + " euroa kuukaudessa.");
            } else if (luku == 8) {
                System.out.println("Määritä x: ");
                int kk = kysyKokonaisluku(1, 999999999);
                System.out.println("Velkaa on jäljellä "
                        + kasiteltava.paljonkoVelkaaJaljellaXKkPaastaString(kk)
                        + " euroa " + kk + ":n kuukauden päästä.");
            } else if (luku == 9) {
                System.out.println("Maksat tästä velasta korkomaksuja"
                        + " yhteensä " + kasiteltava.velanKorkoYhteensaString()
                        + " euroa.");
            } else if (luku == 0) {
                break;
            }
        }
    }

    public static final void saastojenHallinta() {
        Saasto kasiteltava;
        if (Kayttoliittyma.current.getSaastot().isEmpty()) {
            return;
        } else if (Kayttoliittyma.current.getSaastot().size() == 1) {
            kasiteltava = (Saasto) Kayttoliittyma.current.getSaastot().get(0);
        } else {
            System.out.println("Syötä tarkasteltavan säästön järjestysnumero.");
            int syote = kysyKokonaisluku(1,
                    Kayttoliittyma.current.getSaastot().size());
            kasiteltava = (Saasto) Kayttoliittyma.current.getSaastot().get(syote - 1);
        }
        while (true) {
            System.out.println("Säästettävä summa kokonaisuudessaan, syötä 1");
            System.out.println("Säästön selitys, syötä 2");
            System.out.println("Sääston kuukausimäärä, syötä 3");
            System.out.println("Kuukaudessa säästettävä summa, syötä 4");
            System.out.println("Takaisin, syötä 0");
            System.out.println("");
            int luku = kysyKokonaisluku(0, 4);
            if (luku == 1) {
                System.out.println(kasiteltava.getMaaraString());
            } else if (luku == 2) {
                System.out.println(kasiteltava.selitys);
            } else if (luku == 3) {
                System.out.println(kasiteltava.getKuukausiMaara());
            } else if (luku == 4) {
                System.out.println(kasiteltava.getKuukausiSummaString());
            } else if (luku == 0) {
                break;
            }
        }
    }
}
