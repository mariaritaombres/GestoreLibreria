package main.strategy.confronti.valutazioni;


public class ConfrontoValMaggioreUguale implements CriterioDiConfrontoPerValutazione {
    @Override
    public boolean confronta(int recensioneLibro, int risultatoRicerca) {
        return recensioneLibro>=risultatoRicerca;
    }
}
