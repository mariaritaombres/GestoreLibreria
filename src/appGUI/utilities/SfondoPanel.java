package appGUI.utilities;

import javax.swing.*;
import java.awt.*;

public class SfondoPanel extends JPanel {
    private Image bg;

    public SfondoPanel(String nomeFile){
        java.net.URL imgURL = getClass().getResource("/appGUI/resources/" + nomeFile);
        if (imgURL != null) {
            bg = new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Immagine non trovata: " + nomeFile);
        }
        this.setLayout(new BorderLayout());

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }





}
