package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);
    private TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    protected String toisenSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);

        return tokanSiirto;
    }
    protected void paivitaPeliTilanne(String siirto) {
        tekoaly.asetaSiirto(siirto);
    }
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
