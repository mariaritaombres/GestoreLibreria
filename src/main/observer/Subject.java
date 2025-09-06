package main.observer;

public interface Subject {
    void aggiungiOss(Observer l);
    void rimuoviOss(Observer l);
     void notifica();
}
