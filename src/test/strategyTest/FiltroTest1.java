package test.strategyTest;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import main.strategy.confronti.ConfrontoEsatto;
import main.strategy.ricerca.FiltroPerAutore;
import main.strategy.ricerca.FiltroPerGenere;
import main.strategy.ricerca.FiltroPerStato;
import main.strategy.ricerca.FiltroPerTitolo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
Separo testing sul pattern Strategy e Chain of Responsability per la ricerca e filtraggio dei libri.
In questo test gestisco: filtro per titolo, autore, genere e stato di lettura.
 */



public class FiltroTest1 {
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
                .setValutazione(0)
                .setGenere("Giallo")
                .setStato(Stato.inLettura)
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
    public void filtroTitoloTest(){
        libreria.filtroRicerca(new FiltroPerTitolo("Il caso Alaska Sanders", new ConfrontoEsatto()));
        libreria.filtra();
        assertEquals(1,libreria.getLibreria().size());
        assertEquals("Il caso Alaska Sanders", libreria.getLibreria().get(0).getAutore());
    }

    @Test
    public void filtroAutoreTest(){
        libreria.filtroRicerca(new FiltroPerAutore("Dan Brown", new ConfrontoEsatto()));
        libreria.filtra();
        assertEquals(1,libreria.getLibreria().size());
        assertEquals("Dan Brown", libreria.getLibreria().get(0).getAutore());
    }


    @Test
    public void filtroGenereTest(){
        libreria.filtroRicerca(new FiltroPerGenere("Giallo", new ConfrontoEsatto()));
        libreria.filtra();
        assertEquals(2,libreria.getLibreria().size());
        assertEquals("Giallo",libreria.getLibreria().get(0).getGenere());
        assertEquals("Giallo",libreria.getLibreria().get(1).getGenere());
    }


    @Test
    public void filtroLibriLettiTest(){
        libreria.filtroRicerca(new FiltroPerStato(Stato.letto,new ConfrontoEsatto()));
        libreria.filtra();
        assertEquals(2,libreria.getLibreria().size());
        assertEquals(Stato.letto,libreria.getLibreria().get(0).getStato());
        assertEquals(Stato.letto,libreria.getLibreria().get(1).getStato());

    }
    @Test
    public void filtroLibriInLetturaTest(){
        libreria.filtroRicerca(new FiltroPerStato(Stato.inLettura,new ConfrontoEsatto()));
        libreria.filtra();
        assertEquals(1,libreria.getLibreria().size());
        assertEquals(Stato.inLettura,libreria.getLibreria().get(0).getStato());

    }
    @Test
    public void filtroLibriDaLeggereTest(){
        libreria.filtroRicerca(new FiltroPerStato(Stato.daLeggere,new ConfrontoEsatto()));
        libreria.filtra();
        assertEquals(0,libreria.getLibreria().size());

    }






}
