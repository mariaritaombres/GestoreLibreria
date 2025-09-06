package main.strategy.confronti;

public class ConfrontoEsatto implements CriterioDiConfronto{

    @Override
    public boolean confronta(String campoLibro, String risultato) {
        if(campoLibro==null|| risultato==null){
            return false;
        }
        return campoLibro.equalsIgnoreCase(risultato);
    }
}
