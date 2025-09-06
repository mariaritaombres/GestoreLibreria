package main.builder;

import java.util.Objects;

public class Libro {
    private String titolo;
    private String autore;
    private String codiceISBN;
    private String genere;
    private int valutazione;
    private Stato stato;



    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getCodiceISBN() {
        return codiceISBN;
    }


    public String getGenere() {
        return genere;
    }



    public Stato getStato() {
        return stato;
    }




    public int getValutazione() {
        return valutazione;
    }


    @Override
    public final boolean equals(Object o) {
        if(o==null) return false;
        if(o==this) return true;
        if (!(o instanceof Libro libro)) return false;

        Libro l= (Libro)o;


        if(!this.titolo.equals(l.titolo)) return false;
        if(!this.autore.equals(l.autore)) return false;
        if(!this.codiceISBN.equals(l.codiceISBN)) return false;
        if(!this.genere.equals(l.genere)) return false;
        if(!this.stato.equals(l.stato)) return false;
        if(this.valutazione!=l.valutazione) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codiceISBN);
    }

public static class Builder{
    private String titolo;
    private String autore;
    private String codiceISBN;
    private String genere;
   /*
   parametri non necessari per la costruzione dell'oggetto Libro:li pongo di default, nel caso
   in cui non fosse specificati
    */

    private int valutazione=0;
    private Stato stato= Stato.daLeggere;

    public Builder setTitolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public Builder setAutore(String autore) {
        this.autore = autore;
        return this;
    }

    public Builder setGenere(String genere) {
        this.genere = genere;
        return this;
    }

    public Builder setValutazione(int v){
        this.valutazione=v;
        return this;
    }

    public Builder setStato(Stato stato) {
        this.stato = stato;
        return this;
    }

    public Builder setCodiceISBN(String codiceISBN) {
        this.codiceISBN = codiceISBN;
        return this;
    }

    public  String titoloValido(){
        if(this.titolo==null ||this.titolo.trim().isEmpty() )
            throw new RuntimeException("Titolo non può essere vuoto");
        return new String(this.titolo.trim());
    }

    public  String autoreValido(){
        if( this.autore==null|| this.autore.trim().isEmpty())
            throw new RuntimeException("Autore non può essere vuoto");
        return new String(this.autore.trim());
    }

    public  void codiceISBNValido(){
        if( this.codiceISBN==null||this.codiceISBN.trim().isEmpty())
            throw new RuntimeException("ISBN non può essere nullo");
        if(this.codiceISBN.length() != 13){
            throw new RuntimeException("Codice ISBN non valido");
        }

        if (!this.codiceISBN.matches("^978\\d{10}$")) {
            throw new RuntimeException("ISBN non valido: deve contenere 13 cifre e iniziare con 978");
        }

    }

    public void genereValido(){
        if(this.genere==null)
            throw new RuntimeException("il campo genere non puó rimanere vuoto");
    }


    public  void statoValido() {
        if (this.stato == null)
            throw new RuntimeException("Lo stato del libro non può essere nullo");
    }

    public void valutazioneValida() {
        if (this.stato != Stato.letto) {
            if (this.valutazione != 0)
                throw new IllegalArgumentException("non si puó recensire il libro prima di averlo letto");
        } else {
            if (this.valutazione < 1 || this.valutazione > 5)
                throw new IllegalArgumentException("la valutazione deve partire da 1 a 5");
        }
    }


    public Libro build(){
        this.titolo=titoloValido();
       this.autore= autoreValido();
        codiceISBNValido();
        genereValido();
        statoValido();
        valutazioneValida();
    return new Libro(this);
    }


}
    private Libro(Builder builder){
    titolo = builder.titolo;
    autore = builder.autore;
    codiceISBN = builder.codiceISBN;
    genere = builder.genere;
    valutazione = builder.valutazione;
    stato = builder.stato;
    }


}
