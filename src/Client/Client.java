package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.*;
import java.util.*;

public class Client {
    public Client(){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("tentative de connexion");

            Socket client = new Socket(addr, 10000);


            OutputStream out = client.getOutputStream();
            OutputStreamWriter outWriter = new OutputStreamWriter(out);
            PrintWriter writer = new PrintWriter(outWriter);
            String reponse;
            while(client.isConnected()){
                Scanner sc = new Scanner(System.in);
                String str = sc.nextLine();
                writer.println(str);
                writer.flush();

                InputStream in = client.getInputStream();
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader buffReader = new BufferedReader(inReader);
                reponse = buffReader.readLine();
                System.out.println(reponse);
            }
            client.close();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
