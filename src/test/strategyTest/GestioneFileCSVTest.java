package test.strategyTest;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import main.strategy.gestionefile.GestoreFileCSV;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestioneFileCSVTest {


    private GestoreFileCSV gestore;
    private Path fileTemp;
    private List<Libro> libri;
    private GestioneLibreria libreria;


    @BeforeEach
    public void setup() throws IOException {
        libreria=new GestioneLibreria();
        gestore= new GestoreFileCSV(libreria);
        fileTemp= Files.createTempFile("libriperTest",".csv");

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
                .setValutazione(0)
                .setCodiceISBN("9788834610572")
                .setGenere("Giallo")
                .setStato(Stato.daLeggere)
                .build();
        libri= Arrays.asList(libro1,libro2);

    }

    @AfterEach
    public void termina() throws IOException{
        Files.deleteIfExists(fileTemp);
    }


    @Test
    public void salvaTest() throws IOException{
        gestore.salva(libri, fileTemp.toString());
        List<String> righe= Files.readAllLines(fileTemp);
        assertEquals(3,righe.size());
        assertTrue(righe.get(1).contains("Dune,Frank Herbert,9788834739679,Fantascienza,4,letto"));

    }


    @Test
    public void caricaTest() throws IOException {
        // Contenuto CSV di prova
        List<String> contenuto = Arrays.asList(
                "Titolo,Autore,ISBN,Genere,Valutazione,Stato",
                "Dune,Frank Herbert,9788834739679,Fantascienza,4,letto",
                "Il caso Alaska Sanders,Joel Dicker,9788834610572,Giallo,0,daLeggere"
        );
        Files.write(fileTemp, contenuto);

        // Esegui il caricamento sul gestore
        gestore.carica(fileTemp.toString());

        // Ottieni la lista aggiornata dalla libreria
        List<Libro> caricati = libreria.getLibreria();

        // Controlli
        assertEquals(2, caricati.size(), "Numero di libri caricati corretto");
        assertEquals("Dune", caricati.get(0).getTitolo(), "Titolo primo libro corretto");
        assertEquals("Il caso Alaska Sanders", caricati.get(1).getTitolo(), "Titolo secondo libro corretto");

        // Eventualmente puoi testare anche altri campi
        assertEquals("Frank Herbert", caricati.get(0).getAutore());
        assertEquals(4, caricati.get(0).getValutazione());
        assertEquals(Stato.letto, caricati.get(0).getStato());
    }







}
