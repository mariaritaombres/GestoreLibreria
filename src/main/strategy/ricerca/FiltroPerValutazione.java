package main.strategy.ricerca;

import main.builder.Libro;
import main.strategy.confronti.valutazioni.CriterioDiConfrontoPerValutazione;

import java.util.ArrayList;
import java.util.List;

public class FiltroPerValutazione extends RicercaFiltro{
    private int valutazione;
    private CriterioDiConfrontoPerValutazione criterio;

    public FiltroPerValutazione(int valutazione, CriterioDiConfrontoPerValutazione criterio) {
        this.valutazione = valutazione;
        this.criterio = criterio;
    }

    @Override
    protected List<Libro> applicaFiltro(List<Libro> libri) {
        List<Libro> ret= new ArrayList<>();
        for(Libro l:libri){
            if(criterio.confronta(l.getValutazione(), valutazione)){
                ret.add(l);
            }
        }
        return ret;
    }
}
