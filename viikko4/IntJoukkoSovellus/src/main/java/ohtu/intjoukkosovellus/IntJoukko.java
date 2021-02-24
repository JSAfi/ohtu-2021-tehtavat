
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = luoNollaTaulukko(OLETUSKAPASITEETTI);
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        testaaIndeksiParametrinPositiivisuus(kapasiteetti, "Joukon kapasiteetti ei voi olla negatiivinen!");
        ljono = luoNollaTaulukko(kapasiteetti);
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        testaaIndeksiParametrinPositiivisuus(kapasiteetti, "Joukon kapasiteetti ei voi olla negatiivinen!");
        testaaIndeksiParametrinPositiivisuus(kasvatuskoko, "Joukkoa ei voi kasvattaa negatiivisella määrällä");
        ljono = luoNollaTaulukko(kapasiteetti);
        alkioidenLkm = 0;

        this.kasvatuskoko = kasvatuskoko;
    }

    public static void testaaIndeksiParametrinPositiivisuus(int testattavaParametri, String poikkeus) {
        if (testattavaParametri<0) {
            throw new IndexOutOfBoundsException(poikkeus);
        }
    }

    public static int[] luoNollaTaulukko(int kapasiteetti) {
        int[] nollaTaulukko = new int[kapasiteetti];
        for (int i = 0; i < nollaTaulukko.length; i++) {
            nollaTaulukko[i] = 0;
        }
        return nollaTaulukko;
    }

    public boolean kuuluu(int luku) {
        if (etsiLuvunPaikka(luku)>-1) {
            return true;
        }
        return false;
    }
/*
* Palauttaa luvun ensimmäisen indeksin joukossa
 */
    public int etsiLuvunPaikka(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if(luku == ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku) == false) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if(alkioidenLkm % ljono.length == 0) {
                int[] vanhaTaulukko = new int[ljono.length];
                vanhaTaulukko = ljono;
                kopioiTaulukko(ljono, vanhaTaulukko);
                ljono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(vanhaTaulukko, ljono);
            }
            return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int apu;

        if(kuuluu(luku)) {
            int luvunIndeksi = etsiLuvunPaikka(luku);
            ljono[luvunIndeksi] = 0;

            for (int j = luvunIndeksi; j < alkioidenLkm - 1; j++) {
                apu = ljono[j];
                ljono[j] = ljono[j + 1];
                ljono[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];

        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }

        return taulu;
    }
/*
* Palauttaa arvon lukujonon indeksistä
 */
    public int getArvo(int indeksi) {
        if (indeksi < alkioidenLkm && 0 <= indeksi) {
            return ljono[indeksi];
        }
        throw new IndexOutOfBoundsException("Haettavan indeksin arvo virheellinen");
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            x.lisaa(a.getArvo(i));
        }
        for (int i = 0; i < b.alkioidenLkm; i++) {
            x.lisaa(b.getArvo(i));
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();

        for (int i = 0; i < a.alkioidenLkm; i++) {
            for (int j = 0; j < b.alkioidenLkm; j++) {
                if (a.getArvo(i) == b.getArvo(j)) {
                    y.lisaa(b.getArvo(j));
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();

        for (int i = 0; i < a.alkioidenLkm; i++) {
            z.lisaa(a.getArvo(i));
        }
        for (int i = 0; i < b.alkioidenLkm; i++) {
            z.poista(b.getArvo(i));
        }
 
        return z;
    }
        
}
