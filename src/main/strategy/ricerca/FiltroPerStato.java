package main.strategy.ricerca;

import main.builder.Libro;
import main.builder.Stato;
import main.strategy.confronti.CriterioDiConfronto;

import java.util.ArrayList;
import java.util.List;

public class FiltroPerStato extends RicercaFiltro{

    private Stato stato;
    private CriterioDiConfronto criterio;

    public FiltroPerStato(Stato stato, CriterioDiConfronto criterio) {
        this.stato = stato;
        this.criterio = criterio;
    }

    /*
    Converto l'enum sullo stato di lettura per effettuarne il confronto tramite
    l'interfaccia CriterioDiConfronto.
     */


    @Override
    protected List<Libro> applicaFiltro(List<Libro> libri) {
        List<Libro> ret= new ArrayList<>();
        for(Libro l:libri){
            String statoLibro= l.getStato().name();
            if(criterio.confronta(statoLibro, stato.name())){
                ret.add(l);
            }
        }
        return ret;
    }
}
