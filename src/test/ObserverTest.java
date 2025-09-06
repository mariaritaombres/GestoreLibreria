package test;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {
    private GestioneLibreria libreria;
    /*
    Utilizziamo un flag per controllare che notifica() sia stato aggiornato
     */
    private boolean notificato;


    @BeforeEach
    public void setup(){
        libreria= new GestioneLibreria();
        notificato=false;
        libreria.addObserver(()->notificato=true);
        /*
        lambda expression che quando viene chiamato notifica(), notificato diventa true;
        In poche parole, crea direttamente un Observer
         */


    }

    @Test
    public void aggiungiOssTest(){
        assertFalse(notificato);
        Libro libro=new Libro.Builder()
                .setTitolo("Crypto")
                .setAutore("Dan Brown")
                .setCodiceISBN("9788804675051")
                .setGenere("Giallo")
                .setValutazione(0)
                .setStato(Stato.daLeggere)
                .build();
        libreria.aggiungiLibro(libro);
        assertTrue(notificato);
    }




    @Test
    public void rimuoviOssTest(){
        Libro libro=new Libro.Builder()
                .setTitolo("Il caso Alaska Sanders")
                .setAutore("Joel Dicker")
                .setCodiceISBN("9788834610572")
                .setValutazione(2)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();
        libreria.aggiungiLibro(libro);

        notificato=false;

        libreria.rimuoviLibro(libro);
        assertTrue(notificato);
    }







}
