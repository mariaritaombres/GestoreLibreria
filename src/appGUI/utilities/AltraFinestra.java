package appGUI.utilities;

import main.facade.GestioneLibreria;

import javax.swing.*;
import java.awt.*;

public class AltraFinestra extends JFrame {
    JLabel label;
    MenuBar menuBar;
    LibreriaTest libreria;
    GestioneLibreria gestore;
    JButton undoButton, redoButton;
    TabellaLibri catalogo;

    public AltraFinestra(){
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(680, 680);
       this.setResizable(true);
        this.setLayout(new BorderLayout());



       label= new JLabel("ciao");
       label.setVisible(true);



       libreria= new LibreriaTest();
        catalogo= new TabellaLibri(libreria);
       gestore=new GestioneLibreria();
       gestore.addObserver(catalogo);

        menuBar= new MenuBar(catalogo);
        this.setJMenuBar(menuBar);

        JScrollPane scrollPane = new JScrollPane(catalogo);
        scrollPane.setPreferredSize(new Dimension(600, 400)); // ingrandisci tabella
        this.add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

         undoButton = new JButton("Undo");
         redoButton = new JButton("Redo");

        undoButton.addActionListener(e -> {
            gestore.annullaUltimaOperazione();
            gestore.notifica();
            updateButtons();
        });

        redoButton.addActionListener(e -> {
            gestore.ripetiUltimaOperazione();
            gestore.notifica();
            updateButtons();
        });

        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        this.add(buttonPanel, BorderLayout.SOUTH);


       this.setVisible(true);
    }

    void updateButtons() {
        undoButton.setEnabled(gestore.canUndo());
        redoButton.setEnabled(gestore.canRedo());
    }


}
