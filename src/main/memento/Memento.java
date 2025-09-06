package main.memento;

import main.builder.Libro;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private final List<Libro> statoSalvato;


    public Memento(List<Libro> statoCorrente) {
        this.statoSalvato = new ArrayList<>();
        for (Libro libro : statoCorrente) {
            this.statoSalvato.add(new Libro.Builder()
                    .setTitolo(libro.getTitolo())
                    .setAutore(libro.getAutore())
                    .setCodiceISBN(libro.getCodiceISBN())
                    .setGenere(libro.getGenere())
                    .setValutazione(libro.getValutazione())
                    .setStato(libro.getStato())
                    .build());
        }
    }

    public List<Libro> getStatoSalvato() {
        List<Libro> deepcopy = new ArrayList<>();
        for (Libro libro : statoSalvato) {
            deepcopy.add(new Libro.Builder()
                    .setTitolo(libro.getTitolo())
                    .setAutore(libro.getAutore())
                    .setCodiceISBN(libro.getCodiceISBN())
                    .setGenere(libro.getGenere())
                    .setValutazione(libro.getValutazione())
                    .setStato(libro.getStato())
                    .build());
        }
        return deepcopy;
    }

}
