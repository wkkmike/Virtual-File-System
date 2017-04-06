package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.client.ClientOn;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 06/04/2017.
 */
public class ConnectCmd {
    public void command(IOService ioService, VirtualDisk disk){
        ioService.printLine("please enter the server ip");
        String ip = ioService.readLine();
        ioService.printLine("Please enter the server port");
        String port = ioService.readLine();
        int portint = Integer.parseInt(port);
        ClientOn online = new ClientOn();
        online.setDisk(disk);
        online.connect(ip, portint);
    }
}
