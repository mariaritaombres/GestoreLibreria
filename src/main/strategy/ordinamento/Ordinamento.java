package main.strategy.ordinamento;

import main.builder.Libro;

import java.util.List;

public interface Ordinamento {

    void ordina(List<Libro> libreria);
}
