package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private Sovelluslogiikka sovelluslogiikka;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private int edeltavaArvo;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super();
        this.sovelluslogiikka = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.nollaa = nollaa;
        this.undo = undo;
    }
    public void suorita() {
        this.edeltavaArvo = sovelluslogiikka.tulos();

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
        int muutosEdelliseen = this.edeltavaArvo;

        sovelluslogiikka.plus(muutosEdelliseen);

        this.paivitaMuutKomponentit();
    }
    private void paivitaMuutKomponentit() {
        int laskunTulos = sovelluslogiikka.tulos();
        this.tuloskentta.setText("" + laskunTulos);
        this.syotekentta.setText("");

        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }

        this.undo.disableProperty().set(false);
    }
}
