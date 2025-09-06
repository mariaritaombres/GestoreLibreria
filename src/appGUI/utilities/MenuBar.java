package appGUI.utilities;

import main.facade.GestioneLibreria;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    JMenu fileMenu,caricaMenu,modMenu,ordMenu,filtraMenu;

    JMenu salvaMenu;
    JMenuItem salvaJSONItem,salvaCSVItem;
    JMenuItem caricaCSVItem,caricaJSONItem;
    JMenuItem exitItem,modItem,addItem,remItem;

    JMenu ordinaMenu;
    JMenuItem titoloItem,titoloItem2;
    JMenuItem valutaItem,valutaItem2;
    JMenuItem autoreItem,autoreItem2;

    JMenu filtraCome;
    JMenuItem genereItem;
    JMenuItem statoItem;

    TabellaLibri catalogo;
    GestioneLibreria libreria;
    ActionListeners actionListeners;

    public MenuBar(TabellaLibri catalogo){

        this.catalogo=catalogo;
        libreria= new GestioneLibreria();


        fileMenu= new JMenu("File");
        modMenu= new JMenu("Modifica");
        ordMenu= new JMenu("Ordina");
        filtraMenu= new JMenu("Filtra");



        salvaMenu= new JMenu("Salva come:");
        salvaCSVItem= new JMenuItem("CSV");
        salvaJSONItem= new JMenuItem("JSON");

        caricaMenu= new JMenu("Carica da file:");
        caricaCSVItem= new JMenuItem("CSV");
        caricaJSONItem= new JMenuItem("JSON");

        exitItem= new JMenuItem("Esci");

        salvaMenu.add(salvaCSVItem);
        salvaMenu.add(salvaJSONItem);
        fileMenu.add(salvaMenu);
        fileMenu.addSeparator();

        caricaMenu.add(caricaCSVItem);
        caricaMenu.add(caricaJSONItem);
        fileMenu.add(caricaMenu);
        fileMenu.addSeparator();

        fileMenu.add(exitItem);

        modItem= new JMenuItem("Modifica libro");
        addItem= new JMenuItem("Aggiungi libro");
        remItem= new JMenuItem("Rimuovi libro");

        modMenu.add(modItem);
        modMenu.add(addItem);
        modMenu.add(remItem);


        ordinaMenu= new JMenu("Ordina per:");
        titoloItem =new JMenuItem("Titolo");
        autoreItem =new JMenuItem("Autore");
        valutaItem =new JMenuItem("Valutazione");



        ordinaMenu.add(titoloItem);
        ordinaMenu.add(autoreItem);
        ordinaMenu.add(valutaItem);
        ordMenu.add(ordinaMenu);


        filtraCome= new JMenu("Filtra per:");
        titoloItem2 =new JMenuItem("Titolo");
        autoreItem2 =new JMenuItem("Autore");
        valutaItem2 =new JMenuItem("Valutazione");
        genereItem =new JMenuItem("Genere");
        statoItem =new JMenuItem("Stato");


        filtraCome.add(titoloItem2);
        filtraCome.add(autoreItem2);
        filtraCome.add(genereItem);
        filtraCome.add(statoItem);
        filtraCome.add(valutaItem2);

        filtraMenu.add(filtraCome);


        this.add(fileMenu);
        this.add(modMenu);
        this.add(ordMenu);
        this.add(filtraMenu);

        actionListeners=new ActionListeners(libreria,catalogo,this);


    }







}
