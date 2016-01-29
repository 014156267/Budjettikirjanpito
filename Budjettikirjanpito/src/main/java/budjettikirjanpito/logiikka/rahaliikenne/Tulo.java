
package budjettikirjanpito.logiikka.rahaliikenne;

public class Tulo extends Tapahtuma{
    
    private String maksaja;

    public Tulo(String maksaja, double maara, String selitys) {
        super(maara, selitys);
        this.maksaja = maksaja;
    }
    
    
    
}
