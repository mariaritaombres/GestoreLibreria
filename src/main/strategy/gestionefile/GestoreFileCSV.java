package main.strategy.gestionefile;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Classe che tramite pattern Strategy, gestisce la persistenza
della libreria dato un file in formato CSV.
 */


public class GestoreFileCSV implements GestoreFile {
    private GestioneLibreria libreria;

    public GestoreFileCSV(GestioneLibreria libreria) {
        this.libreria = libreria;
    }


    /*
    Salvataggio libreria su file CSV.
     */
    @Override
    public void salva(List<Libro> libri, String pathFile) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile))) {
            bw.write("Titolo,Autore,CodiceISBN,Genere,Valutazione,Stato di Lettura");
            bw.newLine();

            for (Libro libro : libri) {
                bw.write(
                        formattaCampo(libro.getTitolo()) + "," +
                                formattaCampo(libro.getAutore()) + "," +
                                formattaCampo(libro.getCodiceISBN()) + "," +
                                formattaCampo(libro.getGenere()) + "," +
                                libro.getValutazione() + "," +
                                formattaCampo(libro.getStato().name())
                );
                bw.newLine();
            }
            System.out.println("File CSV salvato correttamente nel percorso: " + pathFile);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio CSV: " + e.getMessage());
            throw e;
        }
    }



     /*
     Metodo per caricare, dato un file CSV, una libreria giá esistente.
     Sapendo che un Libro é composto dai parametri nel seguente ordine:titolo, autore, codiceISBN, genere,
      valutazione, stato di lettura.
      */
     @Override
     public void carica(String pathFile) throws IOException {
         List<Libro> libri = new ArrayList<>();
         try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
             String riga = br.readLine(); // salta header
             while ((riga = br.readLine()) != null) {
                 String[] campi = riga.split(",");
                 if (campi.length >= 6) {
                     String titolo = campi[0].trim();
                     String autore = campi[1].trim();
                     String codiceISBN = campi[5].trim();
                     String genere = campi[2].trim();

                     int valutazione = 0;
                     try {
                         valutazione = Integer.parseInt(campi[3].trim());
                     } catch (NumberFormatException e) {
                         System.err.println("Valutazione non valida: " + campi[4] + ", imposto 0");
                     }

                     Stato stato = Stato.daLeggere; // valore di default
                     try {
                         stato = Stato.valueOf(campi[4].trim());
                     } catch (IllegalArgumentException e) {
                         System.err.println("formato stato di lettura del libro non valido"+ Stato.valueOf(campi[5].trim()));

                     }

                     libri.add(new Libro.Builder()
                             .setTitolo(titolo)
                             .setAutore(autore)
                             .setCodiceISBN(codiceISBN)
                             .setGenere(genere)
                             .setValutazione(valutazione)
                             .setStato(stato)
                             .build());
                 }
             }
             libreria.addAll(libri);
             libreria.notifica();
         } catch (IOException e) {
             System.err.println("Errore durante il caricamento CSV: " + e.getMessage());
             throw e;
         }
     }





        //metodo per rendere CSV valido
        private String formattaCampo(String campo) {
            if (campo == null) return "";
            String risultato = campo.replace("\"", "\"\"");
            if (risultato.contains(",") || risultato.contains("\"") || risultato.contains("\n")) {
                risultato = "\"" + risultato + "\"";
            }
            return risultato;
        }





}
