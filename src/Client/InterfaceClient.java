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
    public ArrayList<String> sequenceServ = new ArrayList<String>();    // Sequence à effectuer envoyé par le serveur

    public InetAddress addr;    // Déclarer l'adresse et le socket ici permet d'executer les méthode d'envoie en
    public Socket client;       // OutputStream directement dans la classe ActionListener

    public InterfaceClient() {
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

        jpl0.add(enter, BorderLayout.SOUTH);


        add(jpl0);
        setSize(550, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Client();
    }

    public class ActionL implements ActionListener {            //ActionListener pour chaque bouton
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton a = (JButton) e.getSource();
            if (a.getText().equals("Rouge")) {
                sequence.add("rouge");
            } else if (a.getText().equals("Bleu")) {
                sequence.add("bleu");
            } else if (a.getText().equals("Jaune")) {
                sequence.add("jaune");
            } else if (a.getText().equals("Vert")) {
                sequence.add("vert");
            } else if (a.getText().equals("Start/Restart")) {
                sequence.clear();
                try {
                    OutputStream out = client.getOutputStream();
                    ObjectOutputStream oout = new ObjectOutputStream(out);
                    oout.writeObject(empty);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                System.out.println("Invalid input");
            }
            try {                                                           // Le client envoit la combinaison après
                OutputStream out = client.getOutputStream();                // que chaque bouton est appuyé
                ObjectOutputStream oout = new ObjectOutputStream(out);      // en temps réel
                oout.writeObject(sequence);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println(sequence.toString());

            //if (sequence.size() > sequenceServ.size()){                     // Si le joueur dépasse le nombre d'entrée
            //    sequence.clear();                                           // pour une combinaison elle sera
            //}                                                               // réinitialisé mais la reception de la sequence
                                                                                // du serveur fonctionne pas
        }
    }


    ArrayList<String> empty = new ArrayList<String>();


    @SuppressWarnings("SpellCheckingInspection")
    public class Client {
        public Client() {
            try {
                addr = InetAddress.getLocalHost();
                client = new Socket(addr, 10000);

                String svResponse;
                while (true) {
                    InputStream in = client.getInputStream();
                    ObjectInputStream oin = new ObjectInputStream(in);
                    sequenceServ = (ArrayList<String>) oin.readObject();
                    System.out.println(sequenceServ.toString());

                    InputStreamReader inReader = new InputStreamReader(in);
                    BufferedReader buffReader = new BufferedReader(inReader);
                    svResponse = buffReader.readLine();
                    System.out.println(svResponse);

                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}