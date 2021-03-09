package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    private Sovelluslogiikka sovelluslogiikka;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;
    private int edeltavaArvo;

    public Summa(TextField tuloskentta, TextField syotekentta,  Button plus, Button miinus, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
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

        this.sovelluslogiikka.plus(syote);

        paivitaMuutKomponentit();
    }
/*
* Undo toiminto perustuu edellisen tulosrivin arvon tallentamiseen
* Sovelluslogiikkaan voisi koodata asetaArvo -metodin
* Mutta tässä lasketaan muutos edelliseen arvoon nähden kun palautus tehdään
* ja suoritetaan laskutoiminto
 */
    public void peru() {

        int muutosEdelliseen = sovelluslogiikka.tulos() - this.edeltavaArvo;

        sovelluslogiikka.miinus(muutosEdelliseen);

        paivitaMuutKomponentit();
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
