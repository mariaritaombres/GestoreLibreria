package main.command;

import main.builder.Libro;
import main.facade.GestioneLibreria;

public class RimuoviLibroCommand implements Command{

    private GestioneLibreria libreria;
    private Libro book;

    public RimuoviLibroCommand(GestioneLibreria libreria, Libro book) {
        this.libreria = libreria;
        this.book = book;
    }

    @Override
    public void execute() {
        libreria.rimuoviLibro(book);
    }
}
