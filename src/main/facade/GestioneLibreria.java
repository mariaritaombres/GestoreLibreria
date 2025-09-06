package main.facade;

import main.builder.Libreria;
import main.builder.Libro;
import main.observer.Observer;
import main.strategy.ordinamento.Ordinamento;
import main.strategy.ricerca.RicercaFiltro;

import java.util.List;

/*
Utilizzo pattern Facade per semplificare le interazioni con la classe {@link Libreria}
Nascondendo i dettagli di implementazione in modo da ridurre l'accoppiamento tra il client e le
classi interne.
In questo modo il client utilizza in modo semplice e sicuro la libreria, senza doversi preoccupare
dell'ordine di esecuzione o della gestione degli stati.

 */



public class GestioneLibreria {
    private final Libreria libreria;

    public GestioneLibreria() {
        this.libreria = Libreria.getInstance();
    }


    public void aggiungiLibro(Libro nuovoLibro) {
        libreria.addLibro(nuovoLibro);
    }
    public void modificaLibro(Libro l,Libro nuovo) {
        libreria.modificaLibro(l,nuovo);
    }

    public void rimuoviLibro(Libro l){
        libreria.removeLibro(l);
    }


    public List<Libro> getLibreria(){
        return libreria.getLibri();
    }

    public void addObserver(Observer o){
        libreria.aggiungiOss(o);
    }

    public void removeObserver(Observer o){
        libreria.rimuoviOss(o);
    }

    public void notifica(){
        libreria.notifica();
    }

    public void addAll(List<Libro>libri){
        libreria.addAllLibri(libri);
    }

    public void ordinamento(Ordinamento o){
        libreria.setOrdine(o);
    }

    public void ordina(){
        libreria.ordinaLibri();
    }



    public void filtroRicerca(RicercaFiltro f){ libreria.setFiltro(f);}

    public void filtra(){libreria.filtraLibri();}


    public void annullaUltimaOperazione(){
        libreria.annulla();
    }

    public void ripetiUltimaOperazione(){
        libreria.rifai();
    }

    public boolean canUndo() {return libreria.canUndo();}
    public boolean canRedo() {return libreria.canRedo();}


    public void resetLibreria(){ libreria.reset();}

}
