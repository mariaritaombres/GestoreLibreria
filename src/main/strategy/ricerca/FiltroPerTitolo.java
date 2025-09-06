package main.strategy.ricerca;

import main.builder.Libro;
import main.strategy.confronti.CriterioDiConfronto;

import java.util.ArrayList;
import java.util.List;

public class FiltroPerTitolo extends RicercaFiltro {
    private String titolo;
    private CriterioDiConfronto criterio;

    public FiltroPerTitolo(String titolo, CriterioDiConfronto criterio) {
        this.titolo = titolo;
        this.criterio = criterio;
    }

    @Override
    protected List<Libro> applicaFiltro(List<Libro> libri) {
        List<Libro> ret = new ArrayList<>();
        for (Libro l : libri) {
            if (criterio.confronta(l.getTitolo(), titolo)) {
                ret.add(l);
            }
        }
        return ret;
    }
}

