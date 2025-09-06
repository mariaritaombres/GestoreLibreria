package test.strategyTest;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import main.strategy.ordinamento.OrdinamentoPerAutore;
import main.strategy.ordinamento.OrdinamentoPerTitolo;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrdinamentoTest {

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
                .setStato(Stato.daLeggere)
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
    public void ordinaTitoloTest(){
        libreria.ordinamento(new OrdinamentoPerTitolo());
        libreria.ordina();
        List<Libro> libri= libreria.getLibreria();

        assertEquals("Crypto", libri.get(0).getTitolo());
        assertEquals("Dune", libri.get(1).getTitolo());
        assertEquals("Il caso Alaska Sanders", libri.get(2).getTitolo());
    }

    @Test
    public void ordinaAutoreTest(){
        libreria.ordinamento(new OrdinamentoPerAutore());
        libreria.ordina();
        List<Libro> libri= libreria.getLibreria();

        assertEquals("Dan Brown",libri.get(0).getAutore());
        assertEquals("Frank Herbert",libri.get(1).getAutore());
        assertEquals("Joel Dicker",libri.get(2).getAutore());

    }



}
