package hk.edu.polyu.comp3222.vfs.client;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;

import hk.edu.polyu.comp3222.vfs.Executer;
import hk.edu.polyu.comp3222.vfs.core.*;

/**
 * Created by lidawei on 02/04/2017.
 */
public class ClientOn extends Client{
    private static final String IP  = "127.0.0.1";
    private static final int PORT = 8888;

    private String account;

    public ClientOn(){
        super();
    }

    public void connect(){
        try {
            Socket socket = new Socket(IP, PORT);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputString = input.readLine();
            this.account = inputString;
            System.out.println(inputString);
        }catch (IOException exception) {
            System.out.println("Error: " + exception);
        }

    }

    @Override
    public void action(){
        while(true) {
            String cmd = Executer.clientCmd.readLine();
        }
    }
}
