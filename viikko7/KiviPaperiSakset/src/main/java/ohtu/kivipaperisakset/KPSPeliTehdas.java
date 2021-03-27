package ohtu.kivipaperisakset;

// luokan tarkoitus on tuottaa tietyntyyppisen pelin instanssi
public class KPSPeliTehdas {
    static KiviPaperiSakset luoTekoalyPeli() {
        return new KPSTekoaly();
    }
    static KiviPaperiSakset luoParempiTekoalyPeli() {
        return new KPSParempiTekoaly();
    }
    static KiviPaperiSakset luoKaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }
}
