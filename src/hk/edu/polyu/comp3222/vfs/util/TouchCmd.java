package hk.edu.polyu.comp3222.vfs.util;

import hk.edu.polyu.comp3222.vfs.core.*;

/**
 * Created by lidawei on 03/04/2017.
 */
public class TouchCmd extends Command {
    public VFSUnit command(String[] cmd, IOService ioService, VirtualDisk disk){
        //ioService.printLine("This is the touch handler.");
        String fileName = null;
        if (cmd.length > 1 && !cmd[1].equals(null)) {
            fileName = cmd[1];
        } else {
            ioService.printLine("Wrong Argument for touch command");
        }
        disk.createFile(fileName);
        return disk.getCurrentPath();
    }
}
