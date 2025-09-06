package main.strategy.ricerca;

import main.builder.Libro;
import main.strategy.confronti.CriterioDiConfronto;

import java.util.ArrayList;
import java.util.List;

public class FiltroPerGenere extends RicercaFiltro{
    private String genere;
    private CriterioDiConfronto criterio;

    public FiltroPerGenere(String genere, CriterioDiConfronto criterio) {
        this.genere = genere;
        this.criterio = criterio;
    }

    @Override
    protected List<Libro> applicaFiltro(List<Libro> libri) {
        List<Libro> ret= new ArrayList<>();
        for(Libro l:libri){
            if(criterio.confronta(l.getGenere(), genere)){
                ret.add(l);
            }
        }
        return ret;
    }
}
