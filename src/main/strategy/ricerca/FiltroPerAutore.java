package main.strategy.ricerca;

import main.builder.Libro;
import main.strategy.confronti.CriterioDiConfronto;

import java.util.ArrayList;
import java.util.List;

public class FiltroPerAutore extends RicercaFiltro{
    private String autore;
    private CriterioDiConfronto criterio;

    public FiltroPerAutore(String autore, CriterioDiConfronto criterio) {
        this.autore = autore;
        this.criterio = criterio;
    }

    @Override
    protected List<Libro> applicaFiltro(List<Libro> libri) {
        List<Libro> ret= new ArrayList<>();
        for(Libro l:libri){
            if(criterio.confronta(l.getAutore(), autore)){
                ret.add(l);
            }
        }
        return ret;
    }
}
