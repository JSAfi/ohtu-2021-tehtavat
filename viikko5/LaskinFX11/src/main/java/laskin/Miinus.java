package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Miinus extends Komento{
    private Sovelluslogiikka sovelluslogiikka;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private int edeltavaArvo;
    public Miinus(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super();
        this.sovelluslogiikka = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        this.nollaa = nollaa;
        this.undo = undo;
    }
    public void suorita() {
        int syote = 0;

        if(!syotekentta.getText().equals("")) {
            syote = Integer.parseInt(syotekentta.getText());
        }
        this.edeltavaArvo = sovelluslogiikka.tulos();

        this.sovelluslogiikka.miinus(syote);

        this.paivitaMuutKomponentit();
    }
    public void peru() {
        int muutosEdelliseen = this.edeltavaArvo - sovelluslogiikka.tulos();

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
