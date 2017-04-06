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
    private String account;

    public ClientOn(){
        super();
    }

    public void connect(String ip, int port){
        try{
            Socket socket = new Socket(ip, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),
                    true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }

    }

    @Override
    public void action(){
        while(true) {
            String cmd = Executer.clientCmd.readLine();
        }
    }
}
