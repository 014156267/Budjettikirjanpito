package budjettikirjanpito.logiikka.kayttajat;

public class Yritys extends Kayttaja {

    private String nimi;
    private String ytunnus;

    public Yritys(String nimi, String ytunnus) {
        super();
        this.nimi = nimi;
        this.ytunnus = ytunnus;
    }

    public final String getNimi() {
        return nimi;
    }

    public final String getYtunnus() {
        return ytunnus;
    }

    public final void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public final void setYtunnus(String ytunnus) {
        this.ytunnus = ytunnus;
    }

    public final String toString() {
        return nimi;
    }
}
