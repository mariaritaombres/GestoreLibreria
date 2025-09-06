package test;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/* testo la libreria tramite il pattern Facade, in modo da testare entrambi
 */
public class LibreriaTest {
    private  GestioneLibreria libreria;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    public void setup(){
        libreria= new GestioneLibreria();
              libreria.resetLibreria();
         libro1=new Libro.Builder()
                .setTitolo("Dune")
                .setAutore("Frank Herbert")
                .setCodiceISBN("9788834739679")
                .setValutazione(4)
                .setGenere("Fantascienza")
                .setStato(Stato.letto)
                .build();
         libro2=new Libro.Builder()
                .setTitolo("Il caso Alaska Sanders")
                .setAutore("Joel Dicker")
                 .setValutazione(0)
                .setCodiceISBN("9788834610572")
                .setGenere("Giallo")
                .setStato(Stato.daLeggere)
                .build();

        libreria.aggiungiLibro(libro1);
        libreria.aggiungiLibro(libro2);
    }


    @Test
     public void testAddLibro(){
        Libro  libro3=new Libro.Builder()
                .setTitolo("Crypto")
                .setAutore("Dan Brown")
                .setCodiceISBN("9788804675051")
                .setValutazione(3)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();
        libreria.aggiungiLibro(libro3);
        assertEquals(3, libreria.getLibreria().size());
        assertTrue(libreria.getLibreria().contains(libro3));
    }


    @Test
    public void testRimozioneLibro() {
        libreria.rimuoviLibro(libro1);
        assertEquals(1, libreria.getLibreria().size());
        assertFalse(libreria.getLibreria().contains(libro1));
    }



}
