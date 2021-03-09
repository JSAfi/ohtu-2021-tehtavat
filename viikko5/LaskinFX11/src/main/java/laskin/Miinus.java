package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Miinus extends Komento{
    private Sovelluslogiikka sovelluslogiikka;
    private TextField tuloskentta;
    private TextField syotekentta;
    public Miinus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button plus, Button miinus, Button undo, Sovelluslogiikka sovellus) {
        super();
        this.sovelluslogiikka = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
    }
    public void suorita() {
        int syote = 0;

        if(!syotekentta.getText().equals("")) {
            syote = Integer.parseInt(syotekentta.getText());
        }

        this.sovelluslogiikka.miinus(syote);
/*
* METODIN KOHEESIO HEIKKENEE TÄSSÄ:
* on tyhmää että metodissa päivitetään myös käyttöliittymän komponenttien arvoja
* ei liity tähän komentoon itsessään eli metodi tekee monia asioita
* muutakin kuin alkuperäisen tehtävänsä
* en valitettavasti osaa korjata tehokkaasti tätä
 */
        int laskunTulos = sovelluslogiikka.tulos();
        this.tuloskentta.setText("" + laskunTulos);
        this.syotekentta.setText("");
    }
    public void peru() {

    }
}
