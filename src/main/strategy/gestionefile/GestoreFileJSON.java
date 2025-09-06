package main.strategy.gestionefile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.builder.Libro;
import main.facade.GestioneLibreria;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*
Classe che tramite pattern Strategy, gestisce la persistenza
della libreria dato un file in formato JSON.
 */
public class GestoreFileJSON implements GestoreFile {

    private static final Gson gson= new Gson();

    //utilizzo Type per conservare il tipo di Lista della Libreria, ArrayList.
    private final Type listType = new TypeToken<ArrayList<Libro>>(){}.getType();

    private GestioneLibreria libreria;

    public GestoreFileJSON(GestioneLibreria libreria) {
        this.libreria = libreria;
    }

    @Override
    public void salva(List<Libro> libri, String pathFile) throws IOException {
        try(BufferedWriter bw= new BufferedWriter(new FileWriter(pathFile))) {
            gson.toJson(libri, bw);
            System.out.println("File salvato correttamente nel percorso:"+ pathFile);
        }catch(IOException e){
            System.err.println("errore durante il salvataggio"+ e.getMessage());
            throw e;
        }

    }


    /*
    Metodo per caricare dato un file JSON libreria giá esistente.
     */

    @Override
    public void carica(String pathFile) throws IOException {
        File f = new File(pathFile);
        if (!f.exists()) {
            throw new IOException("File non esistente al percorso: " + pathFile);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            // Legge la lista dal JSON
            List<Libro> libri = gson.fromJson(br, listType);

            // Aggiorna la libreria interna
            libreria.addAll(libri);

            // Notifica gli osservatori (es. tabella) per aggiornare la visualizzazione
            libreria.notifica();

        } catch (IOException e) {
            System.err.println("Errore durante il caricamento JSON: " + e.getMessage());
            throw e;
        }
    }
}
