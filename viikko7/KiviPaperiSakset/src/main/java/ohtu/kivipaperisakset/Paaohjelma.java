package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Valikko paaValikko = new Valikko(new KPSPeliTehdas(), scanner);
        paaValikko.nayta();
    }
}
