package main.builder;

import main.observer.Observer;
import main.observer.Subject;
import main.strategy.ordinamento.Ordinamento;
import main.strategy.ricerca.RicercaFiltro;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/* Utilizzo pattern Singleton per gestire la libreria
* e implemento observer/Subject per notificare in caso di modifiche.
* Per salvare lo stato corrente della libreria utilizzo il pattern Memento
* gestendo i casi di operazioni undo/redo.
* */


public class Libreria implements Subject {
    private static Libreria istanza;
    private final List<Libro> libri= new ArrayList<>();
    private  final List<Observer> osservatori= new ArrayList<>();

    private Libreria(){}

    //utilizzo synchronized per controllare accesso da parte di piú thread
    public static synchronized Libreria getInstance(){
        if(istanza==null){
            istanza= new Libreria();
        }
        return istanza;
    }

    public List<Libro> getLibri(){
        return (new ArrayList<>(libri));
    }

    public void setLibri(List<Libro> nuovi){
        libri.clear();
        libri.addAll(nuovi);
        notifica();
    }

    public void reset(){
        libri.clear();
    }

    public void modificaLibro(Libro libroVecchio, Libro libroNuovo) {
        salvaStato();

        for (int i = 0; i < libri.size(); i++) {
            if (libri.get(i).getCodiceISBN().equals(libroVecchio.getCodiceISBN())) {

                // Se il nuovo ISBN è diverso, controllo che non esista già
                if (!libroVecchio.getCodiceISBN().equals(libroNuovo.getCodiceISBN())) {
                    for (Libro l : libri) {
                        if (l.getCodiceISBN().equals(libroNuovo.getCodiceISBN())) {
                            throw new IllegalArgumentException(
                                    "ISBN già presente in un altro libro: " + libroNuovo.getCodiceISBN()
                            );
                        }
                    }
                }

                libri.set(i, libroNuovo);

                notifica();
                return;
            }
        }

        throw new IllegalArgumentException(
                "Libro da modificare non trovato: " + libroVecchio.getCodiceISBN()
        );
    }







    /*
    Metodi d'ausilio pattern Observer
     */


    @Override
    public void aggiungiOss(Observer o) {
       if(!osservatori.contains(o)) osservatori.add(o);
    }

    @Override
    public void rimuoviOss(Observer o) {
        osservatori.remove(o);
    }

    @Override
    public void notifica() {
        for(Observer ob:osservatori){
            ob.update();
        }
    }

    public void addAllLibri(List<Libro> libreria) {
        libri.addAll(libreria);
    }

    /*
    inner static class per salvare stato corrente della libreria
    tramite pattern Memento
     */
    private static class MementoLibreria{
        private final List<Libro> statoCorrente;

        private MementoLibreria(List<Libro> statoCorrente) {
            this.statoCorrente = new ArrayList<>(statoCorrente);
        }

        private List<Libro> getStatoCorrente(){
            return new ArrayList<>(statoCorrente);
        }

    }


    /*
    Metodi ausiliari per il pattern memento
     */

    private Stack<MementoLibreria> undo= new Stack<>();
    private Stack<MementoLibreria> redo= new Stack<>();


    public void salvaStato() {
        MementoLibreria m= new MementoLibreria(libri);
        undo.push(m);
        redo.clear();
    }

    public void annulla(){
        if(!undo.isEmpty()){
            MementoLibreria m= undo.pop();
            redo.push(new MementoLibreria(libri));
            libri.clear();
            libri.addAll(m.getStatoCorrente());
            notifica();
        }else{
        System.out.println("nessun elemento da annullare");}

    }

    public void rifai(){
        if(!redo.isEmpty()){
            MementoLibreria m= redo.pop();
            undo.push(new MementoLibreria(libri));
            libri.clear();
            libri.addAll(m.getStatoCorrente());
            notifica();
        }else{
            System.out.println("nessun elemento da rifare");
        }
    }

    public boolean canUndo() {
        return !undo.isEmpty();
    }

    public boolean canRedo() {
        return !redo.isEmpty();
    }


    public void addLibro(Libro l){
        // Controlla se esiste già un libro con lo stesso ISBN
        for(Libro book : libri){
            if(book.getCodiceISBN().equals(l.getCodiceISBN())) {
                System.out.println("Libro già presente: " + l.getCodiceISBN());
                return;}}
        salvaStato();
        libri.add(l);
        notifica();
    }


    public void removeLibro(Libro l){
        salvaStato();
        libri.remove(l);
        notifica();
    }

    /*
    Metodo di rimozione tramite codice ISBN
     */


    public void removeLibro(String codiceISBN){
        Libro daRimuovere=null;
        for(Libro l:libri){
            if(l.getCodiceISBN().equals(codiceISBN)){
                daRimuovere=l;
                break;
            }
        }

        if(daRimuovere==null){
            throw new IllegalArgumentException("Libro non trovato con codice ISBN:"+codiceISBN);
        }
        salvaStato();
        libri.remove(daRimuovere);
        notifica();
    }

    /*
    Metodi di ordinamento ausiliari al pattern strategy per l'ordinamento dei libri
     */

    private Ordinamento ordine;

    public void setOrdine(Ordinamento ordine){
        this.ordine=ordine;
    }


    public void ordinaLibri(){
        if(ordine!=null){
            ordine.ordina(libri);
            notifica();
        }
    }

      /*
    Metodi di ordinamento ausiliari al pattern strategy per la ricerca di libri tramite filtro
     */

    private RicercaFiltro filtro;

    public void setFiltro(RicercaFiltro filtro){
        this.filtro=filtro;
    }

    public void filtraLibri(){
        if(filtro!=null){
            List<Libro> filtrati=filtro.filtra(libri);
            libri.clear();
            libri.addAll(filtrati);
            notifica();
        }
    }




}
