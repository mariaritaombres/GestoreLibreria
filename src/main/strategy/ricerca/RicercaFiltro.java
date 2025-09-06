package main.strategy.ricerca;

import main.builder.Libro;

import java.util.List;

public abstract class RicercaFiltro {

    protected RicercaFiltro prossimo;

    public void setNextFilter(RicercaFiltro next)
    {
        this.prossimo=next;
    }

    /*
    Rendo il metodo final in modo tale che le sottoclassi
    non modifichino l'algoritmo generale
     */

    public final List<Libro> filtra(List<Libro> libri){
        List<Libro> conFiltro= applicaFiltro(libri);

        if(prossimo!=null){
            return prossimo.filtra(conFiltro);
        }

        return conFiltro;
    }


    protected abstract List<Libro> applicaFiltro(List<Libro> libri);



}
