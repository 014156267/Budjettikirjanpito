package budjettikirjanpito.logiikka.kayttajat;

import budjettikirjanpito.logiikka.rahaliikenne.Velka;
import java.util.ArrayList;

public class Perhe extends Kayttaja{
    
    private ArrayList<Henkilo> henkilot;

    public Perhe()  {
        super();
        henkilot = new ArrayList<>();
    }
    
    
    
    
    @Override
    public String toString() {
       String tulostettava = "";
        for (Henkilo h : henkilot) {
            tulostettava += h.toString();
        }
       return tulostettava;
    }
    
    
}
