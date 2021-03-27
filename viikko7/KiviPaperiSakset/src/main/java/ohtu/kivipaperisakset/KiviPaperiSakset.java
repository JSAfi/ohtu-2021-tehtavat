package ohtu.kivipaperisakset;
import java.util.Scanner;

public abstract class KiviPaperiSakset {
    private static final Scanner scanner = new Scanner(System.in);

    // tämä on ns template metodi
    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            paivitaPeliTilanne(ekanSiirto);

            System.out.println(tuomari);
            System.out.println();

            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    protected String ensimmaisenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    // tämä on abstrakti metodi sillä sen toteutus vaihtelee eri pelityypeissä
    abstract protected String toisenSiirto();

    // tämän metodin tarkoitus on päivittää pelitilanne tekoälyä tai muuta pelin toimintalogiikkaa varten
    // metodin avulla voisi esimerkiksi antaa ihmispelaajalle ehdotuksen seuraavasta siirrosta
    // "oikeassa käytössä" tarvitsisi luultavasti myös tiedon toisen pelaajan siirrosta
    abstract protected void paivitaPeliTilanne(String siirto);

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
