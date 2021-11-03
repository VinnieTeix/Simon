package Client;

import javax.swing.*;
import java.awt.*;

public class InterfaceClient extends JFrame{
    public InterfaceClient(){
        setTitle("SimonClient");

        JPanel jpl = new JPanel();
        jpl.setLayout(new GridLayout(2,2));

        JButton rouge = new JButton("Rouge");
        rouge.setBackground(Color.RED);

        JButton bleu = new JButton("Bleu");
        bleu.setBackground(Color.BLUE);

        JButton jaune = new JButton("Jaune");
        jaune.setBackground(Color.YELLOW);

        JButton vert = new JButton("Vert");
        vert.setBackground(Color.GREEN);

        jpl.add(rouge);
        jpl.add(bleu);
        jpl.add(jaune);
        jpl.add(vert);

        pack();

        add(jpl);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}