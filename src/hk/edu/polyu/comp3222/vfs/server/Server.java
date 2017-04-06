package hk.edu.polyu.comp3222.vfs.server;


import hk.edu.polyu.comp3222.vfs.client.Client;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by lidawei on 03/04/2017.
 */
public class Server {
    private static final Server INSTANCE = new Server();
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private String pw;
    private String choice;
    private HashMap<String, String> accInfo;

    private Server() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public void listenSocket() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("Could not listen on port 8888");
            System.exit(-1);
        }

        try{
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }

        try{
            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),
                    true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }

        try{
            name = in.readLine();
            pw = in.readLine();
            choice = in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }

        if(choice.equals("l")){
            if(accInfo.get(name).equals(pw)){
                out.println("s");
            }
            else out.println("w");
        }
        else if(choice.equals("s")){
            accInfo.put(name,pw);
        }
        else out.println("n");


    }

}
