package budjettikirjanpito.logiikka.kayttajat;

public class Yritys extends Kayttaja{
    
    private String nimi;
    private String ytunnus;
    
    public Yritys(String nimi, String ytunnus) {
        super();
        this.nimi = nimi;
        this.ytunnus = ytunnus;
    }

    public String getNimi() {
        return nimi;
    }

    public String getYtunnus() {
        return ytunnus;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setYtunnus(String ytunnus) {
        this.ytunnus = ytunnus;
    }
    
    public String toString() {
        return nimi;
    }
    
}
