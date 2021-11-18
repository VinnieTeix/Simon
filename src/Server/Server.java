package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public Server() {

        Random random = new Random();
        int sequence = random.nextInt(4-1 + 1) + 1;

        try {
            ServerSocket svs = new ServerSocket(10000);
            System.out.println("serveur lance sur le port...");
            Socket s = svs.accept();
            System.out.println("serveur connecte au client...");
            String input;

            do{
                InputStream in = s.getInputStream();
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader buffReader = new BufferedReader(inReader);
                input = buffReader.readLine();
                System.out.println(input);

                OutputStream out = s.getOutputStream();
                OutputStreamWriter outWriter = new OutputStreamWriter(out);
                PrintWriter writer = new PrintWriter(outWriter);
                writer.println(input);
                writer.flush();


            } while (true);

        } catch (IOException e) {
            System.out.println("Port used");
        }


    }
}
