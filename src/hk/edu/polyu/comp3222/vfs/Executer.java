package hk.edu.polyu.comp3222.vfs;

import hk.edu.polyu.comp3222.vfs.client.ClientOn;
import hk.edu.polyu.comp3222.vfs.util.IOService;

/**
 * Created by lidawei on 02/04/2017.
 */
public class Executer {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8888;
    public static IOService clientCmd;

    Executer(IOService clientCmd){
        this.clientCmd = clientCmd;
    }

    public static void main(String[] args){
        ClientOn client = new ClientOn();
    }
}
