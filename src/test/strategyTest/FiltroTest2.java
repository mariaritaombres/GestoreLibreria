package test.strategyTest;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import main.strategy.confronti.valutazioni.ConfrontoValMaggioreUguale;
import main.strategy.confronti.valutazioni.ConfrontoValMinoreUguale;
import main.strategy.ricerca.FiltroPerValutazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
Seconda classe di testing pattern Strategy e Chain of Responsability per la ricerca e filtraggio.
 Questo test controlla le valutazioni, dato il vincolo dello stato di lettura in già letto.
 */


public class FiltroTest2 {
    private GestioneLibreria libreria;
    @BeforeEach
    public void setup(){
        libreria= new GestioneLibreria();
        libreria.resetLibreria();

        Libro libro1=new Libro.Builder()
                .setTitolo("Dune")
                .setAutore("Frank Herbert")
                .setCodiceISBN("9788834739679")
                .setValutazione(4)
                .setGenere("Fantascienza")
                .setStato(Stato.letto)
                .build();
        Libro libro2=new Libro.Builder()
                .setTitolo("Il caso Alaska Sanders")
                .setAutore("Joel Dicker")
                .setCodiceISBN("9788834610572")
                .setValutazione(1)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();
        Libro  libro3=new Libro.Builder()
                .setTitolo("Crypto")
                .setAutore("Dan Brown")
                .setCodiceISBN("9788804675051")
                .setValutazione(2)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
        libreria.aggiungiLibro(libro3);


    }

    @Test
    public void filtroValutazioneMaxTest(){
        libreria.filtroRicerca(new FiltroPerValutazione(3,new ConfrontoValMaggioreUguale()));
        libreria.filtra();
        assertEquals(1,libreria.getLibreria().size());
        for(Libro l: libreria.getLibreria()){
            assertTrue(l.getValutazione()>=3);
        }
    }

    @Test
    public void filtroValutazioneMinTest(){
        libreria.filtroRicerca(new FiltroPerValutazione(2, new ConfrontoValMinoreUguale()));
        libreria.filtra();
        assertEquals(2,libreria.getLibreria().size());
        for(Libro l: libreria.getLibreria()){
            assertTrue(l.getValutazione()<=2);
        }

    }












}
