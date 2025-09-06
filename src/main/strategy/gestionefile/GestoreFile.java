package main.strategy.gestionefile;

import main.builder.Libro;

import java.io.IOException;
import java.util.List;

public interface GestoreFile {

    void salva(List<Libro> libri, String pathFile) throws IOException;

    void carica(String pathFile) throws IOException;

}
