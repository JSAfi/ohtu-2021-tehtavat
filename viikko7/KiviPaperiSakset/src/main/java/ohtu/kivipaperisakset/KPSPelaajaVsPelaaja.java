package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);

    protected String toisenSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();

        return tokanSiirto;
    }
    protected void paivitaPeliTilanne(String siirto) {
        // ihmispelaajaa vastaan tätä toiminnallisuutta ei tarvita tämän harjoitustyön puitteissa
    }
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}