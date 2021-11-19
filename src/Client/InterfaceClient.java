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

public class InterfaceClient extends JFrame {
    public int inputNo = 0; //compteur des nombres d'entrées dans une séquence éffectué par le jouer
    public ArrayList<String> sequence = new ArrayList<>();
    public boolean write = false;
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


        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionL());

        jpl0.add(enter, BorderLayout.SOUTH);


        add(jpl0);
        setSize(550, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Client();
    }
    public class ActionL implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            JButton a = (JButton) e.getSource();
            if (a.getText().equals("Rouge")) {
                sequence.add("rouge");
            } else if (a.getText().equals("Bleu")) {
                sequence.add("bleu");
            } else if (a.getText().equals("Jaune")) {
                sequence.add("jaune");
            } else if (a.getText().equals("Vert")) {
                sequence.add("vert");
            } else if (a.getText().equals("Enter")) {
                Send();
            } else {
                System.out.println("Invalid input");
            }

            System.out.println(sequence.toString());
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    public class Client {
        public Client() {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                System.out.println("Connecting to server...");
                Socket client = new Socket(addr, 10000);

                OutputStream out = client.getOutputStream();
                ObjectOutputStream oout = new ObjectOutputStream(out);

                String svResponse;

                InputStream in = client.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);
                ArrayList<String> sequenceServ = (ArrayList<String>) oin.readObject();
                System.out.println(sequenceServ.toString());



                client.close();


            } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                }
            }

            public void Send(){
                try {
                    System.out.println("Submitting :" + sequence.toString());
                    InetAddress addr = InetAddress.getLocalHost();
                    Socket client = new Socket(addr, 10000);

                    OutputStream out = client.getOutputStream();
                    ObjectOutputStream oout = new ObjectOutputStream(out);
                    oout.writeObject(sequence);
                    System.out.println("sent");

                    String svResponse;
                    InputStream in = client.getInputStream();
                    InputStreamReader inReader = new InputStreamReader(in);
                    ObjectInputStream oin = new ObjectInputStream(in);
                    ArrayList<String> sequenceServ = (ArrayList<String>) oin.readObject();
                    System.out.println(sequenceServ.toString());
                    BufferedReader buffReader = new BufferedReader(inReader);
                    svResponse = buffReader.readLine();
                    System.out.println(svResponse);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }