package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ThreadServer extends Thread {

    Socket s;
    ArrayList<String> sequenceServer = new ArrayList<>();
    ArrayList<String> sequenceClient = new ArrayList<>();

    public ThreadServer(Socket c) {
        s = c;
    }

    public void sequenceGenerator() {
        Random random = new Random();                           // Géneration d'un
        int sequenceInt = random.nextInt(4);             // nombre aleatoire

        switch (sequenceInt) {                           // Affectation du nombre aléatoire
            case 0:                                     // à une couleur et puis ajoute cette
                sequenceServer.add("rouge");            // couleur à la séquence que le joueur
                break;                                  // doit reproduire (l'ArrayList "sequenceServeur")
            case 1:
                sequenceServer.add("bleu");
                break;
            case 2:
                sequenceServer.add("jaune");
                break;
            case 3:
                sequenceServer.add("vert");
                break;

        }
    }

    public void run(){
        try {
            do {

                sequenceGenerator();
                OutputStream out = s.getOutputStream();
                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(sequenceServer);
                System.out.println(sequenceServer);
                oout.reset();

                OutputStreamWriter outWriter = new OutputStreamWriter(out);
                PrintWriter writer = new PrintWriter(outWriter);

                InputStream in = s.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);
                sequenceClient = (ArrayList<String>) oin.readObject();

                System.out.println(sequenceClient.toString());

                while (true) {
                    if ((sequenceClient).equals(sequenceServer)) {
                        writer.println("correct");
                        writer.flush();
                        sequenceGenerator();
                        oout.writeObject(sequenceServer);
                        oout.reset();

                    } else {
                        writer.println("false");
                        writer.flush();
                    }
                }
            } while(true);
        } catch (EOFException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}