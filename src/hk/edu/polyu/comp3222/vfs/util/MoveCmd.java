package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 03/04/2017.
 */
public class MoveCmd extends Command {
    public String command(String[] cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("This is the mv handler.");

        return disk.getCurrentPath();
    }
}
