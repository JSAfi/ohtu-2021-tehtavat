package ohtu.verkkokauppa;
import org.springframework.stereotype.Component;

public class Viitegeneraattori implements GeneraattoriInterface {

    
    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    public int uusi(){
        return seuraava++;
    }
}