package main.strategy.ordinamento;

import main.builder.Libro;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerValutazione implements Ordinamento{
    @Override
    public void ordina(List<Libro> libreria) {
        Collections.sort(libreria, Comparator.comparingInt(Libro::getValutazione).reversed());
    }
}
