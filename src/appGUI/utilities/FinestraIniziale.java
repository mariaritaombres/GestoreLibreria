package appGUI.utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraIniziale extends JFrame implements ActionListener {
    JLabel label;
    JButton button;
    JButton button2;

   public FinestraIniziale() {
       this.setTitle("Gestore Libreria");
       this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       this.setSize(400, 450);
       this.setResizable(false);

       SfondoPanel background = new SfondoPanel("libreria.jpg");
       this.setContentPane(background);
       background.setLayout(new BorderLayout());

       JPanel pannelloBottoni = new JPanel();
       pannelloBottoni.setOpaque(false);
       pannelloBottoni.setLayout(new BoxLayout(pannelloBottoni, BoxLayout.Y_AXIS));

       label = new JLabel("Benvenuto");
       label.setFont(new Font("Arial", Font.PLAIN, 28));
       label.setForeground(Color.WHITE);
       label.setAlignmentX(Component.CENTER_ALIGNMENT);
       pannelloBottoni.add(Box.createVerticalStrut(50)); // Spazio sopra
       pannelloBottoni.add(label);
       pannelloBottoni.add(Box.createVerticalStrut(30));

       button = new JButton("Esplora catalogo");
       button.setFont(new Font("Arial", Font.BOLD, 18));
       button.setMaximumSize(new Dimension(250, 50)); // 🔥 Più grande
       button.setAlignmentX(Component.CENTER_ALIGNMENT);
       button.addActionListener(this);
       pannelloBottoni.add(button);

       pannelloBottoni.add(Box.createVerticalStrut(20));

       button2 = new JButton("Esci");
       button2.setFont(new Font("Arial", Font.BOLD, 18));
       button2.setMaximumSize(new Dimension(250, 50)); // 🔥 Più grande
       button2.setAlignmentX(Component.CENTER_ALIGNMENT);
       button2.addActionListener(this);
       pannelloBottoni.add(button2);

       pannelloBottoni.add(Box.createVerticalGlue());

       background.add(pannelloBottoni, BorderLayout.CENTER);

       this.setVisible(true);
   }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            this.dispose();
            AltraFinestra a= new AltraFinestra();
            a.setVisible(true);
        }

        if(e.getSource()==button2)
        {
            ImageIcon foto=new ImageIcon("emoji.png");
            int scelta = JOptionPane.showConfirmDialog(
                    this,
                    "Vuoi davvero uscire?",
                    "Conferma uscita",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    foto
            );

            if (scelta == JOptionPane.YES_OPTION) {
                System.exit(0); // Chiude il programma
            }
            }
    }
}
