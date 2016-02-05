/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettikirjanpito.gui;

import budjettikirjanpito.logiikka.kayttajat.Kayttaja;
import budjettikirjanpito.logiikka.rahaliikenne.Tapahtuma;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Paaohjelma {

    public static Kayttoliittyma kayttoliittyma;
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        kayttoliittyma = new Kayttoliittyma();
        kayttoliittyma.kaynnista();  
    }
    
}
