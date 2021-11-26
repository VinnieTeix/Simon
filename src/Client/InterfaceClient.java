package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InterfaceClient extends JFrame implements Serializable{

    public ArrayList<String> sequence = new ArrayList<>();              // Sequence d'entrée par le joueur
    public ArrayList<String> sequenceServ = new ArrayList<>();    // Sequence à effectuer envoyé par le serveur

    public InetAddress addr = InetAddress.getLocalHost();    // Déclarer l'adresse et le socket ici permet d'executer les méthode d'envoie en
    public Socket client = new Socket(addr, 10000);       // OutputStream directement dans la classe ActionListener
    OutputStream out = client.getOutputStream();
    ObjectOutputStream oout = new ObjectOutputStream(out);

    public InterfaceClient() throws IOException {
        setTitle("SimonClient");


        //JPanel 1
        JPanel jpl1 = new JPanel();
        jpl1.setSize(500, 500);
        jpl1.setLayout(new GridLayout(2, 2));


        JButton rouge = new JButton("Rouge");
        rouge.setBackground(Color.RED);
        rouge.addActionListener(new ActionL());

        JButton bleu = new JButton("Bleu");
        bleu.setBackground(Color.BLUE);
        bleu.addActionListener(new ActionL());

        JButton jaune = new JButton("Jaune");
        jaune.setBackground(Color.YELLOW);
        jaune.addActionListener(new ActionL());

        JButton vert = new JButton("Vert");
        vert.setBackground(Color.GREEN);
        vert.addActionListener(new ActionL());

        jpl1.add(rouge);
        jpl1.add(bleu);
        jpl1.add(jaune);
        jpl1.add(vert);


        //JPanel 0
        JPanel jpl0 = new JPanel();
        jpl0.setLayout(new BorderLayout());
        jpl0.add(jpl1);

        JButton enter = new JButton("Start/Restart");
        enter.addActionListener(new ActionL());

        JLabel userInput = new JLabel();

        jpl0.add(enter, BorderLayout.SOUTH);
        jpl0.add(userInput, BorderLayout.NORTH);

        add(jpl0);
        setSize(550, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Client();
    }

    public class ActionL implements ActionListener {            //ActionListener pour chaque bouton
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

            JButton a = (JButton) e.getSource();
            if (a.getText().equals("Rouge")) {
                sequence.add("rouge");
                oout.writeObject(sequence);
                oout.flush();
            } else if (a.getText().equals("Bleu")) {
                sequence.add("bleu");
                oout.writeObject(sequence);
                oout.flush();
            } else if (a.getText().equals("Jaune")) {
                sequence.add("jaune");
                oout.writeObject(sequence);
                oout.flush();
            } else if (a.getText().equals("Vert")) {
                sequence.add("vert");
                oout.writeObject(sequence);
                oout.flush();
            } else if (a.getText().equals("Start/Restart")) {
                sequence.clear();
                try {
                    oout.writeObject(empty);
                    oout.flush();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                }
            } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            System.out.println("Client sends: " + sequence.toString());
            }


        }


    ArrayList<String> empty = new ArrayList<String>();


    @SuppressWarnings("SpellCheckingInspection")
    public class Client {
        public Client() {
            try {

                do {
                    InputStream in = client.getInputStream();
                    ObjectInputStream oin = new ObjectInputStream(in);
                    sequenceServ = (ArrayList<String>) oin.readObject();
                    System.out.println("Server sends: " + sequenceServ.toString());

                    if((sequenceServ.size()) == (sequence.size())){             // Compare la taille des sequences pour ensuite:
                        if(sequenceServ.equals(empty)){                         // Si l'ArrayList envoyé par le serveur est vide càd le serveur a déterminé que le jouer a eu faux et donc s'est réinitialisé, la séquence du joueur se réinitialise aussi
                            System.out.println("Incorrect, restarting...");
                            sequence.clear();
                        }
                        else {
                            System.out.println("Correct.");
                            sequence.clear();
                        }
                    }

                } while (true);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public class JLabelThread extends Thread{
        public JLabelThread(ArrayList seq, JLabel jlb){
            while (true) {
                jlb.setText(seq.toString());
            }
        }
    }
}