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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}