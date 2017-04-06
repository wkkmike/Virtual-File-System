package hk.edu.polyu.comp3222.vfs.client;

import hk.edu.polyu.comp3222.vfs.Executer;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;
import hk.edu.polyu.comp3222.vfs.util.*;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lidawei on 04/04/2017.
 */
public class ClientOff extends Client {
    public ClientOff(VirtualDisk disk) {
        super(disk);
    }

    @Override
    public void action() {
        while (true) {
            String input = Executer.clientCmd.readLine();
            IOService output = null;
            StringTokenizer st = new StringTokenizer(input, " ");
            ArrayList<String> cmd = null;
            int count = 0;
            while (st.hasMoreTokens()) {
                cmd.add(st.nextToken());
            }
            CommandType c = CommandType.valueOf(cmd.get(0));
            CommandReciever receiver = new CommandReciever();
            receiver.execute(c, cmd, output, disk);

        }
    }
}
