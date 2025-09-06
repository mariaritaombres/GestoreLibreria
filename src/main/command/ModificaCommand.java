package main.command;

import main.builder.Libro;
import main.facade.GestioneLibreria;

public class ModificaCommand implements Command {

    private GestioneLibreria libreria;

   private Libro libroNuovo;
   private Libro libroVecchio;

    public ModificaCommand(GestioneLibreria libreria, Libro libroNuovo, Libro libroVecchio) {
        this.libreria = libreria;
        this.libroNuovo = libroNuovo;
        this.libroVecchio = libroVecchio;
    }

    @Override
    public void execute() {
        libreria.modificaLibro(libroVecchio,libroNuovo);
    }
}
