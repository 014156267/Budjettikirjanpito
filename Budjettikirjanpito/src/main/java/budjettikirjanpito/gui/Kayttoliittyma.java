package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.kayttajat.Henkilo;
import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.kayttajat.Perhe;
import budjettikirjanpito.logiikka.kayttajat.Yritys;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Kayttoliittyma {

    public ArrayList<Kayttaja> kayttajat;
    public Scanner lukija;
    public String tiedostonNimi;

    public Kayttoliittyma() {
        kayttajat = new ArrayList<>();
        lukija = new Scanner(System.in);
        tiedostonNimi = "";
    }

    public void kaynnista() throws FileNotFoundException, IOException, ClassNotFoundException {
        if (new File("kayttajat.ser").exists()) {
            FileInputStream fis = new FileInputStream("kayttajat.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            kayttajat = (ArrayList<Kayttaja>) ois.readObject();
            ois.close();
            fis.close();
        }

        System.out.println("Tervetuloa!");
        while (true) {
            System.out.println("");
            System.out.println("Lisää käyttäjä syöttämällä 1");
            System.out.println("Poista käyttäjä syöttämällä 2");
            System.out.println("Lisää tapahtuma syöttämällä 3");
            System.out.println("Poista tapahtuma syöttämällä 4");
            System.out.println("Tulostus syöttämällä 5");
            System.out.println("Lopeta syöttämällä x");
            String syote = lukija.nextLine();
            if (syote.equals("x")) {
                break;
            } else if (syote.equals("1")) {
                while (true) {
                    System.out.println("Henkilo 1");
                    System.out.println("Perhe 2");
                    System.out.println("Yritys 3");
                    System.out.println("Takaisin x");
                    String tyyppi = lukija.nextLine();
                    if (tyyppi.equals("1")) {
                        System.out.println("Etunimi: ");
                        String etunimi = lukija.nextLine();
                        System.out.println("Sukunimi: ");
                        String sukunimi = lukija.nextLine();
                        Henkilo henkilo = new Henkilo(etunimi, sukunimi);
                        System.out.println("Anna salasana: ");
                        while (true) {
                            String salasana = lukija.nextLine();
                            if (salasananTarkistus(salasana)) {
                                henkilo.salasana = salasana;
                                System.out.println("Henkilö " + henkilo + " luotu.");
                                kayttajat.add(henkilo);
                                break;
                            }
                            System.out.println("Salasana käytössä. Anna toinen salasana: ");
                        }
                        break;
                    } else if (tyyppi.equals("2")) {
                        Perhe perhe = new Perhe();
                        while (true) {
                            System.out.println("Lisää henkilö perheeseen 1");
                            System.out.println("Valmis x");
                            String syote2 = lukija.nextLine();
                            if (syote2.equals("x")) {
                                System.out.println("Anna perheellesi salasana: ");
                                while (true) {
                                    String salasana = lukija.nextLine();
                                    if (salasananTarkistus(salasana)) {
                                        perhe.salasana = salasana;
                                        kayttajat.add(perhe);
                                        System.out.println("Perhe luotu.");
                                        break;
                                    }
                                    System.out.println("Salasana käytössä. Anna toinen salasana: ");
                                }
                                break;
                            } else if (syote2.equals("1")) {
                                System.out.println("Henkilön salasana: ");
                                String salasana = lukija.nextLine();
                                boolean onnistui = false;
                                for (Kayttaja kayttaja : kayttajat) {
                                    if (kayttaja.salasana.equals(salasana) && kayttaja instanceof Henkilo) {
                                        onnistui = true;
                                        System.out.println(kayttaja + " lisätty perheeseen.");
                                        perhe.lisaaHenkilo((Henkilo) kayttaja);
                                        break;
                                    }
                                }
                                if (!onnistui) {
                                    System.out.println("Antamallasi salasanalla ei löydy henkilöä.");
                                }

                            } else {
                                System.out.println("Syötä 1 tai x.");
                            }
                        }
                        break;
                    } else if (tyyppi.equals("3")) {
                        System.out.println("Anna yrityksen nimi: ");
                        String nimi = lukija.nextLine();
                        System.out.println("Anna yrityksen y-tunnus: ");
                        String ytunnus = lukija.nextLine();
                        System.out.println("Anna yrityksellesi salasana: ");
                        String salasana = lukija.nextLine();
                        Yritys yritys = new Yritys(nimi, ytunnus);
                        yritys.salasana = salasana;
                        System.out.println("Yritys " + yritys + " luotu.");
                        kayttajat.add(yritys);
                        break;
                    } else if (tyyppi.equals("x")) {
                        break;
                    }
                }
            } else if (syote.equals("2")) {
                System.out.println("Syötä poistettavan käyttäjän salasana: ");
                String salasana = lukija.nextLine();
                boolean onnistui = false;
                for (Kayttaja kayttaja : kayttajat) {
                    if (kayttaja.salasana.equals(salasana)) {
                        kayttajat.remove(kayttaja);
                        System.out.println("Käyttäjä poistettu.");
                        onnistui = true;
                        break;
                    }
                }
                if (!onnistui) {
                    System.out.println("Salasana on virheellinen.");
                }
            } else if (syote.equals("5")) {
                for (Kayttaja k : kayttajat) {
                    System.out.println(k);
                }
            } else {
                System.out.println("Syötä kelvollinen syöte.");
            }
        }

        System.out.println("Tallenetaanko muutokset? (k/e)");
        while (true) {
            String vastaus = lukija.nextLine();
            if (vastaus.equals("k")) {
                System.out.println("Tallennetaan...");
                FileOutputStream fos = new FileOutputStream("kayttajat.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(kayttajat);
                oos.close();
                fos.close();
                System.out.println("Tallennettu.");
                break;
            } else if (vastaus.equals("e")) {
                break;
            } else {
                System.out.println("Vastaa k tai e.");
            }
        }
    }

    public boolean salasananTarkistus(String s) {
        for (Kayttaja k : kayttajat) {
            if (k.salasana.equals(s)) {
                return false;
            }
        }
        return true;
    }

}
