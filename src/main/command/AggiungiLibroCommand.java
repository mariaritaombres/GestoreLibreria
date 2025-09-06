package main.command;

import main.builder.Libro;
import main.facade.GestioneLibreria;

public class AggiungiLibroCommand implements Command {
    private GestioneLibreria libreria;
    private Libro book;


    public AggiungiLibroCommand(GestioneLibreria libreria, Libro book) {
        this.libreria = libreria;
        this.book = book;
    }

    @Override
    public void execute() {
        libreria.aggiungiLibro(book);
    }
}
