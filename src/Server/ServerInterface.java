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

public class ServerInterface extends JFrame implements Serializable {

public ServerInterface() {


    setTitle("Server");
    JPanel jpl1 = new JPanel();
    JButton kill = new JButton("Kill server");
    kill.addActionListener(new ActionS());
    jpl1.add(kill);
    add(jpl1);

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    new Server();
}

public class ActionS implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(1);
    }
}

public class Server {
    public Server() {
        ServerSocket svs;
        try {
            svs = new ServerSocket(10000);
            System.out.println("Waiting for client connection...");

            while (true) {
                Socket s = svs.accept();
                System.out.println("Client connected.");

                ThreadServer t1 = new ThreadServer(s);
                t1.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
}