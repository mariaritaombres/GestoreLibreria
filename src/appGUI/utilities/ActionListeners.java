package appGUI.utilities;

import main.builder.Libro;
import main.builder.Stato;
import main.facade.GestioneLibreria;
import main.strategy.confronti.ConfrontoEsatto;
import main.strategy.confronti.valutazioni.ConfrontoValMaggioreUguale;
import main.strategy.confronti.valutazioni.ConfrontoValMinoreUguale;
import main.strategy.gestionefile.GestoreFileCSV;
import main.strategy.gestionefile.GestoreFileJSON;
import main.strategy.ordinamento.OrdinamentoPerAutore;
import main.strategy.ordinamento.OrdinamentoPerTitolo;
import main.strategy.ordinamento.OrdinamentoPerValutazione;
import main.strategy.ricerca.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ActionListeners {
    private GestioneLibreria libreria;
    private TabellaLibri catalogo;
    private MenuBar menuBar;
    GestoreFileCSV gestoreCSV;
    GestoreFileJSON gestoreJSON;

    private static int contSalvataggi= 1;

    public ActionListeners(GestioneLibreria libreria, TabellaLibri catalogo, MenuBar menuBar) {
        this.libreria = libreria;
        this.catalogo = catalogo;
        this.menuBar = menuBar;
        gestoreCSV= new GestoreFileCSV(libreria);
        gestoreJSON=new GestoreFileJSON(libreria);
        inizializza();
    }


    private void inizializza(){
        menuBar.salvaCSVItem.addActionListener(e->{
            //utilizzo contatore per il nome del file di salvataggio
            String pathFile = System.getProperty("user.dir") + "/libreria_" + contSalvataggi + ".csv";
            contSalvataggi++;
            try {
                gestoreCSV.salva(libreria.getLibreria(), pathFile);
                JOptionPane.showMessageDialog(
                        null,
                        "File salvato correttamente!\nPercorso: " + pathFile,
                        "Salvataggio completato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Errore durante il salvataggio: " + ex.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
            catalogo.aggiornaTabella();
        });

        menuBar.salvaJSONItem.addActionListener(e->{
            String pathFile = System.getProperty("user.dir") + "/libreria_" + contSalvataggi + ".csv";
            contSalvataggi++;
            try {
                gestoreJSON.salva(libreria.getLibreria(), pathFile);
                JOptionPane.showMessageDialog(
                        null,
                        "File salvato correttamente!\nPercorso: " + pathFile,
                        "Salvataggio completato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Errore durante il salvataggio: " + ex.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
            catalogo.aggiornaTabella();
        });

        menuBar.caricaCSVItem.addActionListener(e->{
            JFileChooser scegliFile= new JFileChooser();

            int result = scegliFile.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                String path = scegliFile.getSelectedFile().getAbsolutePath();

                try {
                    gestoreCSV.carica(path);
                    libreria.notifica();
                    catalogo.aggiornaTabella();
                    JOptionPane.showMessageDialog(null, "Caricamento completato!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore durante il caricamento: " + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        menuBar.caricaJSONItem.addActionListener(e->{
            JFileChooser scegliFile= new JFileChooser();

            int result = scegliFile.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                String path = scegliFile.getSelectedFile().getAbsolutePath();

                try {
                    gestoreJSON.carica(path);
                    libreria.notifica();
                    catalogo.aggiornaTabella();
                    JOptionPane.showMessageDialog(null, "Caricamento completato!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore durante il caricamento: " + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });





        //metodi di modifica
        menuBar.modItem.addActionListener(e -> {
            int selectedRow = catalogo.getTabella().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Seleziona un libro da modificare!");
                return;
            }

            Libro libroSelezionato = libreria.getLibreria().get(selectedRow);

            JTextField titoloField = new JTextField(libroSelezionato.getTitolo());
            JTextField autoreField = new JTextField(libroSelezionato.getAutore());
            JTextField isbnField = new JTextField(libroSelezionato.getCodiceISBN());
            JTextField genereField = new JTextField(libroSelezionato.getGenere());
            JComboBox<Stato> statoBox = new JComboBox<>(Stato.values());
            statoBox.setSelectedItem(libroSelezionato.getStato());
            JTextField valutazioneField = new JTextField(String.valueOf(libroSelezionato.getValutazione()));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Titolo:")); panel.add(titoloField);
            panel.add(new JLabel("Autore:")); panel.add(autoreField);
            panel.add(new JLabel("ISBN:")); panel.add(isbnField);
            panel.add(new JLabel("Genere:")); panel.add(genereField);
            panel.add(new JLabel("Stato:")); panel.add(statoBox);
            panel.add(new JLabel("Valutazione:")); panel.add(valutazioneField);

            int result = JOptionPane.showConfirmDialog(
                    null, panel, "Modifica libro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);



            if (result == JOptionPane.OK_OPTION) {
                try {
                    Libro.Builder builder = new Libro.Builder();

                    // mantiene vecchi attributi se non modificati
                    builder.setTitolo(titoloField.getText().trim().isEmpty() ? libroSelezionato.getTitolo() : titoloField.getText().trim());
                    builder.setAutore(autoreField.getText().trim().isEmpty() ? libroSelezionato.getAutore() : autoreField.getText().trim());
                    builder.setCodiceISBN(isbnField.getText().trim().isEmpty() ? libroSelezionato.getCodiceISBN() : isbnField.getText().trim());
                    builder.setGenere(genereField.getText().trim().isEmpty() ? libroSelezionato.getGenere() : genereField.getText().trim());
                    builder.setStato((Stato) statoBox.getSelectedItem());
                    builder.setValutazione(valutazioneField.getText().trim().isEmpty() ? libroSelezionato.getValutazione() : Integer.parseInt(valutazioneField.getText().trim()));

                    Libro nuovoLibro = builder.build();

                    libreria.modificaLibro(libroSelezionato, nuovoLibro);

                    libreria.notifica();

                    JOptionPane.showMessageDialog(null, "Libro modificato con successo!");


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null, "Errore nella modifica: " + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        menuBar.addItem.addActionListener(e -> {
            JTextField titoloField = new JTextField();
            JTextField autoreField = new JTextField();
            JTextField isbnField = new JTextField();
            JTextField genereField = new JTextField();
            JComboBox<Stato> statoBox = new JComboBox<>(Stato.values());
            statoBox.setSelectedItem(Stato.daLeggere);
            JTextField valutazioneField = new JTextField("0"); // default

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Titolo:")); panel.add(titoloField);
            panel.add(new JLabel("Autore:")); panel.add(autoreField);
            panel.add(new JLabel("ISBN:")); panel.add(isbnField);
            panel.add(new JLabel("Genere:")); panel.add(genereField);
            panel.add(new JLabel("Stato:")); panel.add(statoBox);
            panel.add(new JLabel("Valutazione:")); panel.add(valutazioneField);

            int result = JOptionPane.showConfirmDialog(
                    null, panel, "Aggiungi nuovo libro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    Libro nuovoLibro = new Libro.Builder()
                            .setTitolo(titoloField.getText().trim())
                            .setAutore(autoreField.getText().trim())
                            .setCodiceISBN(isbnField.getText().trim())
                            .setGenere(genereField.getText().trim())
                            .setStato((Stato) statoBox.getSelectedItem())
                            .setValutazione(Integer.parseInt(valutazioneField.getText().trim()))
                            .build();

                    libreria.aggiungiLibro(nuovoLibro);
                    libreria.notifica();


                    JOptionPane.showMessageDialog(null, "Libro aggiunto con successo!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null, "Errore nell'aggiunta del libro: " + ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuBar.remItem.addActionListener(e->{
            int selectedRow = catalogo.getTabella().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Seleziona una libro da rimuovere!");
                return;
            }

            Libro libroSelezionato = libreria.getLibreria().get(selectedRow);
            libreria.notifica();
            JOptionPane.showMessageDialog(null, "Libro rimosso con successo!");

            try{
                libreria.rimuoviLibro(libroSelezionato);
            }catch(Exception ec){
                JOptionPane.showMessageDialog(
                        null, "Errore nella rimozione del libro: " + ec.getMessage(),
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }


        });




        //metodi ordinamento
        menuBar.titoloItem.addActionListener(e -> {
            GestioneLibreria g= new GestioneLibreria();
            g.ordinamento(new OrdinamentoPerTitolo());
            g.ordina();
            libreria.notifica();
        });

        menuBar.autoreItem.addActionListener(e->{
            GestioneLibreria  g= new GestioneLibreria();
            g.ordinamento(new OrdinamentoPerAutore());
            g.ordina();
            libreria.notifica();
        });

        menuBar.valutaItem.addActionListener(e->{
            GestioneLibreria  g= new GestioneLibreria();
            g.ordinamento(new OrdinamentoPerValutazione());
            g.ordina();
            libreria.notifica();
        });


        //metodi filtraggio


        menuBar.titoloItem2.addActionListener(e->{
            String ricerca = JOptionPane.showInputDialog(
                    null,
                    "Inserisci il titolo da cercare:",
                    "Filtra per titolo",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (ricerca != null && !ricerca.isBlank()) {
                GestioneLibreria g = new GestioneLibreria();
                g.filtroRicerca(new FiltroPerTitolo(ricerca, new ConfrontoEsatto()));
                g.filtra();
                catalogo.aggiornaTabella();}
        });

        menuBar.autoreItem2.addActionListener(e->{
            String ricerca = JOptionPane.showInputDialog(
                    null,
                    "Inserisci il nome dell`autore da cercare:",
                    "Filtra per titolo",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (ricerca != null && !ricerca.isBlank()) {
                GestioneLibreria g = new GestioneLibreria();
                g.filtroRicerca(new FiltroPerAutore(ricerca, new ConfrontoEsatto()));
                g.filtra();
                catalogo.aggiornaTabella();}
        });

        menuBar.genereItem.addActionListener(e->{
            String ricerca = JOptionPane.showInputDialog(
                    null,
                    "Inserisci il genere da cercare:",
                    "Filtra per titolo",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (ricerca != null && !ricerca.isBlank()) {
                GestioneLibreria g = new GestioneLibreria();
                g.filtroRicerca(new FiltroPerGenere(ricerca, new ConfrontoEsatto()));
                g.filtra();
                catalogo.aggiornaTabella();}
        });

        menuBar.statoItem.addActionListener(e->{
            Stato[] opzioni = {Stato.daLeggere, Stato.inLettura, Stato.letto};

            int scelta = JOptionPane.showOptionDialog(
                    null,
                    "Seleziona lo stato da filtrare:",
                    "Filtra per stato",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opzioni,
                    opzioni[0]
            );

            if (scelta >= 0) {
                Stato stato = opzioni[scelta];
                GestioneLibreria g = new GestioneLibreria();
                g.filtroRicerca(new FiltroPerStato(stato, new ConfrontoEsatto()));
                g.filtra();
                catalogo.aggiornaTabella();}
        });

        menuBar.valutaItem2.addActionListener(e->{
            JPanel panel = new JPanel(new GridLayout(2, 1));

            JRadioButton maggioreUgualeBtn = new JRadioButton("Maggiore o uguale a", true);
            JRadioButton minoreUgualeBtn = new JRadioButton("Minore o uguale a");

            ButtonGroup group = new ButtonGroup();
            group.add(maggioreUgualeBtn);
            group.add(minoreUgualeBtn);

            JPanel radioPanel = new JPanel();
            radioPanel.add(maggioreUgualeBtn);
            radioPanel.add(minoreUgualeBtn);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));

            panel.add(radioPanel);
            panel.add(spinner);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Filtra per valutazione",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                int valore = (int) spinner.getValue();

                FiltroPerValutazione filtro;
                if (maggioreUgualeBtn.isSelected()) {
                    filtro = new FiltroPerValutazione(valore, new ConfrontoValMaggioreUguale());
                } else {
                    filtro = new FiltroPerValutazione(valore, new ConfrontoValMinoreUguale());
                }



                GestioneLibreria g = new GestioneLibreria();
                g.filtroRicerca(filtro);
                g.filtra();
                catalogo.aggiornaTabella();}
        });



        menuBar.exitItem.addActionListener(e->{
            ImageIcon foto=new ImageIcon("fotouscita.jpg");
            int scelta = JOptionPane.showConfirmDialog(
                    menuBar,
                    "Vuoi davvero uscire?",
                    "Conferma uscita",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    foto
            );

            if (scelta == JOptionPane.YES_OPTION) {
                System.exit(0); // Chiude il programma
            }

        });



    }

}
