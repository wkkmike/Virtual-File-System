package hk.edu.polyu.comp3222.vfs.util;


import hk.edu.polyu.comp3222.vfs.core.VFSUnit;
import hk.edu.polyu.comp3222.vfs.core.VirtualDisk;

/**
 * Created by lidawei on 03/04/2017.
 */
public class QuitCmd extends Command {
    public String command(String[] cmd, IOService ioService, VirtualDisk disk){
        ioService.printLine("quitting the VFS system");
        System.exit(0);
        return null;
    }
}
