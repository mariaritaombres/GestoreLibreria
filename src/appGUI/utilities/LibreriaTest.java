package appGUI.utilities;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;

import java.util.List;

public class LibreriaTest {
    private final GestioneLibreria g;

    private List<Libro> libri;

   public LibreriaTest(){

         g= new GestioneLibreria();

        Libro libro1=new Libro.Builder()
                .setTitolo("Il tredicesimo passeggero")
                .setAutore("Ghiannis Marìs")
                .setCodiceISBN("9788807898624")
                .setValutazione(4)
                .setGenere("Giallo")
                .setStato(Stato.letto)
                .build();

        Libro libro2=new Libro.Builder()
                .setTitolo("Il mastino dei Baskerville")
                .setAutore("Arthur Conan Doyle")
                .setCodiceISBN("9788807897453")
                .setValutazione(0)
                .setGenere("Giallo")
                .setStato(Stato.inLettura)
                .build();


        Libro libro3=new Libro.Builder()
                .setTitolo("Fu sera e fu mattina")
                .setAutore("Ken Follet")
                .setCodiceISBN("9788804752370")
                .setValutazione(3)
                .setGenere("Storico")
                .setStato(Stato.letto)
                .build();


        g.aggiungiLibro(libro1);
        g.aggiungiLibro(libro2);
        g.aggiungiLibro(libro3);

   }
    public List<Libro> getLibreria() {
        return g.getLibreria();
    }

}
