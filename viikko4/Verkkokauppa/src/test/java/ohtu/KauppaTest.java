package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;

    @Before
    public void setUp() {
        this.pankki = mock(Pankki.class);

        this.viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        this.varasto = mock(Varasto.class);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(50);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mämmi", 2));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "piirakka", 10));
    }
    /* Testataan korista poistaminen.
    *  Varaston palautaVarastoon -metodia on tällöin pitänyt kutsua kerran
    *  ja kutsussa pitänyt olla parametrina sama tuote kuin mitä on ollut tarkoitus poistaa korista ja palauttaa
    *  varastoon
    * */
    @Test
    public void testaaPoistaKorista() {
        Kauppa k = new Kauppa(this.varasto, this.pankki, this.viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("mai", "12345-666");
        verify(varasto, times(1)).palautaVarastoon(new Tuote(1, "maito", 2));
    }
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        Kauppa k = new Kauppa(this.varasto, this.pankki, this.viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("mai", "12345-666");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(10));
        k.aloitaAsiointi();

        /* Tuote 2 maksa 2 rahaa, jos korissa olisi jotain jäljellä edellisestä kerrasta, tilimaksu olisi jotain muuta
            kuin 2
         */
        k.lisaaKoriin(2);
        k.tilimaksu("mai", "12345-666");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(2));
    }
    /*
    Testataan että joka maksutapahtumassa pyydetään uusi viitenumero;
    Tehdään mock viitegeneraattori, varmistetaan että sen viitenumeron generointia on kutsuttu
    yhtä monta kertaa kuin on ostostapahtumia
     */
    @Test
    public void kauppaPyytaaUudenViitenumeron() {
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);

        Kauppa k = new Kauppa(this.varasto, this.pankki, mockViite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("mai", "12345-666");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(1);
        k.tilimaksu("mai", "12345-666");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("mai", "12345-666");

        verify(mockViite, times(3)).uusi();
    }
    @Test
    public void testaaLoppunutTuote() {
        Kauppa k = new Kauppa(this.varasto, this.pankki, this.viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("mai", "12345-666");

        verify(pankki).tilisiirto(eq("mai"), eq(42), eq("12345-666"), eq("33333-44455"),eq(2));
    }
    @Test
    public void testaaTilisiirtoKahdellaSamallaTuotteella() {
        Kauppa k = new Kauppa(this.varasto, this.pankki, this.viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("mai", "12345-666");

        verify(pankki).tilisiirto(eq("mai"), eq(42), eq("12345-666"), eq("33333-44455"),eq(4));

    }
    @Test
    public void testaaTilisiirtoKahdellaEriTuotteella() {
        Kauppa k = new Kauppa(this.varasto, this.pankki, this.viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("mai", "12345-666");

        verify(pankki).tilisiirto(eq("mai"), eq(42), eq("12345-666"), eq("33333-44455"),eq(7));
    }
    @Test
    public void testaaTilisiirtoKutsunParametrit() {

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
}