package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ThreadServer extends Thread implements Serializable{

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

                OutputStream out = s.getOutputStream();
                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(sequenceServer);
                oout.flush();

                System.out.println("Sever sends: " + sequenceServer);

                InputStream in = s.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);
                sequenceClient = (ArrayList<String>) oin.readObject();

                System.out.println("Client sends: " + sequenceClient.toString());

                OutputStreamWriter outWriter = new OutputStreamWriter(oout);
                PrintWriter writer = new PrintWriter(outWriter);
                if ((sequenceClient).equals(sequenceServer)) {
                    System.out.println("Correct.");
                    sequenceGenerator();
                    oout.writeObject(sequenceServer);
                    writer.println("correcte");
                    writer.flush();
                } else {
                    System.out.println("Incorrect.");
                    sequenceServer.clear();
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