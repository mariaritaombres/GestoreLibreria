package test;

import main.builder.Libreria;
import main.builder.Libro;
import main.builder.Stato;
import main.memento.Memento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
Utilizzo diretto della Libreria per gestione dello stato interno preciso
 */
public class MementoTest {
    private Libreria libreria;
    private Libro libro1;
    private Libro libro2;
    private Memento memento;

    @BeforeEach
    public void setupMemento(){
        libreria =libreria.getInstance();
        libreria.reset();
            libro1=new Libro.Builder()
                    .setTitolo("Dune")
                    .setAutore("Frank Herbert")
                    .setCodiceISBN("9788834739679")
                    .setGenere("Fantascienza")
                    .setValutazione(0)
                    .setStato(Stato.daLeggere)
                    .build();
           libreria.addLibro(libro1);
           memento= new Memento(libreria.getLibri());
    }

    @Test
    public void testStatoSalvato(){
        List<Libro> stato= memento.getStatoSalvato();
        assertEquals(1,stato.size());
        assertEquals(libro1, stato.get(0));
    }


    @Test
    public void testRipristinoStato(){
         libro2=new Libro.Builder()
                .setTitolo("La veritá sul caso Harry Quebert")
                .setAutore("Joel Dicker")
                .setCodiceISBN("9788830109391")
                .setGenere("Giallo")
                 .setValutazione(0)
                .setStato(Stato.inLettura)
                .build();
        libreria.addLibro(libro2);

        assertEquals(2,libreria.getLibri().size());
        libreria.reset();
        libreria.addAllLibri(memento.getStatoSalvato());

        List<Libro> stato= libreria.getLibri();
        assertEquals(1,stato.size());
        assertEquals(libro1,stato.get(0));


    }









}
