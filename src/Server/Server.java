package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements Serializable {

    ServerSocket svs;

    public Server() {
        try {
            svs = new ServerSocket(10000);
            System.out.println("Waiting for client connection...");

            while (true) {
                Socket s = svs.accept();
                System.out.println("Client connected.");

                ThreadServer t1 = new ThreadServer(s);
                t1.start();
            }


            /*
            do{

                OutputStream out = s.getOutputStream();
                ObjectOutputStream oout = new ObjectOutputStream(out);
                oout.writeObject(sequenceServer);

                OutputStreamWriter outWriter = new OutputStreamWriter(out);
                PrintWriter writer = new PrintWriter(outWriter);
                writer.println("Client connected");

                InputStream in = s.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);
                sequenceClient = (ArrayList<String>) oin.readObject();

                System.out.println(sequenceClient.toString());

                while(true) {
                    if (sequenceClient == sequenceServer) {
                        writer.println("correct");
                        writer.flush();
                        streak = ++streak;
                        sequenceGenerator();
                    } else {
                        writer.println("false");
                        writer.flush();
                    }
                }


            } while (true);
            */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}