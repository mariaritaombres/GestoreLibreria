package test;

import main.builder.Libro;
import main.builder.Stato;
import main.command.AggiungiLibroCommand;
import main.command.CommandHandler;
import main.command.ModificaCommand;
import main.command.RimuoviLibroCommand;
import main.facade.GestioneLibreria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private GestioneLibreria libreria;
    private CommandHandler invoker;

    @BeforeEach
    public void setup(){
        libreria= new GestioneLibreria();
        libreria.resetLibreria();
        invoker=new CommandHandler();
    }



    @Test
    public void testAggiungiLibroCommand(){
        Libro libro=new Libro.Builder()
                .setTitolo("Dune")
                .setAutore("Frank Herbert")
                .setCodiceISBN("9788834739679")
                .setValutazione(4)
                .setGenere("Fantascienza")
                .setStato(Stato.letto)
                .build();
        invoker.eseguiComando(new AggiungiLibroCommand(libreria,libro));


        assertEquals(1,libreria.getLibreria().size());
        assertTrue(libreria.getLibreria().contains(libro));
    }


    @Test
    public void testRimuoviLibroCommand(){
        Libro libro=new Libro.Builder()
                .setTitolo("Il caso Alaska Sanders")
                .setAutore("Joel Dicker")
                .setCodiceISBN("9788834610572")
                .setValutazione(3)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();
        invoker.eseguiComando(new AggiungiLibroCommand(libreria,libro));
        invoker.eseguiComando(new RimuoviLibroCommand(libreria,libro));


        assertEquals(0,libreria.getLibreria().size());
        assertFalse(libreria.getLibreria().contains(libro));
    }


    /*
    per questo test modifichiamo il titolo di un libro, il suo stato di lettura aggiungendone la valutazione
     */
    @Test
    public void testModificaLibroCommand(){
        // Libro originale
        Libro libro = new Libro.Builder()
                .setTitolo("Il caso Alaska Sanders")
                .setAutore("Joel Dicker")
                .setCodiceISBN("9788834610572")  // ISBN originale
                .setValutazione(3)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();

        // Libro modificato: titolo, valutazione e ISBN cambiano
        Libro libroNew = new Libro.Builder()
                .setTitolo("La verità sul caso di Harry Quebert")
                .setAutore("Joel Dicker")
                .setCodiceISBN("9788830109391")  // nuovo ISBN
                .setValutazione(4)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();

        // Aggiungo il libro originale
        invoker.eseguiComando(new AggiungiLibroCommand(libreria, libro));

        // Modifico il libro
        invoker.eseguiComando(new ModificaCommand(libreria, libroNew, libro));

        // Controllo che la modifica sia avvenuta
        Libro modificato = libreria.getLibreria().get(0);
        assertEquals(libroNew, modificato);

        // Controllo che l’ISBN sia stato aggiornato
        assertEquals("9788830109391", modificato.getCodiceISBN());
    }





}
