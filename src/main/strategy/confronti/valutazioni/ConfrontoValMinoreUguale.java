package main.strategy.confronti.valutazioni;

public class ConfrontoValMinoreUguale implements CriterioDiConfrontoPerValutazione{
    @Override
    public boolean confronta(int recensioneLibro, int risultatoRicerca) {
        if(recensioneLibro<0 && risultatoRicerca<0) throw new IllegalArgumentException("la recensione non puó essere minore di zero");
        if(recensioneLibro==0 && risultatoRicerca==0) throw new IllegalArgumentException("la recensione non puó essere uguale a zero");
        return recensioneLibro<=risultatoRicerca;
    }
}
