package appGUI.utilities;

import main.builder.Libro;
import main.observer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TabellaLibri extends JPanel implements Observer {
    private LibreriaTest libreria;
    private JTable tabella;
    private DefaultTableModel modello;

    public TabellaLibri(LibreriaTest libreria){
        this.libreria = libreria;

        modello = new DefaultTableModel(
                new Object[]{"Titolo","Autore","Genere","Valutazione","Stato lettura","ISBN"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabella = new JTable(modello);
        setLayout(new BorderLayout());
        add(new JScrollPane(tabella), BorderLayout.CENTER);

        aggiornaTabella();
    }


    public JTable getTabella(){
        return tabella;
    }

    public void aggiornaTabella() {
        modello.setRowCount(0);
        for (Libro l : libreria.getLibreria()) {
            modello.addRow(new Object[]{
                    l.getTitolo(),
                    l.getAutore(),
                    l.getGenere(),
                    l.getValutazione(),
                    l.getStato(),
                    l.getCodiceISBN()
            });
        }
    }


    @Override
    public void update() {
        aggiornaTabella();
    }
}
