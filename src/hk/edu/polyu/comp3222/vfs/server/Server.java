package hk.edu.polyu.comp3222.vfs.server;


import java.io.*;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by lidawei on 03/04/2017.
 */
public class Server {
    private static final Server INSTANCE = new Server();

    private Server() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Server getInstance() {
        return INSTANCE;
    }

    public void receiveFile(String savePath,String ip,int port){
        Socket socket=null;
        try {
            socket = new Socket(ip,port);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        DataInputStream dis=null;
        try {
            dis = new DataInputStream(new BufferedInputStream(socket
                    .getInputStream()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        int bufferSize = 1024;
        byte[] buf = new byte[bufferSize];
        int passedlen = 0;
        try{
            savePath += dis.readUTF();
            DataOutputStream fileOut = new DataOutputStream(
                    new BufferedOutputStream(new BufferedOutputStream(
                            new FileOutputStream(savePath))));
            while (true) {
                int read = 0;
                if (dis != null) {
                    read = dis.read(buf);
                }
                passedlen += read;
                if (read == -1) {
                    break;
                }
                fileOut.write(buf, 0, read);
            }
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
