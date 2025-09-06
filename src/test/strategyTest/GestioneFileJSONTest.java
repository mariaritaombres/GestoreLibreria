package test.strategyTest;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import main.strategy.gestionefile.GestoreFileJSON;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestioneFileJSONTest {
    private GestoreFileJSON gestore;
    private Path fileTemp;
    private List<Libro> libri;
    private GestioneLibreria libreria;
    @BeforeEach
    void setup() throws IOException {
        libreria=new GestioneLibreria();
        gestore = new GestoreFileJSON(libreria);
        fileTemp = Files.createTempFile("testLibri", ".json");

        Libro libro1 = new Libro.Builder()
                .setTitolo("Dune")
                .setAutore("Frank Herbert")
                .setCodiceISBN("9788834739679")
                .setValutazione(4)
                .setGenere("Fantascienza")
                .setStato(Stato.letto)
                .build();
        Libro libro2 = new Libro.Builder()
                .setTitolo("Il caso Alaska Sanders")
                .setAutore("Joel Dicker")
                .setValutazione(0)
                .setCodiceISBN("9788834610572")
                .setGenere("Giallo")
                .setStato(Stato.daLeggere)
                .build();
        libri = Arrays.asList(libro1, libro2);
    }





    @AfterEach
    public void termina() throws IOException{
        Files.deleteIfExists(fileTemp);
    }


    @Test
    public void salvaTest() throws IOException {
        gestore.salva(libri, fileTemp.toString());

        assertTrue(Files.exists(fileTemp));
        String contenuto = Files.readString(fileTemp);
        assertTrue(contenuto.contains("\"titolo\":\"Il caso Alaska Sanders\""));
        assertTrue(contenuto.contains("\"autore\":\"Joel Dicker\""));
    }


    @Test
    public void caricaTest() throws IOException {
            String json = "[\n" +
                    "{\"titolo\":\"Dune\",\"autore\":\"Frank Herbert\",\"codiceISBN\":\"978834739679\",\"genere\":\"Fantascienza\",\"valutazione\":4,\"stato\":\"letto\"},\n" +
                    "{\"titolo\":\"Il caso Alaska Sanders\",\"autore\":\"Joel Dicker\",\"codiceISBN\":\"9788834610572\",\"genere\":\"Giallo\",\"valutazione\":0,\"stato\":\"daLeggere\"}\n" +
                    "]";
            Files.writeString(fileTemp, json);

            // Esegui il caricamento sul gestore
            gestore.carica(fileTemp.toString());

            // Ottieni la lista aggiornata dalla libreria
            List<Libro> caricati = libreria.getLibreria();

            // Controlli
            assertEquals(2, caricati.size(), "Numero di libri caricati corretto");
            assertEquals("Dune", caricati.get(0).getTitolo(), "Titolo primo libro corretto");
            assertEquals("Il caso Alaska Sanders", caricati.get(1).getTitolo(), "Titolo secondo libro corretto");

        }





}
