package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);
    private Tekoaly tekoaly = new Tekoaly();

    protected String toisenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);

        return tokanSiirto;
    }
    protected void paivitaPeliTilanne(String siirto) {
        // yksinkertainen tekoäly ei tee metodilla mitään, mutta tämä jätetty koska voisi teoreettisesti tehdä
        tekoaly.asetaSiirto(siirto);
    }

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}