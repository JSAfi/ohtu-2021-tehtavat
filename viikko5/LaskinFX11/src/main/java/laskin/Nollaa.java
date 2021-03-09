package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private Sovelluslogiikka sovelluslogiikka;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super();
        this.sovelluslogiikka = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.nollaa = nollaa;
    }
    public void suorita() {
        int syote = 0;

        if(!syotekentta.getText().equals("")) {
            syote = Integer.parseInt(syotekentta.getText());
        }

        this.sovelluslogiikka.nollaa();

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

        this.nollaa.disableProperty().set(true);
    }
    public void peru() {

    }
}
