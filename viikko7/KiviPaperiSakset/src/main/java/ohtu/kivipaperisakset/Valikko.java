package ohtu.kivipaperisakset;

import java.util.Scanner;

// luokan tarkoitus on olla käyttöliittymä: ottaa syöte ja suorittaa kytketty toiminnallisuus
public class Valikko {
    private Scanner io;
    private KPSPeliTehdas pelattavaPeli;

    public Valikko(KPSPeliTehdas peliTehdas, Scanner io) {
        this.pelattavaPeli = peliTehdas;
        this.io = io;
    }
    public void nayta() {
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = this.io.nextLine();
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

            if (vastaus.endsWith("a")) {
                pelattavaPeli.luoKaksinpeli().pelaa();
            } else if (vastaus.endsWith("b")) {
                pelattavaPeli.luoTekoalyPeli().pelaa();
            } else if (vastaus.endsWith("c")) {
                pelattavaPeli.luoParempiTekoalyPeli().pelaa();
            } else {
                break;
            }

        }
    }
}
